package com.decade.framework.async;

import java.io.IOException;

import android.content.Context;
import android.text.TextUtils;

import com.decade.framework.DCApplication;
import com.decade.framework.async.core.DCCoreAsyncTask;
import com.decade.framework.cache.DCJsonFileCache;
import com.decade.framework.kit.DCEncryptTool;
import com.decade.framework.kit.DCLog;
import com.decade.framework.network.DCNetStatusDefine;
import com.decade.framework.network.DCNetWorkeUtility;
import com.decade.framework.network.DCRequestParams;

/**
 * @description: 网络请求抽象基类
 * @author: Decade
 * @date: 2013-5-20
 * 
 */
public abstract class DCBaseAsyncTask<Params, Progress> extends
		DCCoreAsyncTask<Params, Object, DCiResponse> {
	private int _statusCode = DCNetStatusDefine.S_DEFAULT;

	/**
	 * @preserve
	 */
	protected static final int HTTP_POST = 10;
	/**
	 * @preserve
	 */
	protected static final int HTTP_GET = 11;
	private DCAsyncTaskParams _taskParams;
	private Context _context;

	public DCBaseAsyncTask(Context context, DCAsyncTaskParams taskParams) {
		super();
		_context = context;
		_taskParams = taskParams;
	}

	protected DCiCacheType getCacheType() {
		if (_taskParams != null) {
			return _taskParams.getCacheType();
		}
		return null;
	}

	protected DCiAsyncTaskCallback getEvent() {
		if (_taskParams != null) {
			return _taskParams.getEvent();
		}
		return null;
	}

	public DCAsyncTaskParams getTaskParams() {
		return _taskParams;
	}

	public DCiResponse getParse() {
		if (_taskParams != null) {
			return _taskParams.getParse();
		}
		return null;
	}

	public void setTaskParams(DCAsyncTaskParams taskParams) {
		_taskParams = taskParams;
	}

	@Override
	protected DCiResponse doInBackground(Params... params) {
		return null;
	}

	protected String requestData(String url, DCRequestParams params,
			int httpType) throws IOException {
		if (DCApplication.getApp() != null) {
			if (DCApplication.getApp().isNetStatus()) {
				if (httpType == HTTP_POST) {
					return DCNetWorkeUtility.requestHttpPost(url, params);

				} else if (httpType == HTTP_GET) {
					return DCNetWorkeUtility.requestHttpGet(url, params);
				}
			} else {
				senddata(DCNetStatusDefine.S_NET_DISCONNECTED);
			}
		} else {
			DCLog.e(getClass(), "DCApplication not initialize");
		}
		return null;
	}

	protected String requestData(String url, String params, int httpType)
			throws IOException {
		if (DCApplication.getApp() != null) {
			if (DCApplication.getApp().isNetStatus()) {
				if (httpType == HTTP_POST) {
					return DCNetWorkeUtility.requestHttpPost(url, params);
				} else if (httpType == HTTP_GET) {
					return DCNetWorkeUtility.requestHttpGet(url, params);
				}
			} else {
				senddata(DCNetStatusDefine.S_NET_DISCONNECTED);
			}
		} else {
			DCLog.e(getClass(), "DCApplication not initialize");
		}
		return null;
	}

	protected void saveCache(String response_Text, String urlCache) {
		if (!TextUtils.isEmpty(response_Text)) {
			if (_taskParams.isEncrypt()) {
				String text = DCEncryptTool.getInstance().encrypt(
						DCEncryptTool.KEY, response_Text);
				DCJsonFileCache.getJsonFileCache().saveCache(text, urlCache);
			} else {
				DCJsonFileCache.getJsonFileCache().saveCache(response_Text,
						urlCache);
			}
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (_taskParams != null) {
			onStart(_taskParams.isOpenPrompt(), _taskParams.getPromptContent());
		}
	}

	@Override
	protected void onPostExecute(DCiResponse result) {
		requestComplete(result);
	}

	public void senddata(Object... values) {
		publishProgress(values);
	}

	@Override
	protected void onProgressUpdate(Object... values) {
		Integer code = (Integer) values[0];
		switch (code) {
		case DCNetStatusDefine.SHOW_CACHE:
			DCLog.d(getClass(), "show cache");
			DCiResponse response = (DCiResponse) values[1];
			_statusCode = DCNetStatusDefine.S_COMPLETE;
			requestComplete(response);
			break;
		case DCNetStatusDefine.SHOW_LOADING:
			DCLog.d(getClass(), "show loading");
			openTopLoadView();
			break;
		case DCNetStatusDefine.CLOSE_LOADING:
			DCLog.d(getClass(), "close loading");
			closeTopLoadView();
			break;
		case DCNetStatusDefine.S_CACHE_NO_EXPIRED:
			_statusCode = DCNetStatusDefine.S_CACHE_NO_EXPIRED;
			DCLog.i(getClass(), "Cache has not expired !");
			break;
		case DCNetStatusDefine.S_NET_DISCONNECTED:
			DCLog.e(getClass(), "network disconnected");
			_statusCode = DCNetStatusDefine.S_NET_DISCONNECTED;
			netDisconnected();
			break;
		case DCNetStatusDefine.S_COMPLETE:
			DCLog.d(getClass(), "complete");
			if (_statusCode != DCNetStatusDefine.S_NET_DISCONNECTED) {
				_statusCode = DCNetStatusDefine.S_COMPLETE;
			}
			break;
		case DCNetStatusDefine.S_JSON_PARSE_ERROR:
			DCLog.e(getClass(), "json parse error");
			_statusCode = DCNetStatusDefine.S_JSON_PARSE_ERROR;
			onJsonPaserError((String) values[1]);
			closeTopLoadView();
			break;
		case DCNetStatusDefine.S_NETWORK_ERROR:
			DCLog.e(getClass(), "network error");
			if (_statusCode != DCNetStatusDefine.S_NET_DISCONNECTED) {
				_statusCode = DCNetStatusDefine.S_NETWORK_ERROR;
				onServerRequestError((String) values[1]);
			}
			closeTopLoadView();
			break;
		default:
			break;
		}

	}

	private void requestComplete(DCiResponse result) {
		DCLog.d(getClass(), "requestComplete" + _statusCode);
		if (_taskParams != null) {
			onFinish(_taskParams.isClosePrompt());
		}
		if (_statusCode == DCNetStatusDefine.S_COMPLETE) {
			onComplete(result);
		}
	}

	protected DCiResponse doJsonParse(String response_text) throws Exception {
		DCiResponse response = null;
		if (getParse() != null) {
			try {
				response = getParse().paser(response_text);
			} catch (Exception e) {
				throw new Exception("json_parse_error", e);
			}
		}
		return response;
	}

	public void openTopLoadView() {
		if (getEvent() != null) {
			getEvent().openTopLoadView();
		}
	}

	public void closeTopLoadView() {

		if (getEvent() != null) {
			getEvent().closeTopLoadView();
		}
	}

	protected void onServerRequestError(String message) {
		if (getEvent() != null) {
			getEvent().onServerResponseError(message);
		}
	}

	protected void onJsonPaserError(String message) {
		if (getEvent() != null) {
			getEvent().onJsonPaserError(message);
		}
	}

	protected void netDisconnected() {
		if (getEvent() != null) {
			getEvent().onNetDisconnected();
		}
	}

	protected void onComplete(DCiResponse _response) {
		if (getEvent() != null) {
			if (_taskParams != null) {
				getEvent().onComplete(_response, _taskParams.getRequestCode());
			}
		}
	}

	/**
	 * @preserve
	 * @return
	 */
	public Context getContext() {
		return _context;
	}

	/**
	 * @preserve
	 * @param openPrompt
	 */
	protected void onStart(boolean openPrompt, String content) {
	}

	/**
	 * @preserve
	 * @param closePrompt
	 */
	protected void onFinish(boolean closePrompt) {
	}

}
