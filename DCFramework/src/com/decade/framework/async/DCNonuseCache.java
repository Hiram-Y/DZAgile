package com.decade.framework.async;

import java.io.IOException;

import com.decade.framework.network.DCNetStatusDefine;
import com.decade.framework.network.DCRequestParams;

/**
 * @description: 缓存策略，不使用缓存
 * @author: Decade
 * @date: 2013-5-31
 * 
 */
public class DCNonuseCache implements DCiCacheType {

	public DCiResponse processCache(DCBaseAsyncTask<?, ?> ayncTask, String url,
			DCRequestParams params, int httpType)
			throws IOException, Exception {
		DCiResponse response = ayncTask.doJsonParse(ayncTask.requestData(url, params, httpType));
		ayncTask.senddata(DCNetStatusDefine.S_COMPLETE);
		return response;
	}

	public DCiResponse processCache(DCBaseAsyncTask<?, ?> ayncTask, String url,
			String params, int httpType) throws IOException, Exception {
		DCiResponse response = ayncTask.doJsonParse(ayncTask.requestData(url, params, httpType));
		ayncTask.senddata(DCNetStatusDefine.S_COMPLETE);
		return response;
	}

}
