package com.decade.framework.async;

import java.io.IOException;

import org.json.JSONException;

import android.text.TextUtils;

import com.decade.framework.cache.DCJsonFileCache;
import com.decade.framework.kit.DCEncryptTool;
import com.decade.framework.kit.DCLog;
import com.decade.framework.network.DCNetStatusDefine;
import com.decade.framework.network.DCNetWorkeUtility;
import com.decade.framework.network.DCRequestParams;

/**
 * @description: 缓存策略，不适用缓存
 * @author: Decade
 * @date: 2013-5-31
 * @preserve protected
 */
public class DCUseCache implements DCiCacheType {

	private long _cacheIndate;
	private String _cacheName;

	public DCUseCache(String cacheName, long cacheIndate) {
		_cacheName = cacheName;
		_cacheIndate = cacheIndate;
	}
	
	public DCiResponse processCache(final DCBaseAsyncTask<?, ?> ayncTask,
			String url, DCRequestParams params, int httpType)
			throws IOException, Exception {
		final String cacheResult = getDataFromDiskCache(ayncTask, url, params);
		if (cacheResult == null) { // 如果没有缓存，下载数据，存储。
			DCLog.d(getClass(), "Without cache");
			return requestDataAndSave(ayncTask, url, params, httpType);
		} else { // 如果有缓存先显示缓存
			DCiResponse cacheRs = ayncTask.doJsonParse(cacheResult);
			ayncTask.senddata(DCNetStatusDefine.SHOW_CACHE, cacheRs);
			if (DCJsonFileCache.getJsonFileCache().isExpiredCache( // 如果缓存过期
					_cacheName, _cacheIndate)) {
				DCLog.d(getClass(), "Cache expires");
				ayncTask.senddata(DCNetStatusDefine.SHOW_LOADING);
				DCiResponse result = requestDataAndSave(ayncTask, url, params,
						httpType);
				ayncTask.senddata(DCNetStatusDefine.CLOSE_LOADING);
				return result;
			} else {
				ayncTask.senddata(DCNetStatusDefine.S_CACHE_NO_EXPIRED);
			}
		}

		return null;
	}
	
	public DCiResponse processCache(DCBaseAsyncTask<?, ?> ayncTask, String url,
			String params, int httpType) throws IOException, Exception {
		final String cacheResult = getDataFromDiskCache(ayncTask, url, params);
		if (cacheResult == null) { // 如果没有缓存，下载数据，存储。
			DCLog.d(getClass(), "Without cache");
			return requestDataAndSave(ayncTask, url, params, httpType);
		} else { // 如果有缓存先显示缓存
			DCiResponse cacheRs = ayncTask.doJsonParse(cacheResult);
			ayncTask.senddata(DCNetStatusDefine.SHOW_CACHE, cacheRs);
			if (DCJsonFileCache.getJsonFileCache().isExpiredCache( // 如果缓存过期
					_cacheName, _cacheIndate)) {
				DCLog.d(getClass(), "Cache expires");
				ayncTask.senddata(DCNetStatusDefine.SHOW_LOADING);
				DCiResponse result = requestDataAndSave(ayncTask, url, params,
						httpType);
				ayncTask.senddata(DCNetStatusDefine.CLOSE_LOADING);
				return result;
			} else {
				ayncTask.senddata(DCNetStatusDefine.S_CACHE_NO_EXPIRED);
			}
		}

		return null;
	}

	/**
	 * 请求数据并存储
	 * 
	 * @param ayncTask
	 * @param url
	 * @param params
	 * @param httpType
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public DCiResponse requestDataAndSave(DCBaseAsyncTask<?, ?> ayncTask,
			String url, DCRequestParams params, int httpType)
			throws IOException, Exception {
		String pResult = ayncTask.requestData(url, params, httpType);
		return saveData(ayncTask, pResult);
	}

	public DCiResponse saveData(DCBaseAsyncTask<?, ?> ayncTask, String result)
			throws Exception {
		DCiResponse response = ayncTask.doJsonParse(result);
		ayncTask.senddata(DCNetStatusDefine.S_COMPLETE);
		ayncTask.saveCache(result, _cacheName);
		return response;
	}

	public DCiResponse requestDataAndSave(DCBaseAsyncTask<?, ?> ayncTask,
			String url, String params, int httpType) throws IOException,
			Exception {
		String pResult = ayncTask.requestData(url, params, httpType);
		return saveData(ayncTask, pResult);
	}

	/**
	 * 从磁盘缓存获取数据
	 * 
	 * @param url
	 * @param params
	 * @return 没有缓存返回 null
	 */
	public String getDataFromDiskCache(DCBaseAsyncTask<?, ?> ayncTask,
			String url, DCRequestParams params) {
		if (TextUtils.isEmpty(_cacheName)) {
			_cacheName = DCNetWorkeUtility.getUrlWithQueryString(url, params);
		}
		return getDataFromDiskCacheByName(ayncTask);
	}

	public String getDataFromDiskCache(DCBaseAsyncTask<?, ?> ayncTask,
			String url, String params) {
		if (TextUtils.isEmpty(_cacheName)) {
			_cacheName = url + params;
		}
		return getDataFromDiskCacheByName(ayncTask);
	}

	public String getDataFromDiskCacheByName(DCBaseAsyncTask<?, ?> ayncTask) {
		String cacheResult = DCJsonFileCache.getJsonFileCache().selectCache(
				_cacheName);
		if (!TextUtils.isEmpty(cacheResult)) {
			if (ayncTask.getTaskParams().isEncrypt()) {
				String text = DCEncryptTool.getInstance().decrypt(
						DCEncryptTool.KEY, cacheResult);
				return text;
			}
			return cacheResult;
		}
		return null;
	}

	public long getCacheIndate() {
		return _cacheIndate;
	}

	public void setCacheIndate(long cacheIndate) {
		_cacheIndate = cacheIndate;
	}

	public String getCacheName() {
		return _cacheName;
	}

	public void setCacheName(String cacheName) {
		_cacheName = cacheName;
	}
	
}
