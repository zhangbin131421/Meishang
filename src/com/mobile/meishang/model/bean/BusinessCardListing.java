package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BusinessCardListing extends Head {
	// {
	// "result": "1",
	// "message": "",
	// "data": [{
	// "moduleid": 3,
	// "modulename": "美容",
	// "modulepicpath": "image/module/mr.png",
	// "moduleurl": "purchased/list/looks/list.htm",
	// "updatetime": "2015-4-29 18:15:56",
	// "createtime": "2015-4-29 18:50:56",
	// "state": 1 1、我没有名片不可以发交换名片请求
	// 2、未发送交换名片请求3、已发送 交换名片请求、4、交换名片成功 并保存到朋友圈中去、已经是朋友圈中的如果有可以直接查看对方名片 默认为1 }]
	// }

	private List<BusinessCardItem> list;

	public BusinessCardListing() {
	}

	public BusinessCardListing(JSONObject json) throws JSONException {
		super(json);
		JSONArray jsonArray = getJsonArray(json, "data");
		int length = jsonArray.length();
		list = new ArrayList<BusinessCardItem>();
		for (int i = 0; i < length; i++) {
			list.add(new BusinessCardItem(jsonArray.getJSONObject(i)));
		}
	}

	public List<BusinessCardItem> getList() {
		return list;
	}

}
