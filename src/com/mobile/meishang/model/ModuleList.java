package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class ModuleList extends Head {
	private List<Module> moduleList;

	public ModuleList() {
	}

	public ModuleList(JSONObject json) throws JSONException {
		moduleList = new ArrayList<Module>();
		JSONArray jsonArray = getJsonArray(json, "list");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			moduleList.add(new Module(jsonArray.getJSONObject(i)));
		}
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

}
