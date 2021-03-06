package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.Discover;
import com.mobile.meishang.model.LehuigoHomeDataItem;
import com.mobile.meishang.model.ModuleChild;

public class InsideActivityData extends Head {
	private AdvertisingGallery advertisingGallery;
	private List<ModuleChild> smodules;
	private List<Discover> list;

	public InsideActivityData(JSONObject json) throws JSONException {
		advertisingGallery = new AdvertisingGallery(json);
		smodules = new ArrayList<ModuleChild>();
		JSONArray smoduleArray = getJsonArray(json, "smoduleList");
		if (smoduleArray != null) {
			int length = smoduleArray.length();
			for (int i = 0; i < length; i++) {
				smodules.add(new ModuleChild(smoduleArray.getJSONObject(i)));
			}
		}
		JSONArray topArray = getJsonArray(json, "list");
		list = new ArrayList<Discover>();
		if (topArray != null) {
			int topLength = topArray.length();
			for (int i = 0; i < topLength; i++) {
				list.add(new Discover(topArray.getJSONObject(i)));
			}

		}

	}

	public AdvertisingGallery getAdvertisingGallery() {
		return advertisingGallery;
	}

	public List<ModuleChild> getSmodules() {
		return smodules;
	}

	public List<Discover> getList() {
		return list;
	}

}
