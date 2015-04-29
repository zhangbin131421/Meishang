package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class InsideActivityData extends Head {
	// {
	// "ADVERT": [],
	// "TEMPLATE": [],
	// "TITLE": "美商云讯"
	// }
	private AdvertisingGallery advertisingGallery;

	public InsideActivityData(JSONObject json) throws JSONException {
		advertisingGallery = new AdvertisingGallery(json);

	}

	public AdvertisingGallery getAdvertisingGallery() {
		return advertisingGallery;
	}

}
