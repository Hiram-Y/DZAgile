/**
 * 
 */
package com.decade.framework.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.decade.framework.DCApplication;

/**
 * @author Decade 侦听网络状态的变化
 * 
 */
public class DCNetBroadcast extends BroadcastReceiver {
	private static DCiNetStateCallback _netStateCallback = null;
	private static DCNetBroadcast _receiver = new DCNetBroadcast();
	private static IntentFilter _intentFilter;

	public static void registerReceiver(Context context) {
		setReceiver(context);
	}
	
	public static void registerReceiver(Context context,DCiNetStateCallback netStateCallback) {
		_netStateCallback = netStateCallback;
		setReceiver(context);
	}

	public static void unregisterReceiver(Context context) {
		if(_receiver!=null){
			context.unregisterReceiver(_receiver);
		}
	}

	private static void setReceiver(Context context) {
		_intentFilter = new IntentFilter();
		_intentFilter.addAction(DCBroadcaseDefine.NET_CONNECT);
		context.registerReceiver(_receiver, _intentFilter);
	}

	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(DCBroadcaseDefine.NET_CONNECT)) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = connectivityManager.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				// 判断当前网络是否已经连接
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					DCApplication.getApp().setNetStatus(true);
					if (_netStateCallback != null) {
						_netStateCallback.connected();
					}
				} else {
					DCApplication.getApp().setNetStatus(false);
					if (_netStateCallback != null) {
						_netStateCallback.disconnected();
					}
				}
			} else {
				DCApplication.getApp().setNetStatus(false);
				if (_netStateCallback != null) {
					_netStateCallback.disconnected();
				}
			}
		}
	}

	/**
	 * @description:
	 * @author: Decade
	 * @date: 2013-9-16
	 * 
	 */
	public interface DCiNetStateCallback {
		public void connected();
		public void disconnected();
	}
}
