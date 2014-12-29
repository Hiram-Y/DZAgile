package com.decade.framework.async;

import java.io.IOException;

import com.decade.framework.network.DCRequestParams;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-31
 * @preserve protected
 */
public interface DCiCacheType {
	public DCiResponse processCache( DCBaseAsyncTask<?, ?> ayncTask, String url,
			DCRequestParams params, int httpType)
			throws IOException, Exception;
	
	public DCiResponse processCache( DCBaseAsyncTask<?, ?> ayncTask, String url,
			String params, int httpType)
			throws IOException, Exception;
	
}
