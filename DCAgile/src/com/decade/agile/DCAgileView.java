package com.decade.agile;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.decade.agile.components.DCRectToast;
import com.decade.agile.components.DCRectToast.ToastTheme;
import com.decade.framework.DCBaseView;
import com.decade.framework.DCBaseViewActivity;
import com.decade.framework.async.DCiAsyncTaskCallback;
import com.decade.framework.async.DCiResponse;
import com.decade.framework.kit.DCBuild;

public abstract class DCAgileView extends DCBaseView implements DCiAsyncTaskCallback  {

	protected void open() {
	};// 3.打开，获取数据打开视图

	protected void resume() {
	};// 4.恢复视图

	protected void pause() {
		breakTask();
	};// 5.中止

	protected void destroy() {
	};// 7.销毁

	public DCAgileView(ViewGroup root, DCBaseViewActivity parent) {
		super(root, parent);
	}

	private final void breakTask() {
		resetLoading();
		setTopRightViewEnabled(true);
		
	}
	

	@Override
	public void closeTopLoadView() {
		closeLoading();
	}

	@Override
	public void openTopLoadView() {
		openLoading();
	}

	@Override
	public void onJsonPaserError(String message) {
		if(DCBuild.getDebugMode()){
			DCRectToast.showToastLong(getContext(), "数据解析异常!"+ message,ToastTheme.ERROR);

		}else {
			DCRectToast.showToastShort(getContext(), "数据解析异常!",ToastTheme.ERROR);
		}
	}

	@Override
	public void onNetDisconnected() {
		DCRectToast.showToastShort(getContext(), "网络连接异常!",ToastTheme.WARNING);
	}

	@Override
	public void onServerResponseError(String message) {
		if(DCBuild.getDebugMode()){
			DCRectToast.showToastLong(getContext(), "请求异常!"+message,ToastTheme.ERROR);

		}else {
			DCRectToast.showToastShort(getContext(), "请求异常!",ToastTheme.ERROR);

		}
	}

	@Override
	public void onComplete(DCiResponse response, int requestCode) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	protected void startActivity(Intent intent) {
		super.startActivity(intent);
		getParent().overridePendingTransition(R.anim.agile_activity_open_enter, R.anim.agile_activity_open_exit);

	}

	@Override
	protected boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			preViewLastPage();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
