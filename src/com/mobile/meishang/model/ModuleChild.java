package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class ModuleChild extends Head {
//	{
//	    "smoduleid": 1,
//	    "moduleid": 3,
//	    "name": "面部美容",
//	    "picpath": "http://121.40.126.98:80/data/image/smodule/mr_mbmr.png",
//	    "updatetime": "2015-4-29 18:38:51",
//	    "createtime": "2015-4-29 18:38:51"
//	}

	private String smoduleid;
	private String moduleid;
	private String name;
	private String picpath;

	public ModuleChild() {
	}

	public ModuleChild(JSONObject json) throws JSONException {
		smoduleid = getJsonStrValue(json, "smoduleid");
		moduleid = getJsonStrValue(json, "moduleid");
		name = getJsonStrValue(json, "name");
		picpath = getJsonStrValue(json, "picpath");
	}

	public String getSmoduleid() {
		return smoduleid;
	}

	public String getModuleid() {
		return moduleid;
	}

	public String getName() {
		return name;
	}

	public String getPicpath() {
		return picpath;
	}

}
