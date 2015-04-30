package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class AdvertisingGalleryItem extends Head implements Parcelable {

	// {
	// "advertisingid": 1,
	// "name": "京东商店",
	// "picpath":
	// "http://121.43.224.225:8888/data/image/advertising/adv_0_1.jpg",
	// "updatatime": "2015-04-29 18:05:03",
	// "createtime": "2015-04-29 18:05:12",
	// "moduleid": 0,
	// "lablename": "推广"
	// }
	// {

	private String advertisingid;
	private String picpath;
	private String lablename;

	public AdvertisingGalleryItem() {
	}

	public AdvertisingGalleryItem(JSONObject json) throws JSONException {
		advertisingid = getJsonStrValue(json, "advertisingid");
		picpath = getJsonStrValue(json, "picpath");
		lablename = getJsonStrValue(json, "lablename");
	}

	public AdvertisingGalleryItem(Parcel in) {
		advertisingid = in.readString();
		picpath = in.readString();
		lablename = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(advertisingid);
		dest.writeString(picpath);
		dest.writeString(lablename);
	}

	public static final Parcelable.Creator<AdvertisingGalleryItem> CREATOR = new Creator<AdvertisingGalleryItem>() {

		@Override
		public AdvertisingGalleryItem[] newArray(int size) {
			return new AdvertisingGalleryItem[size];
		}

		@Override
		public AdvertisingGalleryItem createFromParcel(Parcel source) {
			return new AdvertisingGalleryItem(source);
		}
	};

	public String getAdvertisingid() {
		return advertisingid;
	}

	public String getPicpath() {
		return picpath;
	}

	public String getLablename() {
		return lablename;
	}

}
