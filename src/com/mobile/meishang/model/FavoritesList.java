package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class FavoritesList extends Head {
	private List<LehuigoDetailData> lehuigoDetailDatas;
	private List<Discover> discovers;
	private List<Infomation> infomations;

	public FavoritesList() {
	}

	public FavoritesList(JSONObject json) throws JSONException {
		lehuigoDetailDatas = new ArrayList<LehuigoDetailData>();
		discovers = new ArrayList<Discover>();
		infomations = new ArrayList<Infomation>();
		JSONArray jsonArray = getJsonArray(json, "inList");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				lehuigoDetailDatas.add(new LehuigoDetailData(jsonArray
						.getJSONObject(i)));
			}
		}
		JSONArray jsonArray2 = getJsonArray(json, "proList");
		if (jsonArray2 != null) {
			int length = jsonArray2.length();
			for (int i = 0; i < length; i++) {
				discovers.add(new Discover(jsonArray2.getJSONObject(i)));
			}
		}
		JSONArray jsonArray3 = getJsonArray(json, "infoList");
		if (jsonArray3 != null) {
			int length = jsonArray3.length();
			for (int i = 0; i < length; i++) {
				infomations.add(new Infomation(jsonArray3.getJSONObject(i)));
			}
		}
	}

	public List<LehuigoDetailData> getLehuigoDetailDatas() {
		return lehuigoDetailDatas;
	}

	public List<Discover> getDiscovers() {
		return discovers;
	}

	public List<Infomation> getInfomations() {
		return infomations;
	}

}
