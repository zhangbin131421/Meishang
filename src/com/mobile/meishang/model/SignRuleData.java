package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class SignRuleData extends Head {
//	{
//	    "ruleid": 1,
//	    "userid": 1,
//	    "content": "积分是您在美商APP进行签到游戏等操作时获得的通用积分拥有积分后您不仅可以再积分商城常与幸运抽奖,疯狂竞拍和积分兑换等活动换取礼品还可以使用积分低订单金额:1新注册会员即送200积分2连续签到的积分依次为：5,10,15,20,25,30之后每天签到都为30积分3注：连续签到期间如果中断领取将冲第一天开始计算",
//	    "title": "签到领积分说明",
//	    "type": "1",
//	    "updatetime": "2015-4-30 22:50:21",
//	    "createtime": "2015-4-30 22:50:21"
//	}
	private String  title;
	private String  content;

	public SignRuleData() {

	}

	public SignRuleData(JSONObject json) throws JSONException {
		title=getJsonStrValue(json, "title");
		content=getJsonStrValue(json, "content");
		
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}



}
