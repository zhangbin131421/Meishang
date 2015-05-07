package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.LehuigoHomeDataItem;
import com.mobile.meishang.model.Smodule;

public class InsideActivityData extends Head {
	private AdvertisingGallery advertisingGallery;
	private List<Smodule> smodules;
	private List<LehuigoHomeDataItem> list;

	public InsideActivityData(JSONObject json) throws JSONException {
		advertisingGallery = new AdvertisingGallery(json);
		smodules = new ArrayList<Smodule>();
		JSONArray smoduleArray = getJsonArray(json, "smoduleList");
		if (smoduleArray != null) {
			int length = smoduleArray.length();
			for (int i = 0; i < length; i++) {
				smodules.add(new Smodule(smoduleArray.getJSONObject(i)));
			}
		}
		JSONArray topArray = getJsonArray(json, "list");
		list = new ArrayList<LehuigoHomeDataItem>();
		if (topArray != null) {
			int topLength = topArray.length();
			for (int i = 0; i < topLength; i++) {
				list.add(new LehuigoHomeDataItem(topArray.getJSONObject(i)));
			}

		}

	}

	public AdvertisingGallery getAdvertisingGallery() {
		return advertisingGallery;
	}

	public List<Smodule> getSmodules() {
		return smodules;
	}

	public List<LehuigoHomeDataItem> getList() {
		return list;
	}


}
