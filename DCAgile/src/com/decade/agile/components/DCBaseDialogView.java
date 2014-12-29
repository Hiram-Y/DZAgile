package com.decade.agile.components;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.decade.agile.R;
import com.decade.framework.DCApplication;

/**
 * @description: 基础对话框
 * @author: Decade
 * @date: 2013-5-6
 */

public abstract class DCBaseDialogView extends DCDialog {

	public Context _context;
	public View _baseView;

	public DCBaseDialogView(Context context) {
		super(context, R.style.notitle_dialog_style);
		_context = context;
	}

	@Override
	public View create(DCBaseDialogParams params) {
		_baseView = View.inflate(_context, R.layout.agile_base_dialog_view,
				null);
		LinearLayout top_layout = (LinearLayout) _baseView
				.findViewById(R.id.base_dialog_top_layout);
		LinearLayout center_layout = (LinearLayout) _baseView
				.findViewById(R.id.base_dialog_center_layout);
		LinearLayout bottom_layout = (LinearLayout) _baseView
				.findViewById(R.id.base_dialog_bottom_layout);

		if (params.getDialogBgResId() != 0) {
			_baseView.setBackgroundResource(params.getDialogBgResId());
		}
		if (params.getTopViewBgColor() != 0) {
			top_layout.setBackgroundColor(params.getTopViewBgColor());
		}
		if (params.getTopViewBgResId() != 0) {
			top_layout.setBackgroundResource(params.getTopViewBgResId());
		}
		if (params.getBottomViewBgResId() != 0) {
			bottom_layout.setBackgroundResource(params.getBottomViewBgResId());
		}

		addToTopView(top_layout);
		addToCenterView(center_layout);
		addToBottomView(bottom_layout);
		setContentView(_baseView);
		setDialogSize(getDialogWidth(),getDialogHeight());
		return _baseView;
	}

	/**
	 * 设置对话框的大小
	 * 
	 * @param width
	 *            为0时，设置默认值， 默认值为屏幕宽度的 十三分之一
	 * @param height
	 *            为0时 设置默认值 ，默认值根据布局大小变化
	 */
	public void setDialogSize(int width,int height) {
		Window w = getWindow();
		WindowManager.LayoutParams wl = w.getAttributes();
		if(width != 0){
			wl.width = width;
		}else {
			wl.width = getDialogWidth();
		}
		if (height != 0) {
			wl.height = height;
		}
		w.setAttributes(wl);
	}

	protected int getDialogWidth() {
		return DCApplication.getApp().getWorkSpaceWidth() / 13 * 11;
	}

	protected int getDialogHeight() {
		return DCApplication.getApp().getWorkSpaceHeight() / 4 * 1;
	}

	protected abstract void addToTopView(LinearLayout layout);

	protected abstract void addToCenterView(LinearLayout layout);

	protected abstract void addToBottomView(LinearLayout layout);

}
