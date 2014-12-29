package com.decade.agile.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.decade.agile.R;

public class DCRectDialog extends DCDialog{

	private Context _context;
	private DCBaseDialogParams _params;
	private TextView _content_tv;

	private static DCiDialog _dialog;

	private DCRectDialog(Context context, DCBaseDialogParams params) {
		super(context, R.style.CustomProgressDialog);
		_context = context;
		_params = params;
		 create(params);
	}

	public static DCiDialog getInstance(Context context,
			DCBaseDialogParams params) {
		if (_dialog == null) {
			return new DCRectDialog(context, params);
		}
		return _dialog;
	}

	@Override
	public View create(DCBaseDialogParams params) {
		View view = LayoutInflater.from(_context).inflate(
				R.layout.agile_rect_dialog_view, null);
		_content_tv = (TextView) view.findViewById(R.id.agile_tips_loading_msg_tv);
		_content_tv.setText(_params.getContent());
		setContentView(view);
		setCanceledOnTouchOutside(false);
		return view;
	}

	@Override
	public DCBaseDialogParams getParams() {
		return _params;
	}

	@Override
	public void refresh() {
		_content_tv.setText(_params.getContent());
	}

}
