package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Head {
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
	private User user;

	public Login() {

	}

	public Login(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		user = new User(getJsonObject(jsonObject, "user"));

	}

	public User getUser() {
		return user;
	}

}
