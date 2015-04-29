package com.decade.agile.validator;

import android.content.Context;
import android.widget.EditText;

/**
 * 
 * @description: 表单验证 工具类
 * @author: Decade
 * @date: 2013-9-12
 * 
 */
public class DCFormValidate {
	private Context _context;
	private DCForm _form;

	public DCFormValidate(Context context) {
		_context = context;
		_form = new DCForm();
	}

	/**
	 * 手机号码验证
	 * 
	 * @param editText
	 */
	public void addMobileValidator(EditText editText, int emptyMsg,
			int formErrorMsg) {
		DCMobileValidator validator = null;
		DCValidate validate = new DCValidate(editText);
		if (emptyMsg == 0 && formErrorMsg == 0) {
			validator = new DCMobileValidator(_context);
		} else {
			validator = new DCMobileValidator(_context, emptyMsg, formErrorMsg);
		}
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * 非空验证
	 * 
	 * @param editText
	 * @param promptResId
	 */
	public void addNotEmptyValidator(EditText edit, int promptResId) {
		DCValidate validate = new DCValidate(edit);
		DCNotEmptyValidator validator = new DCNotEmptyValidator(_context,
				promptResId);
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * 二次确认验证
	 * 
	 * @param edit
	 * @param confirmEdit
	 * @param emptyMsgId
	 * @param errorMsgId
	 */
	public void addConfirmValidate(EditText edit, EditText confirmEdit,
			int errorMsgId) {
		DCConfirmValidate validate = new DCConfirmValidate(_context, edit,
				confirmEdit, errorMsgId);
		_form.addValidates(validate);
	}

	/**
	 * 只允许输入字符+数字
	 * 
	 * @param edit
	 * @param confirmEdit
	 * @param errorMsgId
	 */
	public void addAlnumValidator(EditText edit, EditText confirmEdit,
			int errorMsgId) {
		DCValidate validate = new DCValidate(edit);
		DCAlnumValidator validator = new DCAlnumValidator(_context, errorMsgId);
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * 邮箱验证
	 * 
	 * @param edit
	 * @param confirmEdit
	 * @param errorMsgId
	 */
	public void addEmailValidator(EditText edit, EditText confirmEdit,
			int errorMsgId) {
		DCValidate validate = new DCValidate(edit);
		DCEmailValidator validator = new DCEmailValidator(_context, errorMsgId);
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * URL验证
	 * 
	 * @param edit
	 * @param confirmEdit
	 * @param errorMsgId
	 */
	public void addUrlValidator(EditText edit, EditText confirmEdit,
			int errorMsgId) {
		DCValidate validate = new DCValidate(edit);
		DCUrlValidator validator = new DCUrlValidator(_context, errorMsgId);
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * 使用正则表达式验证
	 * 
	 * @param editText
	 * @param pattern
	 *            正则表达式
	 * @param promptResId
	 */
	public void add(EditText editText, String pattern, int promptResId) {
		DCValidate validate = new DCValidate(editText);
		DCRegExpValidator regExpValidator = new DCRegExpValidator(_context,
				promptResId);
		regExpValidator.setPattern(pattern);
		validate.addValidator(regExpValidator);
		_form.addValidates(validate);
	}

	/**
	 * 表单验证
	 * 
	 * @return true 表示通过，false 不通过
	 */
	public boolean isValidate() {
		return _form.validate();
	}

	public DCForm getForm() {
		return _form;
	}

}
