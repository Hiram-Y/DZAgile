package com.decade.framework.async;

import java.io.IOException;

import android.text.TextUtils;

import com.decade.framework.network.DCNetStatusDefine;
import com.decade.framework.network.DCNetWorkeUtility;
import com.decade.framework.network.DCRequestParams;

/**
 * @description: 缓存策略，不使用缓存但是存储
 * @author: Decade
 * @date: 2013-5-31
 * 
 */
public class DCNonuseCacheButSave implements DCiCacheType {

	public String _cacheName;

	public DCNonuseCacheButSave(String cacheName) {
		_cacheName = cacheName;
	}

	public DCiResponse processCache(DCBaseAsyncTask<?, ?> ayncTask, String url,
			DCRequestParams params, int httpType) throws IOException,
			Exception {
		String responseResult = ayncTask.requestData(url, params, httpType);
		if (TextUtils.isEmpty(_cacheName)) {
			_cacheName = DCNetWorkeUtility.getUrlWithQueryString(url, params);
		}
		DCiResponse result = ayncTask.doJsonParse(responseResult);
		ayncTask.senddata(DCNetStatusDefine.S_COMPLETE);
		ayncTask.saveCache(responseResult, _cacheName);
		return result;
	}


	public DCiResponse processCache(DCBaseAsyncTask<?, ?> ayncTask, String url,
			String params, int httpType) throws IOException, Exception {
		String responseResult = ayncTask.requestData(url, params, httpType);
		if (TextUtils.isEmpty(_cacheName)) {
			_cacheName = url + params;
		}
		DCiResponse result = ayncTask.doJsonParse(responseResult);
		ayncTask.senddata(DCNetStatusDefine.S_COMPLETE);
		ayncTask.saveCache(responseResult, _cacheName);
		return result;
	}

}
