package com.allxone.coinmarket.utilities;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookiesUtils {
	
	public static Cookie add(String name, String value, int days, HttpServletResponse resp) {
		Cookie cookie = new Cookie(name,value);
		cookie.setMaxAge(days*60*60*60);
		cookie.setPath("/");
		resp.addCookie(cookie);
		return cookie;
	}
	
	public static String get(String name, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if(cookies!=null) {
			for (Cookie cookie:cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
}
