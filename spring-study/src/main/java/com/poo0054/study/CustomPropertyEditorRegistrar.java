package com.poo0054.study;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/27 11:25
 */

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Interest.class, new InterestPropertyEditor());
	}
}
