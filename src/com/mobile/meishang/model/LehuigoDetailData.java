package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.meishang.model.bean.Head;

public class LehuigoDetailData extends Head implements Parcelable {
	// {
	// "purchasedid": 1,
	// "title": "复古女性套装",
	// "integral": 323,
	// "price": 4545,
	// "brand": "波司登",
	// "source": "自拍",
	// "no": "se323",
	// "version": "修身",
	// "style": "甜美",
	// "clotheslength": "中长款",
	// "sleevelengt": "长袖",
	// "collar": "立领",
	// "sleevetype": "常规",
	// "coatfront": "双排扣",
	// "pattern": "纯色",
	// "fabricmaterial": "羊毛",
	// "content": "31",
	// "season": "2014冬季",
	// "color": "87878",
	// "size": "xxl",
	// "picpath": "1",
	// "moduleid": 1,
	// "smoduleid": 0,
	// "userid": 1,
	// "originalprice": 232,
	// "fw": "10-2000积分",
	// "state": 1
	// }
	private String purchasedid;
	private String title;
	private String integral;
	private String price;
	private String brand;
	private String source;
	private String no;
	private String version;
	private String style;
	private String clotheslength;
	private String sleevelengt;
	private String collar;
	private String sleevetype;
	private String coatfront;
	private String pattern;
	private String fabricmaterial;
	private String content;
	private String season;
	private String color;
	private String size;

	public LehuigoDetailData() {

	}

	public LehuigoDetailData(Parcel in) {
		purchasedid = in.readString();
		title = in.readString();
		integral = in.readString();
		price = in.readString();
		brand = in.readString();
		source = in.readString();
		no = in.readString();
		version = in.readString();
		style = in.readString();
		clotheslength = in.readString();
		sleevelengt = in.readString();
		collar = in.readString();
		sleevetype = in.readString();
		coatfront = in.readString();
		pattern = in.readString();
		fabricmaterial = in.readString();
		content = in.readString();
		season = in.readString();
		color = in.readString();
		size = in.readString();

	}

	public LehuigoDetailData(JSONObject json) throws JSONException {
		purchasedid = getJsonStrValue(json, "purchasedid");
		title = getJsonStrValue(json, "title");
		integral = getJsonStrValue(json, "integral");
		price = getJsonStrValue(json, "price");
		brand = getJsonStrValue(json, "brand");
		source = getJsonStrValue(json, "source");
		no = getJsonStrValue(json, "no");
		version = getJsonStrValue(json, "version");
		style = getJsonStrValue(json, "style");
		clotheslength = getJsonStrValue(json, "clotheslength");
		sleevelengt = getJsonStrValue(json, "sleevelengt");
		collar = getJsonStrValue(json, "collar");
		sleevetype = getJsonStrValue(json, "sleevetype");
		coatfront = getJsonStrValue(json, "coatfront");
		pattern = getJsonStrValue(json, "pattern");
		fabricmaterial = getJsonStrValue(json, "fabricmaterial");
		content = getJsonStrValue(json, "content");
		season = getJsonStrValue(json, "season");
		color = getJsonStrValue(json, "color");
		size = getJsonStrValue(json, "size");

	}

	public String getPurchasedid() {
		return purchasedid;
	}

	public String getTitle() {
		return title;
	}

	public String getIntegral() {
		return integral;
	}

	public String getPrice() {
		return price;
	}

	public String getBrand() {
		return brand;
	}

	public String getSource() {
		return source;
	}

	public String getNo() {
		return no;
	}

	public String getVersion() {
		return version;
	}

	public String getStyle() {
		return style;
	}

	public String getClotheslength() {
		return clotheslength;
	}

	public String getSleevelengt() {
		return sleevelengt;
	}

	public String getCollar() {
		return collar;
	}

	public String getSleevetype() {
		return sleevetype;
	}

	public String getCoatfront() {
		return coatfront;
	}

	public String getPattern() {
		return pattern;
	}

	public String getFabricmaterial() {
		return fabricmaterial;
	}

	public String getContent() {
		return content;
	}

	public String getSeason() {
		return season;
	}

	public String getColor() {
		return color;
	}

	public String getSize() {
		return size;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(purchasedid);
		dest.writeString(title);
		dest.writeString(integral);
		dest.writeString(price);
		dest.writeString(brand);
		dest.writeString(source);
		dest.writeString(no);
		dest.writeString(version);
		dest.writeString(style);
		dest.writeString(clotheslength);
		dest.writeString(sleevelengt);
		dest.writeString(collar);
		dest.writeString(sleevetype);
		dest.writeString(coatfront);
		dest.writeString(pattern);
		dest.writeString(fabricmaterial);
		dest.writeString(content);
		dest.writeString(season);
		dest.writeString(color);
		dest.writeString(size);

	}

	public static final Parcelable.Creator<LehuigoDetailData> CREATOR = new Creator<LehuigoDetailData>() {

		@Override
		public LehuigoDetailData[] newArray(int size) {
			return new LehuigoDetailData[size];
		}

		@Override
		public LehuigoDetailData createFromParcel(Parcel source) {
			return new LehuigoDetailData(source);
		}
	};

}
