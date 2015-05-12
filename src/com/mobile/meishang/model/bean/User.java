package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class User extends Head {
	// {
	// "message": "",
	// "RESULT": "0",
	// "user": {
	// "header": "http://121.43.224.225:8888/data",
	// "userName": "",
	// "userId": 0,
	// "telephone": "13776636043",
	// "userPass": "",
	// "createtime": "",
	// "updatetime": "",
	// "nickname": "",
	// "integral": "0"
	// }
	// }

	private String header;
	private String userName;
	private String userId;
	private String telephone;
	private String nickname;
	private String integral;

	public User() {
	}

	public User(JSONObject json) throws JSONException {
		super(json);
		JSONObject userJsonObject = getJsonObject(json, "user");
		header = getJsonStrValue(userJsonObject, "header");
		userName = getJsonStrValue(userJsonObject, "userName");
		userId = getJsonStrValue(userJsonObject, "userid");
		telephone = getJsonStrValue(userJsonObject, "telephone");
		nickname = getJsonStrValue(userJsonObject, "nickname");
		integral = getJsonStrValue(userJsonObject, "integral");

	}

	public String getHeader() {
		return header;
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
