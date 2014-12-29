package com.decade.framework.async;

import java.io.IOException;

import android.content.Context;

import com.decade.framework.network.DCNetStatusDefine;
import com.decade.framework.network.DCRequestParams;

/**
 * @description: Http请求发送数据
 * @author: Decade
 * @date: 2013-5-6
 * @preserve protected
 */
public abstract class DCHttpAsyncTask<Params, Progress> extends
		DCBaseAsyncTask<Params, Progress> {

	/**
	 * @param taskParams
	 */
	public DCHttpAsyncTask(Context context, DCAsyncTaskParams taskParams) {
		super(context, taskParams);
	}

	protected DCiResponse doRequestFromHttp(String url, DCRequestParams params,int httpType) {
		try {
			DCiResponse result = getCacheType().processCache(this, url, params,
					httpType);
			return result;
		} catch (IOException e) {
			senddata(DCNetStatusDefine.S_NETWORK_ERROR,e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			if(e.getMessage().equals("json_parse_error")){
				senddata(DCNetStatusDefine.S_JSON_PARSE_ERROR,e.getMessage());
			}
			e.printStackTrace();
		}
		return null;
	}
	
	protected DCiResponse doRequestFromHttp(String url, String params,int httpType) {
		try {
			DCiResponse result = getCacheType().processCache(this, url, params,
					httpType);
			return result;
		} catch (IOException e) {
			senddata(DCNetStatusDefine.S_NETWORK_ERROR,e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			if(e.getMessage().equals("json_parse_error")){
				senddata(DCNetStatusDefine.S_JSON_PARSE_ERROR,e.getMessage());
			}
			e.printStackTrace();
		}
		return null;
	}

}
