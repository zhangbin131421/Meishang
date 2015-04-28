package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Module extends Head {
//	{
//	    "name": "低压灯",
//	    "picPath": null,
//	    "templateId": 6,
//	    "text": "低压灯",
//	    "moduleId": 1
//	}

	private String name;
	private String picPath;
	private String templateId;
	private String moduleId;


	public Module() {
	}

	public Module(JSONObject json) throws JSONException {
		name = getJsonStrValue(json, "name");
		picPath = getJsonStrValue(json, "picPath");
		templateId = getJsonStrValue(json, "templateId");
		moduleId = getJsonStrValue(json, "moduleId");
	}

	public String getPicPath() {
		return picPath;
	}

	public String getTemplateId() {
		return templateId;
	}

	public String getName() {
		return name;
	}

	public String getModuleId() {
		return moduleId;
	}


}
