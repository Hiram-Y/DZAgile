package com.decade.agile.kit;

import android.content.Context;
import android.text.TextUtils;

import com.decade.agile.R;
import com.decade.agile.components.DCBaseDialogParams;
import com.decade.agile.components.DCRectDialog;
import com.decade.agile.components.DCStripDialog;
import com.decade.agile.components.DCiDialog;

/**
 * @description: 对话框辅助类
 * @author: Decade
 * @date: 2013-6-14
 */
public class DCDialogHelper {
	public static DCiDialog _dialog;

	/**
	 * 创建一个加载对话框
	 * 
	 * @param context
	 * @param content
	 */
	private static void createPrompt(Context context, String content,
			DialogTheme theme) {
		DCBaseDialogParams params = new DCBaseDialogParams();
		if (theme == DialogTheme.STRIP) {
			if (TextUtils.isEmpty(content)) {
				params.setContent(context.getString(R.string.data_loading));
			} else {
				params.setContent(content);
			}
			_dialog = DCStripDialog.getInstance(context, params);
		} else if (theme == DialogTheme.RECT) {
			if (TextUtils.isEmpty(content)) {
				params.setContent(context
						.getString(R.string.agile_load_msg_ing));
			} else {
				params.setContent(content);
			}
			_dialog = DCRectDialog.getInstance(context, params);
		}
		if (_dialog != null) {
			_dialog.open();
		}
	}

	/**
	 * 打开加载对话框
	 * 
	 * @param context
	 */
	public static void openPrompt(Context context, DialogTheme theme) {
		openPrompt(context, null, theme);
	}

	public static void openPrompt(Context context, String content,
			DialogTheme theme) {
		createPrompt(context, content, theme);
	}

	/**
	 * 关闭加载对话框
	 */
	public static void closePrompt() {
		if (_dialog != null) {
			_dialog.close();
		}
	}

	public enum DialogTheme {
		RECT, STRIP
	}

}
