package com.decade.agile.components;

import android.view.View;

public interface DCiDialog {
	public View create(DCBaseDialogParams params);
	public DCBaseDialogParams getParams();
	public void refresh();  // 刷新UI
	public void open();
	public void close();
}
