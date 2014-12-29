package com.decade.agile.kit;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-5-24
 * @preserve protected
 */
public interface DCIdGenerator<T> {
	/**
	 * 生成下一个id并返回。
	 * 
	 * @return 返回新的id.
	 */
	public T next();

}
