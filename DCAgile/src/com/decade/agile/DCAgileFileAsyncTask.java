package com.decade.agile;

import java.io.File;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.decade.agile.kit.DCDialogHelper;
import com.decade.agile.kit.DCDialogHelper.DialogTheme;
import com.decade.framework.async.DCAsyncTaskParams;
import com.decade.framework.async.DCFileAsyncTask;
import com.decade.framework.async.DCiResponse;

/**
 * @description: 文件上传
 * @author: Decade
 * @date: 2014-7-5
 */
public class DCAgileFileAsyncTask extends DCFileAsyncTask<String, Integer>{

	public DCAgileFileAsyncTask(Context context, DCAsyncTaskParams params) {
		super(context, params);
	}
	
	public DCiResponse doTask(String url, Map<String, String> params,
			Map<String, File> files) {
		return doUpload(url, params, files);
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
