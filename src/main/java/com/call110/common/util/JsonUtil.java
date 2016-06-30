package com.call110.common.util;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	public static String obj2string(Object obj) throws IOException{
		return mapper.writeValueAsString(obj);
	}
	public static <T> T string2obj(String str, Class<T> valueType) throws IOException{
		return mapper.readValue(str, valueType);
	}
	public static ObjectNode createJson(){
		return factory.objectNode();
	}
}
