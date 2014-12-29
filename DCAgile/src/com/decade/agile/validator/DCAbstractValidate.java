package com.decade.agile.validator;


import android.widget.TextView;

public abstract class DCAbstractValidate {

	/**
	 * Add a new validator for fields attached
	 * @param validator
	 * 		{@link DCAbstractValidator} : The validator to attach
	 */
	public abstract void addValidator(DCAbstractValidator validator);
	
	/**
	 * Function called when the {@link DCForm} validation
	 * @param value
	 * 		{@link String} : value to validate
	 * @return
	 * 		true if all validators are valid
     *      false if a validator is invalid
	 */
	public abstract boolean isValid(String value);
	
	/**
	 * Returns the error message displayed on the connected component
	 * @return
	 * 		{@link String} : the message to display
	 */
	public abstract String getMessages();
	
	/**
	 * Function recovering the field attached to our validator
	 * @return
	 * 		{@link TextView} : The fields attached
	 */
	public abstract TextView getSource();
}
