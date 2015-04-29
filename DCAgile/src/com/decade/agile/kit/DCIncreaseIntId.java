package com.decade.agile.kit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: int 类型的自增长ID生成类，支持多线程。
 * @author: Decade
 * @date: 2013-5-24
 * 
 */

public class DCIncreaseIntId implements DCIdGenerator<Integer> {
	private AtomicInteger id;

	public DCIncreaseIntId() {
		id = new AtomicInteger();
	}

	public DCIncreaseIntId(int initialId) {
		id = new AtomicInteger(initialId);
	}


	public Integer next() {
		return id.incrementAndGet();
	}

}