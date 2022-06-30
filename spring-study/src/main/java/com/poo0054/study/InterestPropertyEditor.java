package com.poo0054.study;

import java.beans.PropertyEditorSupport;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/27 11:26
 */

public class InterestPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null) {
			final String[] split = text.split(",");
			Interest interest = new Interest();
			interest.setOne(split[0]);
			interest.setTown(split[1]);
			setValue(interest);
		}

	}
}
