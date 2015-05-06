package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture extends Head implements Parcelable {

	// {
	// "pictureid": 4,
	// "name": "项目工程1",
	// "picpath": "http://121.40.126.98:80/data/image/pic/pic.png",
	// "updatatime": "2015-04-30 16:50:02.0",
	// "createtime": "2015-04-30 16:50:31.0",
	// "moduleid": 100,
	// "smoduleid": 0,
	// "objectid": 1
	// },

	private String pictureid;
	private String picpath;

	public Picture() {
	}

	public Picture(JSONObject json) throws JSONException {
		pictureid = getJsonStrValue(json, "pictureid");
		picpath = getJsonStrValue(json, "picpath");
	}

	public Picture(Parcel in) {
		pictureid = in.readString();
		picpath = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(pictureid);
		dest.writeString(picpath);
	}

	public static final Parcelable.Creator<Picture> CREATOR = new Creator<Picture>() {

		@Override
		public Picture[] newArray(int size) {
			return new Picture[size];
		}

		@Override
		public Picture createFromParcel(Parcel source) {
			return new Picture(source);
		}
	};

	public String getAdvertisingid() {
		return pictureid;
	}

	public String getPicpath() {
		return picpath;
	}

}
