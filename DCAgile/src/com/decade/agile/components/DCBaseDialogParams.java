package com.decade.agile.components;


/**
 * @description: 对话框属性
 * @author: Decade
 * @date: 2013-9-16
 * @preserve protected
 */
public class DCBaseDialogParams{
	
	private String _title;
	private int _topViewBgResId;
	private int _topViewBgColor;
	private int _dialogBgResId;
	private int _bottomViewBgResId;
	private String _content;

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getTopViewBgResId() {
		return _topViewBgResId;
	}

	public void setTopViewBgResId(int topViewBgResId) {
		_topViewBgResId = topViewBgResId;
	}

	public int getDialogBgResId() {
		return _dialogBgResId;
	}

	public void setDialogBgResId(int dialogBgResId) {
		_dialogBgResId = dialogBgResId;
	}

	public int getBottomViewBgResId() {
		return _bottomViewBgResId;
	}

	public void setBottomViewBgResId(int bottomViewBgResId) {
		_bottomViewBgResId = bottomViewBgResId;
	}

	public int getTopViewBgColor() {
		return _topViewBgColor;
	}

	public void setTopViewBgColor(int topViewBgColor) {
		_topViewBgColor = topViewBgColor;
	}
	
	

}
