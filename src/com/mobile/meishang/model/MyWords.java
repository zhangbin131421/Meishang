package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class MyWords extends Head {

	private Discover discover;
	private List<Discover> discovers;
	private List<Picture> pictures;

	public MyWords() {

	}

	public MyWords(JSONObject json) throws JSONException {
		discover = new Discover(getJsonObject(json, "data"));
		JSONArray jsonArray = getJsonArray(json, "list");
		discovers = new ArrayList<Discover>();
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			discovers.add(new Discover(jsonArray.getJSONObject(i)));
		}
		
		JSONArray pictureArray = getJsonArray(json, "picList");
		pictures = new ArrayList<Picture>();
		int PictureLength = pictureArray.length();
		for (int i = 0; i < PictureLength; i++) {
			pictures.add(new Picture(pictureArray.getJSONObject(i)));
		}
	}

	public Discover getDiscover() {
		return discover;
	}

	public List<Discover> getDiscovers() {
		return discovers;
	}

	public List<Picture> getPictures() {
		return pictures;
	}


}
