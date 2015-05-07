package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class Discover extends Head {
	// "projectid": 1,
	// "title": "呵护肌肤",
	// "present": "语音介绍",
	// "lablename": "推广",
	// "middlen": "50-100",
	// "phonenum": "025-666666",
	// "count": 567,
	// "goodness": "项目优势",
	// "userid": 1,
	// "type": "护肤",
	// "state": 1,
	// "createtime": "2015-4-30 16:44:39",
	// "updatetime": "2015-4-30 16:44:39",
//	picpath
	// "introduction":
	// "项目介绍:创业者在寻找资金，同时资金也在寻找项目。\r\n\r\n　　其实，项目对资金的需要是有时机有条件的，只有万事俱备，只欠东风，具备 “优势”的时候，外部资金的注入才有意义，才能发挥作用。\r\n\r\n　　资金对项目的需要更是有时机有条件的。只有到了项目的成熟能够显现的时候，资金才需要项目，才需要这个能够使自己得以保存和增加的载体。才需要这个能够循环往复转动的机构或器物。这好比是血液需要已经存在的健康的机体，才能够保持自己并借助机体的造血功能使自己增量。资金对项目的需要,是在这个时候这样的条件下。\r\n",
	// "integral": 0
	private String projectid;
	private String title;
	private String present;
	private String lablename;
	private String middlen;
	private String phonenum;
	private String count;
	private String goodness;
	private String type;
	private String state;
	private String createtime;
	private String updatetime;
	private String picpath;
	private String introduction;

	public Discover() {

	}

	public Discover(JSONObject json) throws JSONException {
		projectid = getJsonStrValue(json, "projectid");
		title = getJsonStrValue(json, "title");
		present = getJsonStrValue(json, "present");
		lablename = getJsonStrValue(json, "lablename");
		middlen = getJsonStrValue(json, "middlen");
		phonenum = getJsonStrValue(json, "phonenum");
		count = getJsonStrValue(json, "count");
		goodness = getJsonStrValue(json, "goodness");
		type = getJsonStrValue(json, "type");
		state = getJsonStrValue(json, "state");
		createtime = getJsonStrValue(json, "createtime");
		updatetime = getJsonStrValue(json, "updatetime");
		picpath = getJsonStrValue(json, "picpath");
		introduction = getJsonStrValue(json, "introduction");

	}

	public String getProjectid() {
		return projectid;
	}

	public String getTitle() {
		return title;
	}

	public String getPresent() {
		return present;
	}

	public String getLablename() {
		return lablename;
	}

	public String getMiddlen() {
		return middlen;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public String getCount() {
		return count;
	}

	public String getGoodness() {
		return goodness;
	}

	public String getType() {
		return type;
	}

	public String getState() {
		return state;
	}

	public String getCreatetime() {
		return createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public String getPicpath() {
		return picpath;
	}

	public String getIntroduction() {
		return introduction;
	}

}
