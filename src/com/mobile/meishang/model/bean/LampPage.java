package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class LampPage extends Head {
//	  {
	// "createTime": "2014-08-12 15:53:05",
	// "templateId": 6,
	// "moduleId": 7,
	// "lampId": 1,
	// "lampName": "开林HL-13301",
	// "lampIid": "1185421",
	// "lampWeight": "1.305kg",
	// "lampAddress": "广东佛山",
	// "lampNo": "L00034",
	// "lampStyle": "护眼台灯",
	// "lampSource": "开林",
	// "lampPower": null,
	// "updateTime": "2014-08-12 15:53:05"
	// }

	private String name;
	private String picPath;
	private String templateId;
	private String moduleId;


	public LampPage() {
	}

	public LampPage(JSONObject json) throws JSONException {
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
