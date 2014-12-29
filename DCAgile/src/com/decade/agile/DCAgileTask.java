package com.decade.agile;

import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;

import com.decade.agile.kit.DCDialogHelper;
import com.decade.agile.kit.DCDialogHelper.DialogTheme;
import com.decade.framework.async.DCAsyncTaskParams;
import com.decade.framework.async.DCHttpAsyncTask;
import com.decade.framework.async.DCiResponse;
import com.decade.framework.network.DCRequestParams;

/**
 * @description
 * @author Decade
 * @date 2013-4-23
 */
public abstract class DCAgileTask <Params, Progress> extends DCHttpAsyncTask<Params, Progress> {

	public DCAgileTask(Context context, DCAsyncTaskParams taskParams) {
		super(context, taskParams);
	}

	public DCiResponse doTask(String url, DCRequestParams params, int httpType) {
		return doRequestFromHttp(url, params, httpType);
	}
	
	public DCiResponse doTask(String url, JSONObject params, int httpType) {
		return doTask(url, params.toString(), httpType);
	}
	
	public DCiResponse doTask(String url, String params, int httpType) {
		return doRequestFromHttp(url, params.toString(), httpType);
	}
	
	

	@Override
	protected void onFinish(boolean closePrompt) {
		super.onFinish(closePrompt);
		if (closePrompt) {
			DCDialogHelper.closePrompt();
		}
	}

	@Override
	protected void onStart(boolean openPrompt, String content) {
		super.onStart(openPrompt,content);
		if (openPrompt) {
			if (TextUtils.isEmpty(content)) {
				DCDialogHelper.openPrompt(getContext(), DialogTheme.RECT);
			} else {
				DCDialogHelper.openPrompt(getContext(), content,
						DialogTheme.RECT);
			}
		}
	}

}
