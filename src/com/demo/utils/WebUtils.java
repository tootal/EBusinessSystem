package com.demo.utils;

import java.util.UUID;

/**
 * utils-生成随机数
 * @author MouseHappy
 */
public class WebUtils {

	/**
	 * 生成随机ID
	 * @return
	 */
	public static String makeID(){
		return UUID.randomUUID().toString();
	}
}
