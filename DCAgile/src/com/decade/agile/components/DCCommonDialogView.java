package com.decade.agile.components;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.decade.agile.R;

/**
 * @description: 一般提示对话框
 * @author: Decade
 * @date: 2013-5-6
 */

public class DCCommonDialogView extends DCBaseDialogView {

	public TextView prompt_content;
	public DCPromptDialogParams _params;

	public DCCommonDialogView(Context context, DCPromptDialogParams params) {
		super(context);
		_params = params;
		create(params);
	}

	protected void addToCenterView(LinearLayout layout) {
		View center = View.inflate(_context, R.layout.agile_common_center_view,
				layout);

		prompt_content = (TextView) center.findViewById(R.id.prompt_content);
		if (!TextUtils.isEmpty(_params.getContent())) {
			if (_params.getContent().contains("\n")) {
				prompt_content.setText(_params.getContent());

			} else if (_params.getContent().contains("<br>")) {
				prompt_content.setText(Html.fromHtml(_params.getContent()));
			} else {
				prompt_content.setText(_params.getContent());
			}
			if (_params.getContent().length() < 30) {
				LayoutParams params = new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				params.gravity = Gravity.CENTER_VERTICAL;
				prompt_content.setLayoutParams(params);
			}
		}
	}

	private void setButton(String str, final Button button) {
		if (!TextUtils.isEmpty(str)) {
			button.setVisibility(View.VISIBLE);
			button.setText(str);
			button.setOnClickListener(new Button.OnClickListener() {
				@Override
				public void onClick(View v) {

					if (_params.getPromptBtnCallback() != null) {
						if (button.getId() == R.id.prompt_left_bt) {
							_params.getPromptBtnCallback().onClick(
									DCCommonDialogView.this, button,
									Position.LEFT, _params.getEventCode());
						} else if (button.getId() == R.id.prompt_center_bt) {
							_params.getPromptBtnCallback().onClick(
									DCCommonDialogView.this, button,
									Position.CENTER, _params.getEventCode());
						} else if (button.getId() == R.id.prompt_right_bt) {
							_params.getPromptBtnCallback().onClick(
									DCCommonDialogView.this, button,
									Position.RIGHT, _params.getEventCode());
						}
					}
				}
			});
		}
	}

	public Dialog getDialog() {
		return this;
	}

	public TextView getContentTextView() {
		return prompt_content;
	}

	/**
	 * 此方法可用来更新对话框内容提示
	 */
	@Override
	public void refresh() {
		prompt_content.setText(Html.fromHtml(_params.getContent()));
	}

	@Override
	protected void addToTopView(LinearLayout layout) {
		View top = View.inflate(_context, R.layout.agile_common_top_view,
				layout);
		TextView prompt_title = (TextView) top.findViewById(R.id.prompt_title);
		if (!TextUtils.isEmpty(_params.getTitle())) {
			prompt_title.setText(_params.getTitle());
		} else {
			layout.setVisibility(View.GONE);
		}
	}

	@Override
	protected void addToBottomView(LinearLayout layout) {
		View bottom = View.inflate(_context, R.layout.agile_common_bottom_view,
				layout);
		Button prompt_left_bt = (Button) bottom
				.findViewById(R.id.prompt_left_bt);
		Button prompt_center_bt = (Button) bottom
				.findViewById(R.id.prompt_center_bt);
		Button prompt_right_bt = (Button) bottom
				.findViewById(R.id.prompt_right_bt);

		if (_params.getBtnTextSize() != 0) {
			prompt_left_bt.setTextSize(_params.getBtnTextSize());
			prompt_center_bt.setTextSize(_params.getBtnTextSize());
			prompt_right_bt.setTextSize(_params.getBtnTextSize());
		}
		if (_params.getLeftBgColor() != 0) {
			prompt_left_bt.setBackgroundColor(_params.getLeftBgColor());
		}
		if (_params.getLeftBgResId() != 0) {
			prompt_left_bt.setBackgroundResource(_params.getLeftBgResId());
		}

		if (_params.getCenterBgColor() != 0) {
			prompt_center_bt.setBackgroundColor(_params.getCenterBgColor());
		}
		if (_params.getCenterBgResId() != 0) {
			prompt_center_bt.setBackgroundResource(_params.getCenterBgResId());
		}

		if (_params.getRightBgColor() != 0) {
			prompt_right_bt.setBackgroundColor(_params.getRightBgColor());
		}
		if (_params.getRightBgResId() != 0) {
			prompt_right_bt.setBackgroundResource(_params.getRightBgResId());
		}

		if (_params.getLeftTextColor() != 0) {
			prompt_left_bt.setTextColor(_params.getLeftTextColor());
		}
		if (_params.getCenterTextColor() != 0) {
			prompt_center_bt.setTextColor(_params.getCenterTextColor());
		}
		if (_params.getRightTextColor() != 0) {
			prompt_right_bt.setTextColor(_params.getRightTextColor());
		}
		if (TextUtils.isEmpty(_params.getLeftBtnText())
				&& TextUtils.isEmpty(_params.getCenterBtnText())
				&& TextUtils.isEmpty(_params.getrRightBtnText())) {
			layout.setVisibility(View.GONE);
			return;
		}
		setButton(_params.getLeftBtnText(), prompt_left_bt);
		setButton(_params.getCenterBtnText(), prompt_center_bt);
		setButton(_params.getrRightBtnText(), prompt_right_bt);

	}

	@Override
	public DCBaseDialogParams getParams() {
		return _params;
	}

}
