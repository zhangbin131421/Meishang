package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

public class LehuigoHomeGroup {
	private String id;
	private String title;
	private List<LehuigoHomeDataItem> list=new ArrayList<LehuigoHomeDataItem>();

	public LehuigoHomeGroup() {

	}

	public LehuigoHomeGroup(String title, List<LehuigoHomeDataItem> toplist) {
		this.title = title;
		this.list = toplist;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<LehuigoHomeDataItem> getList() {
		return list;
	}

}
