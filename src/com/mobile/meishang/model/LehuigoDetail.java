package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class LehuigoDetail extends Head {

	private LehuigoDetailData data;
	private List<Picture> pictures;

	public LehuigoDetail() {

	}

	public LehuigoDetail(JSONObject json) throws JSONException {
		data = new LehuigoDetailData(getJsonObject(json, "data"));
		JSONArray pictureArray = getJsonArray(json, "list");
		pictures = new ArrayList<Picture>();
		int PictureLength = pictureArray.length();
		for (int i = 0; i < PictureLength; i++) {
			pictures.add(new Picture(pictureArray.getJSONObject(i)));
		}
	}

	public LehuigoDetailData getData() {
		return data;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

}
