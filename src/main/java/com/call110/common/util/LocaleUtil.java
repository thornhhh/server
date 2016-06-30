package com.call110.common.util;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public class LocaleUtil {
	public static String getCurrentLanguage(){
		Locale locale = LocaleContextHolder.getLocale();
		return locale.getLanguage(); 
	}
}
