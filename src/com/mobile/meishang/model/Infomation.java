package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class Infomation extends Head {
	// {
	// "infoid": 1,
	// "context": "西部首轮：灰熊 4-1 开拓者\r\n\r\n西部首轮再次决出一队胜者：
	// "picpath": "image/pur/lgh.png",
	// "createtime": "2015-4-30 17:38:23",
	// "updatetime": "2015-4-30 17:38:23",
	// "title": "篮球大战",
	// "count": 78,
	// "moduleid": 7,
	// "smoduleid": 25
	// }
	private String infoid;
	private String context;
	private String picpath;
	private String createtime;
	private String updatetime;
	private String title;
	private String count;
	private String moduleid;
	private String smoduleid;

	public Infomation() {

	}

	public Infomation(JSONObject json) throws JSONException {
		infoid = getJsonStrValue(json, "infoid");
		context = getJsonStrValue(json, "context");
		picpath = getJsonStrValue(json, "picpath");
		createtime = getJsonStrValue(json, "createtime");
		updatetime = getJsonStrValue(json, "updatetime");
		title = getJsonStrValue(json, "title");
		count = getJsonStrValue(json, "count");
		moduleid = getJsonStrValue(json, "moduleid");
		smoduleid = getJsonStrValue(json, "smoduleid");

	}

	public String getInfoid() {
		return infoid;
	}

	public String getContext() {
		return context;
	}

	public String getPicpath() {
		return picpath;
	}

	public String getCreatetime() {
		return createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public String getTitle() {
		return title;
	}

	public String getCount() {
		return count;
	}

	public String getModuleid() {
		return moduleid;
	}

	public String getSmoduleid() {
		return smoduleid;
	}

}
