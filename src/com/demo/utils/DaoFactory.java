package com.demo.utils;

/**
 * utils
 * @author MouseHappy
 */
public class DaoFactory {
	
	private static final DaoFactory factory = new DaoFactory();
	private DaoFactory(){}
	
	public static DaoFactory getInstance(){
		return factory;
	}
	
	/**
	 * 创建DaoImpl对象
	 * @param className
	 * @param clazz
	 * @return
	 */
	public <T> T createDao(String className, Class<T> clazz){
		try{
			T t = (T) Class.forName(className).newInstance();
			return t;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
