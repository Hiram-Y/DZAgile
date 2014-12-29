package com.decade.agile;

import android.content.Intent;
import android.view.ViewGroup;

import com.decade.agile.components.DCCommonTopView;
import com.decade.agile.components.DCRectToast;
import com.decade.agile.components.DCRectToast.ToastTheme;
import com.decade.framework.DCActivity;
import com.decade.framework.DCiBottomView;
import com.decade.framework.DCiTopView;
import com.decade.framework.async.DCiAsyncTaskCallback;
import com.decade.framework.async.DCiResponse;

/**
 * @description: 项目activity 基类
 * @author: Decade
 * @date: 2013-5-20
 */
public class DCAgileActivity extends DCActivity implements DCiAsyncTaskCallback {

	@Override
	public void onJsonPaserError(String message) {
		DCRectToast.showToastShort(this, "数据解析异常!",ToastTheme.ERROR);

	}

	@Override
	public void onNetDisconnected() {
		DCRectToast.showToastShort(this, "网络异常!",ToastTheme.WARNING);
	}

	@Override
	public void onServerResponseError(String message) {
		DCRectToast.showToastShort(this, "请求无响应!",ToastTheme.ERROR);

	}

	@Override
	public void openTopLoadView() {
		openLoading();
	}

	@Override
	public void closeTopLoadView() {
		closeLoading();
	}

	@Override
	public void onComplete(DCiResponse response, int requestCode) {

	}
	
	@Override
	protected DCiTopView getTopViewLoader() {
		return new DCCommonTopView(this,
				((ViewGroup) findViewById(R.id.main_top_view)));
	}

	@Override
	protected DCiBottomView getBottomViewLoader() {
		return null;
	}

	@Override
	public void finish() {
		super.finish();
		finishTransitionAnim();
	}

	public void finishTransitionAnim() {
		overridePendingTransition(R.anim.agile_activity_close_enter,
				R.anim.agile_activity_close_exit);
	}

	public void startTransitionAnim() {
		overridePendingTransition(R.anim.agile_activity_open_enter,
				R.anim.agile_activity_open_exit);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		startTransitionAnim();
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		startTransitionAnim();
	}
	
	

}
