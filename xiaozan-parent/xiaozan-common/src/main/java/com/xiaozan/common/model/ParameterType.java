package com.xiaozan.common.model;

public class ParameterType extends CommonMessage {

	protected String name;
	protected String value;

	public ParameterType() {

	}

	public ParameterType(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}