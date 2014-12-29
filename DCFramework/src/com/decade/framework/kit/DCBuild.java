package com.decade.framework.kit;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-6-4
 * @preserve protected
 */
public class DCBuild {
	private static boolean _debug = true;
	/**
	 * 设置调试状态，为true会打印日志
	 * @param debug
	 */
	public static void setDebugMode(boolean debug){
		
		_debug = debug;
	}
	
	public static boolean getDebugMode(){
		return _debug;
	}
}
