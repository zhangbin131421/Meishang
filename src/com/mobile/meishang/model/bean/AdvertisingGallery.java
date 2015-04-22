package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvertisingGallery extends Head {
	private List<AdvertisingGalleryItem> list;

	public AdvertisingGallery(JSONObject json) throws JSONException {
		JSONArray jsonArray = getJsonArray(json, "ADVERT");
		int length = jsonArray.length();
		list = new ArrayList<AdvertisingGalleryItem>();
		for (int i = 0; i < length; i++) {
			list.add(new AdvertisingGalleryItem(jsonArray.getJSONObject(i)));
		}
	}

	public List<AdvertisingGalleryItem> getList() {
		return list;
	}

}
