package com.call110.common.util;

import java.io.UnsupportedEncodingException;

public abstract class BaseA {
    public static final int MIME_CHUNK_SIZE = 76;

    public static final int PEM_CHUNK_SIZE = 64;

    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    protected static final int MASK_8BITS = 0xff;
    protected static final byte PAD_DEFAULT = '='; // Allow static access to default
    
    protected final byte PAD = PAD_DEFAULT; // instance variable just in case it needs to vary later

    private final int unencodedBlockSize;

    private final int encodedBlockSize;

    protected final int lineLength;
    
    private final int chunkSeparatorLength;

    protected byte[] buffer;

    protected int pos;

    private int readPos;

    protected boolean eof;

    protected int currentLinePos;

    protected int modulus;

    protected BaseA(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength){
        this.unencodedBlockSize = unencodedBlockSize;
        this.encodedBlockSize = encodedBlockSize;
        this.lineLength = (lineLength > 0  && chunkSeparatorLength > 0) ? (lineLength / encodedBlockSize) * encodedBlockSize : 0;
        this.chunkSeparatorLength = chunkSeparatorLength;
    }

    boolean hasData() {  // package protected for access from I/O streams
        return this.buffer != null;
    }

    int available() {  // package protected for access from I/O streams
        return buffer != null ? pos - readPos : 0;
    }

    protected int getDefaultBufferSize() {
        return DEFAULT_BUFFER_SIZE;
    }

    /** Increases our buffer by the {@link #DEFAULT_BUFFER_RESIZE_FACTOR}. */
    private void resizeBuffer() {
        if (buffer == null) {
            buffer = new byte[getDefaultBufferSize()];
            pos = 0;
            readPos = 0;
        } else {
            byte[] b = new byte[buffer.length * DEFAULT_BUFFER_RESIZE_FACTOR];
            System.arraycopy(buffer, 0, b, 0, buffer.length);
            buffer = b;
        }
    }

    /**
     * Ensure that the buffer has room for <code>size</code> bytes
     *
     * @param size minimum spare space required
     */
    protected void ensureBufferSize(int size){
        if ((buffer == null) || (buffer.length < pos + size)){
            resizeBuffer();
        }
    }

    int readResults(byte[] b, int bPos, int bAvail) {  // package protected for access from I/O streams
        if (buffer != null) {
            int len = Math.min(available(), bAvail);
            System.arraycopy(buffer, readPos, b, bPos, len);
            readPos += len;
            if (readPos >= pos) {
                buffer = null; // so hasData() will return false, and this method can return -1
            }
            return len;
        }
        return eof ? -1 : 0;
    }

    protected static boolean isWhiteSpace(byte byteToCheck) {
        switch (byteToCheck) {
            case ' ' :
            case '\n' :
            case '\r' :
            case '\t' :
                return true;
            default :
                return false;
        }
    }

    /**
     * Resets this object to its initial newly constructed state.
     */
    private void reset() {
        buffer = null;
        pos = 0;
        readPos = 0;
        currentLinePos = 0;
        modulus = 0;
        eof = false;
    }

    public Object encode(Object pObject) throws RuntimeException {
        if (!(pObject instanceof byte[])) {
            throw new RuntimeException("Parameter supplied to Base-N encode is not a byte[]");
        }
        return encode((byte[]) pObject);
    }

    public String encodeToString(byte[] pArray) {
        return newStringUtf8(encode(pArray));
    }

    public Object decode(Object pObject) throws RuntimeException {        
        if (pObject instanceof byte[]) {
            return decode((byte[]) pObject);
        } else if (pObject instanceof String) {
            return decode((String) pObject);
        } else {
            throw new RuntimeException("Parameter supplied to Base-N decode is not a byte[] or a String");
        }
    }

    public byte[] decode(String pArray) {
        return decode(getBytesUtf8(pArray));
    }

    public byte[] decode(byte[] pArray) {
        reset();
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        decode(pArray, 0, pArray.length);
        decode(pArray, 0, -1); // Notify decoder of EOF.
        byte[] result = new byte[pos];
        readResults(result, 0, result.length);
        return result;
    }

    public byte[] encode(byte[] pArray) {
        reset();        
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        encode(pArray, 0, pArray.length);
        encode(pArray, 0, -1); // Notify encoder of EOF.
        byte[] buf = new byte[pos - readPos];
        readResults(buf, 0, buf.length);
        return buf;
    }
    
    public String encodeAsString(byte[] pArray){
        return newStringUtf8(encode(pArray));
    }

    abstract void encode(byte[] pArray, int i, int length);  // package protected for access from I/O streams

    abstract void decode(byte[] pArray, int i, int length); // package protected for access from I/O streams
    
    protected abstract boolean isInAlphabet(byte value);
    
    public boolean isInAlphabet(byte[] arrayOctet, boolean allowWSPad) {
        for (int i = 0; i < arrayOctet.length; i++) {
            if (!isInAlphabet(arrayOctet[i]) &&
                    (!allowWSPad || (arrayOctet[i] != PAD) && !isWhiteSpace(arrayOctet[i]))) {
                return false;
            }
        }
        return true;
    }

    public boolean isInAlphabet(String basen) {
        return isInAlphabet(getBytesUtf8(basen), true);
    }

    protected boolean containsAlphabetOrPad(byte[] arrayOctet) {
        if (arrayOctet == null) {
            return false;
        }
        for (byte element : arrayOctet) {
            if (PAD == element || isInAlphabet(element)) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] pArray) {
        long len = ((pArray.length + unencodedBlockSize-1)  / unencodedBlockSize) * (long) encodedBlockSize;
        if (lineLength > 0) { // We're using chunking
            len += ((len + lineLength-1) / lineLength) * chunkSeparatorLength;
        }
        return len;
    }
    protected static String newStringUtf8(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8", e);
        }
    }
    protected static byte[] getBytesUtf8(String string) {
        if (string == null) {
            return null;
        }
        try {
            return string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        	throw new RuntimeException("UTF-8", e);
        }
    }
}
