package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class AdvertisingGalleryItem extends Head implements Parcelable {
	// {
	// "picPath": "http://121.41.38.198:8888/data/ImageFile/advert/adv_0_1.jpg",
	// "lableId": "1",
	// "dataLable": {},
	// "templateId": 0,
	// "advertId": 1,
	// "title": "推广",
	// "text": "推广"
	// }

	private String picPath;
	private String lableId;

	// private String templateId;
	// private String advertId;
	// private String title;
	// private String text;

	public AdvertisingGalleryItem() {
	}

	public AdvertisingGalleryItem(JSONObject json) throws JSONException {
		picPath = getJsonStrValue(json, "picPath");
		lableId = getJsonStrValue(json, "lableId");
	}

	public AdvertisingGalleryItem(Parcel in) {
		picPath = in.readString();
		lableId = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(picPath);
		dest.writeString(lableId);
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

	public String getPicPath() {
		return picPath;
	}

	public String getLableId() {
		return lableId;
	}

}
