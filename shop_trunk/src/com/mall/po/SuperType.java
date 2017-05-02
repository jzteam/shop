package com.mall.po;

import java.io.Serializable;

public class SuperType implements Serializable{
	private static final long serialVersionUID = 1L;

	private int superTypeId;

	private String typeName;

	public int getSuperTypeId() {
		return superTypeId;
	}

	public void setSuperTypeId(int superTypeId) {
		this.superTypeId = superTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
