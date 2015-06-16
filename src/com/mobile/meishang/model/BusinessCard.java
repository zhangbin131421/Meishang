package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class BusinessCard extends Head {
	// {
	// "id": 16,
	// "userId": 1,
	// "name": "袁成1",
	// "companyName": "美尚1",
	// "position": "总监1",
	// "provinceId": 3,
	// "moduleId": 3,
	// "status": 1,
	// "headerpath": "http://121.40.126.98:80/data/image/header.jpg"
	// }
	private String id;
	private String userId;
	private String name;
	private String companyName;
	private String position;
	private String provinceId;
	private String moduleId;
	private int status;
	private String headerpath;

	public BusinessCard() {

	}

	public BusinessCard(JSONObject json) throws JSONException {
		id = getJsonStrValue(json, "id");
		userId = getJsonStrValue(json, "userId");
		name = getJsonStrValue(json, "name");
		companyName = getJsonStrValue(json, "companyName");
		position = getJsonStrValue(json, "position");
		provinceId = getJsonStrValue(json, "provinceId");
		moduleId = getJsonStrValue(json, "moduleId");
		status = getJsonIntValue(json, "status");
		headerpath = getJsonStrValue(json, "headerpath");

	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getPosition() {
		return position;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public int getStatus() {
		return status;
	}

	public String getHeaderpath() {
		return headerpath;
	}

}
