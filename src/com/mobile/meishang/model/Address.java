package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class Address extends Head {
	// {
	// "receiptid": 1,
	// "name": "程东",
	// "phone": "1354326289",
	// "post": "224000",
	// "address": "北京市昭阳区",
	// "addresss": "北京市昭阳区24栋202",
	// "userid": 1,
	// "updatetime": "2015-5-1 11:16:20",
	// "createtime": "2015-5-1 11:16:20"
	// }

	private String name;
	private String phone;
	private String post;
	private String addresss;

	public Address() {
	}

	public Address(JSONObject json) throws JSONException {
		name = getJsonStrValue(json, "name");
		phone = getJsonStrValue(json, "phone");
		post = getJsonStrValue(json, "post");
		addresss = getJsonStrValue(json, "addresss");

	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getPost() {
		return post;
	}

	public String getAddresss() {
		return addresss;
	}

}
