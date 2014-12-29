package com.decade.framework.async;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-6
 * @preserve protected
 */
public class DCAsyncTaskParams {
	private static final int DEFAULT_REQUESTCODE = -10000;
	private DCiAsyncTaskCallback _event;
	private DCiResponse _parse;
	private int _requestCode = DEFAULT_REQUESTCODE;
	private DCiCacheType _cacheType;;
	private boolean _openPrompt;
	private boolean _closePrompt;
	private String _promptContent;
	private boolean _encrypt;

	public DCAsyncTaskParams(DCiAsyncTaskCallback event, DCiResponse parse,
			DCiCacheType cacheType, int requestCode) {
		_event = event;
		_parse = parse;
		_cacheType = cacheType;
		_requestCode = requestCode;
		init(cacheType);
	}

	public void init(DCiCacheType cacheType) {
		if (cacheType == null) {
			_cacheType = new DCNonuseCache();
		}
		setOpenPrompt(true);
		setClosePrompt(true);
		setEncrypt(false);
	}

	public DCiAsyncTaskCallback getEvent() {
		return _event;
	}

	public void setEvents(DCiAsyncTaskCallback event) {
		_event = event;
	}

	public DCiResponse getParse() {
		return _parse;
	}

	public void setParse(DCiResponse parse) {
		_parse = parse;
	}

	public int getRequestCode() {
		return _requestCode;
	}

	public void setRequestCode(int requestCode) {
		_requestCode = requestCode;
	}

	public DCiCacheType getCacheType() {
		return _cacheType;
	}

	public void setCacheType(DCiCacheType cacheType) {
		_cacheType = cacheType;
	}

	public boolean isOpenPrompt() {
		return _openPrompt;
	}

	public void setOpenPrompt(boolean openPrompt) {
		_openPrompt = openPrompt;
	}

	public boolean isClosePrompt() {
		return _closePrompt;
	}

	public void setClosePrompt(boolean closePrompt) {
		_closePrompt = closePrompt;
	}

	public String getPromptContent() {
		return _promptContent;
	}

	public void setPromptContent(String promptContent) {
		_promptContent = promptContent;
	}

	public boolean isEncrypt() {
		return _encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		_encrypt = encrypt;
	}

}
