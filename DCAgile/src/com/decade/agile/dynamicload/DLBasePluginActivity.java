/*
 * Copyright (C) 2014 singwhatiwanna(任玉刚) <singwhatiwanna@gmail.com>
 *
 * collaborator:田啸,宋思宇
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.decade.agile.dynamicload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

import com.decade.agile.DCAgileActivity;

/**
 * note: can use that like this.
 * 
 * @see {@link DLBasePluginActivity.that}
 * @author renyugang
 */
public class DLBasePluginActivity extends DCAgileActivity implements DLPlugin {

	private static final String TAG = "DLBasePluginActivity";


	/**
	 * 等同于mProxyActivity，可以当作Context来使用，会根据需要来决定是否指向this<br/>
	 * 可以当作this来使用
	 */
	protected Activity that;
	protected DLPluginManager mPluginManager;
	protected DLPluginPackage mPluginPackage;
	protected int mFrom = DLConstants.FROM_INTERNAL;

	@Override
	public void attach(Activity proxyActivity, DLPluginPackage pluginPackage) {
		Log.d(TAG, "attach: proxyActivity= " + proxyActivity);
		that = (Activity) proxyActivity;
		mPluginPackage = pluginPackage;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mFrom = savedInstanceState.getInt(DLConstants.FROM,
					DLConstants.FROM_INTERNAL);
		}
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onCreate(savedInstanceState);
			that = this;
		}

		mPluginManager = DLPluginManager.getInstance(that);
		Log.d(TAG,
				"onCreate: from= "
						+ (mFrom == DLConstants.FROM_INTERNAL ? "DLConstants.FROM_INTERNAL"
								: "FROM_EXTERNAL"));
	}

	@Override
	public void setContentView(View view) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.setContentView(view);
		} else {
			that.setContentView(view);
		}
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.setContentView(view, params);
		} else {
			that.setContentView(view, params);
		}
	}

	@Override
	public void setContentView(int layoutResID) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.setContentView(layoutResID);
		} else {
			that.setContentView(layoutResID);
		}
	}

	@Override
	public void addContentView(View view, LayoutParams params) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.addContentView(view, params);
		} else {
			that.addContentView(view, params);
		}
	}

	@Override
	public View findViewById(int id) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.findViewById(id);
		} else {
			return that.findViewById(id);
		}
	}

	@Override
	public Intent getIntent() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getIntent();
		} else {
			return that.getIntent();
		}
	}

	@Override
	public ClassLoader getClassLoader() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getClassLoader();
		} else {
			return that.getClassLoader();
		}
	}

	@Override
	public Resources getResources() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getResources();
		} else {
			return that.getResources();
		}
	}

	@Override
	public String getPackageName() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getPackageName();
		} else {
			return mPluginPackage.packageName;
		}
	}

	@Override
	public LayoutInflater getLayoutInflater() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getLayoutInflater();
		} else {
			return that.getLayoutInflater();
		}
	}

	@Override
	public MenuInflater getMenuInflater() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getMenuInflater();
		} else {
			return that.getMenuInflater();
		}
	}

	@Override
	public SharedPreferences getSharedPreferences(String name, int mode) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getSharedPreferences(name, mode);
		} else {
			return that.getSharedPreferences(name, mode);
		}
	}

	@Override
	public Context getApplicationContext() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getApplicationContext();
		} else {
			return that.getApplicationContext();
		}
	}

	@Override
	public WindowManager getWindowManager() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getWindowManager();
		} else {
			return that.getWindowManager();
		}
	}

	@Override
	public Window getWindow() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getWindow();
		} else {
			return that.getWindow();
		}
	}

	@Override
	public Object getSystemService(String name) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.getSystemService(name);
		} else {
			return that.getSystemService(name);
		}
	}

	@Override
	public void finish() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.finish();
		} else {
			that.finish();
		}
	}

	@Override
	public void onBackPressed() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onBackPressed();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	@Override
	public void onStart() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onStart();
		}
	}

	@Override
	public void onRestart() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onRestart();
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onRestoreInstanceState(savedInstanceState);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onSaveInstanceState(outState);
		}
	}

	public void onNewIntent(Intent intent) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onNewIntent(intent);
		}
	}

	@Override
	public void onResume() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onResume();
		}
	}

	@Override
	public void onPause() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onPause();
		}
	}

	@Override
	public void onStop() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onStop();
		}
	}

	@Override
	public void onDestroy() {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onDestroy();
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.onTouchEvent(event);
		}
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.onKeyUp(keyCode, event);
		}
		return false;
	}

	public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onWindowAttributesChanged(params);
		}
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			super.onWindowFocusChanged(hasFocus);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return super.onCreateOptionsMenu(menu);
		}
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (mFrom == DLConstants.FROM_INTERNAL) {
			return onOptionsItemSelected(item);
		}
		return false;
	}

	/**
	 * @param dlIntent
	 * @return may be {@link #START_RESULT_SUCCESS},
	 *         {@link #START_RESULT_NO_PKG}, {@link #START_RESULT_NO_CLASS},
	 *         {@link #START_RESULT_TYPE_ERROR}
	 */
	public int startPluginActivity(DLIntent dlIntent) {
		return startPluginActivityForResult(dlIntent, -1);
	}

	/**
	 * @param dlIntent
	 * @return may be {@link #START_RESULT_SUCCESS},
	 *         {@link #START_RESULT_NO_PKG}, {@link #START_RESULT_NO_CLASS},
	 *         {@link #START_RESULT_TYPE_ERROR}
	 */
	public int startPluginActivityForResult(DLIntent dlIntent, int requestCode) {
		if (mFrom == DLConstants.FROM_EXTERNAL) {
			if (dlIntent.getPluginPackage() == null) {
				dlIntent.setPluginPackage(mPluginPackage.packageName);
			}
		}
		return mPluginManager.startPluginActivityForResult(that, dlIntent,
				requestCode);
	}

}
