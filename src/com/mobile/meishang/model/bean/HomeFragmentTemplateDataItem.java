package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeFragmentTemplateDataItem extends Head {
	// {
	// "picPath": "http://121.41.38.198:8888/data/ImageFile/log/l_l_1.jpg",
	// "templateId": 1,
	// "title": "乐汇购",
	// "text": "乐汇购模块"
	// }

	private String picPath;
	private String templateId;
	private String title;

	// private String templateId;
	// private String advertId;
	// private String title;
	// private String text;

	public HomeFragmentTemplateDataItem() {
	}

	public HomeFragmentTemplateDataItem(JSONObject json) throws JSONException {
		picPath = getJsonStrValue(json, "picPath");
		templateId = getJsonStrValue(json, "templateId");
		title = getJsonStrValue(json, "title");
	}

	public String getPicPath() {
		return picPath;
	}

	public String getTemplateId() {
		return templateId;
	}

	public String getTitle() {
		return title;
	}

}
