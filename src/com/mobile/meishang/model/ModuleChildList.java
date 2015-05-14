package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class ModuleChildList extends Head {
	private List<ModuleChild> moduleList;

	public ModuleChildList() {
	}

	public ModuleChildList(JSONObject json) throws JSONException {
		moduleList = new ArrayList<ModuleChild>();
		JSONArray jsonArray = getJsonArray(json, "moduleList");
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			moduleList.add(new ModuleChild(jsonArray.getJSONObject(i)));
		}
	}

	public List<ModuleChild> getModuleList() {
		return moduleList;
	}

}
