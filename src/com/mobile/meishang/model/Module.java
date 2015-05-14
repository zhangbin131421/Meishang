package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class Module extends Head {
	// "moduleid": 3,
	// "modulename": "美容",
	// "modulepicpath": "image/module/mr.png",
	// "moduleurl": "purchased/list/looks/list.htm",
	// "updatetime": "2015-4-29 18:15:56",
	// "createtime": "2015-4-29 18:50:56",
	// "smoduleList": []

	private String moduleid;
	private String modulename;
	private String modulepicpath;
	private String moduleurl;
	private List<ModuleChild> list;

	public Module() {
	}

	public Module(JSONObject json) throws JSONException {
		moduleid = getJsonStrValue(json, "moduleid");
		modulename = getJsonStrValue(json, "modulename");
		modulepicpath = getJsonStrValue(json, "modulepicpath");
		moduleurl = getJsonStrValue(json, "moduleurl");
		list = new ArrayList<ModuleChild>();
		JSONArray jsonArray = getJsonArray(json, "smoduleList");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new ModuleChild(jsonArray.getJSONObject(i)));
			}
		}
	}

	public String getModuleid() {
		return moduleid;
	}

	public String getModulename() {
		return modulename;
	}

	public String getModulepicpath() {
		return modulepicpath;
	}

	public String getModuleurl() {
		return moduleurl;
	}

	public List<ModuleChild> getList() {
		return list;
	}

}
