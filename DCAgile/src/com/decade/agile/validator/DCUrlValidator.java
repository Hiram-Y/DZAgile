package com.decade.agile.validator;

import android.content.Context;
import android.webkit.URLUtil;

public class DCUrlValidator extends DCAbstractValidator {
	private int _errorMessage;

	public DCUrlValidator(Context c, int errorMessage) {
		super(c);
		_errorMessage = errorMessage;
	}

	@Override
	public boolean isValid(String url) {
		if (url.length() > 0) {
			if (URLUtil.isValidUrl(url))
				return true;
			else
				return false;
		} else {
			return true;
		}
	}

	@Override
	public String getMessage() {
		return _context.getString(_errorMessage);
	}

}
