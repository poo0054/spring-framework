package com.poo0054.study.propertyEditor;

/**
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/27 11:29
 */


public class Interest {
	private String one;
	private String town;

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return "Interest{" +
				"one='" + one + '\'' +
				", town='" + town + '\'' +
				'}';
	}
}
