package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class User extends Head {
	// {
	// "message": "登录成功",
	// "result": 1,
	// "user": {
	// "userid": 11,
	// "password": "e10adc3949ba59abbe56e057f20f883e",
	// "telephone": "13776636043",
	// "status": 0,
	// "integral": 60,
	// "headerpath": "image/header.jpg"
	// }
	// }

	private String userId;
	private String telephone;
	private String integral;
	private String headerpath;
	private String userName;
	private String nickname;

	public User() {
	}

	public User(JSONObject json) throws JSONException {
		super(json);
		JSONObject userJsonObject = getJsonObject(json, "user");
		headerpath = getJsonStrValue(userJsonObject, "headerpath");
		userName = getJsonStrValue(userJsonObject, "userName");
		userId = getJsonStrValue(userJsonObject, "userid");
		telephone = getJsonStrValue(userJsonObject, "telephone");
		nickname = getJsonStrValue(userJsonObject, "nickname");
		integral = getJsonStrValue(userJsonObject, "integral");

	}

	public String getHeaderpath() {
		return headerpath;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getNickname() {
		return nickname;
	}

	public String getIntegral() {
		return integral;
	}

}
