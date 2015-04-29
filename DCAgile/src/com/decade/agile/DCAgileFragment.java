package com.decade.agile;

import android.support.v4.app.Fragment;

import com.decade.agile.components.DCRectToast;
import com.decade.agile.components.DCRectToast.ToastTheme;
import com.decade.framework.DCActivity;
import com.decade.framework.async.DCiAsyncTaskCallback;
import com.decade.framework.async.DCiResponse;

public class DCAgileFragment extends Fragment implements DCiAsyncTaskCallback {

	@Override
	public void onJsonPaserError(String message,int requestCode) {
		DCRectToast.showToastShort(getActivity(), "数据解析异常!", ToastTheme.ERROR);

	}

	@Override
	public void onNetDisconnected(int requestCode) {
		DCRectToast.showToastShort(getActivity(), "网络异常!", ToastTheme.WARNING);
	}

	@Override
	public void onServerResponseError(String message,int requestCode) {
		DCRectToast.showToastShort(getActivity(), "请求异常!", ToastTheme.ERROR);

	}

	@Override
	public void openTopLoadView(int requestCode) {
		((DCActivity) getActivity()).openLoading();
	}

	@Override
	public void closeTopLoadView(int requestCode) {
		((DCActivity) getActivity()).closeLoading();
	}

	@Override
	public void onComplete(DCiResponse response, int requestCode) {

	}

}
