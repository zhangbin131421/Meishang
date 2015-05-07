package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class LehuigoHomeDataItem extends Head {
	// {
	// "purchasedid": 16,
	// "title": "保暖",
	// "integral": 0,
	// "price": 67,
	// "brand": "都市丽人",
	// "source": "自拍",
	// "no": "se2324",
	// "version": "其他",
	// "style": "可拆卸",
	// "clotheslength": "无",
	// "sleevelengt": "无",
	// "collar": "无",
	// "sleevetype": "常规",
	// "coatfront": "无",
	// "pattern": "白色",
	// "fabricmaterial": "棉质",
	// "content": "100",
	// "season": "2015冬季",
	// "color": "24324",
	// "size": "内衣",
	// "picpath": "1",
	// "moduleid": 4,
	// "smoduleid": 11,
	// "userid": 1,
	// "originalprice": 131,
	// "fw": "10-2000积分",
	// "state": 1
	// }

	private String purchasedid;
	private String title;
	private String integral;
	private String picpath;

	public LehuigoHomeDataItem() {
	}

	public LehuigoHomeDataItem(JSONObject json) throws JSONException {
		purchasedid = getJsonStrValue(json, "purchasedid");
		title = getJsonStrValue(json, "title");
		integral = getJsonStrValue(json, "integral");
		picpath = getJsonStrValue(json, "picpath");
	}

	public String getPurchasedid() {
		return purchasedid;
	}

	public String getTitle() {
		return title;
	}

	public String getIntegral() {
		return integral;
	}

	public String getPicpath() {
		return picpath;
	}

}
