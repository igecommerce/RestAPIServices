package com.gaia.web.rest.vm;

public class BreadCrumb {

	private Long categoryId;
	private String name;

	public BreadCrumb(Long categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return categoryId + ":" + name;
	}

}
