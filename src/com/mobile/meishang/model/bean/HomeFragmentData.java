package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragmentData extends Head {
	// {
	// "ADVERT": [],
	// "TEMPLATE": [],
	// "TITLE": "美商云讯"
	// }
	private AdvertisingGallery advertisingGallery;
	private HomeFragmentTemplateData templateData;

	public HomeFragmentData(JSONObject json) throws JSONException {
		advertisingGallery = new AdvertisingGallery(json);
		templateData = new HomeFragmentTemplateData(json);

	}

	public AdvertisingGallery getAdvertisingGallery() {
		return advertisingGallery;
	}

	public HomeFragmentTemplateData getTemplateData() {
		return templateData;
	}

}
