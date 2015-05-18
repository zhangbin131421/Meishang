package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class Bid extends Head {
	// {
	// "biddingid": 1,
	// "title": "金秀妹夫面膜MOERMO面膜",
	// "updatetime": "2015-4-29 18:32:34",
	// "createtime": "2015-4-29 18:32:37",
	// "item": "给积分的是否合住  让您又有年轻的秘诀 快来体验吧",
	// "prodesc": "美容美发连锁创建于1990年 王勇线程装撒旦",
	// "proaddress": "鼓楼区 紫风大厦19楼",
	// "userid": 1,
	// "smoduleid": 1,
	// "moduleid": 0,
	// "smodule": {
	// "smoduleid": 1,
	// "moduleid": 3,
	// "name": "面部美容",
	// "picpath": "image/smodule/mr_mbmr.png",
	// "updatetime": "2015-4-29 18:38:51",
	// "createtime": "2015-4-29 18:38:51"
	// },
	// "state": 0
	// }
	private String biddingid;
	private String title;
	private String item;
	private String prodesc;
	private String proaddress;

	public Bid() {

	}

	public Bid(JSONObject json) throws JSONException {
		biddingid = getJsonStrValue(json, "biddingid");
		title = getJsonStrValue(json, "title");
		item = getJsonStrValue(json, "item");
		prodesc = getJsonStrValue(json, "prodesc");
		proaddress = getJsonStrValue(json, "proaddress");

	}

	public String getBiddingid() {
		return biddingid;
	}

	public String getTitle() {
		return title;
	}

	public String getItem() {
		return item;
	}

	public String getProdesc() {
		return prodesc;
	}

	public String getProaddress() {
		return proaddress;
	}

}
