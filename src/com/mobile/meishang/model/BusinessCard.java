package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import com.mobile.meishang.model.bean.Head;

public class BusinessCard extends Head implements Parcelable {
	// {
	// "id": 16,
	// "userId": 1,
	// "name": "袁成1",
	// "companyName": "美尚1",
	// "position": "总监1",
	// "provinceId": 3,
	// "moduleId": 3,
	// "status": 2,
	// "headerpath": "http://121.40.126.98:80/data/image/header.jpg",
	// "provinceName": "湖北省",
	// "moduleName": "美容"
	// }
	private String id;
	private String userId;
	private String name;
	private String tel;
	private String companyName;
	private String position;
	private String provinceId;
	private String moduleId;
	private int status;
	private String headerpath;
	private String provinceName;
	private String moduleName;

	public BusinessCard() {

	}

	public BusinessCard(Parcel in) {
		id = in.readString();
		userId = in.readString();
		name = in.readString();
		tel = in.readString();
		companyName = in.readString();
		position = in.readString();
		provinceId = in.readString();
		moduleId = in.readString();
		status = in.readInt();
		headerpath = in.readString();
		provinceName = in.readString();
		moduleName = in.readString();
	}

	public BusinessCard(JSONObject json) throws JSONException {
		id = getJsonStrValue(json, "id");
		userId = getJsonStrValue(json, "userId");
		name = getJsonStrValue(json, "name");
		tel = getJsonStrValue(json, "tel");
		companyName = getJsonStrValue(json, "companyName");
		position = getJsonStrValue(json, "position");
		provinceId = getJsonStrValue(json, "provinceId");
		moduleId = getJsonStrValue(json, "moduleId");
		status = getJsonIntValue(json, "status");
		headerpath = getJsonStrValue(json, "headerpath");
		provinceName = getJsonStrValue(json, "provinceName");
		moduleName = getJsonStrValue(json, "moduleName");

	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getPosition() {
		return position;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public int getStatus() {
		return status;
	}

	public String getHeaderpath() {
		return headerpath;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public String getModuleName() {
		return moduleName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(userId);
		dest.writeString(name);
		dest.writeString(tel);
		dest.writeString(companyName);
		dest.writeString(position);
		dest.writeString(provinceId);
		dest.writeString(moduleId);
		dest.writeInt(status);
		dest.writeString(headerpath);
		dest.writeString(provinceName);
		dest.writeString(moduleName);
	}

	public static final Parcelable.Creator<BusinessCard> CREATOR = new Creator<BusinessCard>() {

		@Override
		public BusinessCard[] newArray(int size) {
			return new BusinessCard[size];
		}

		@Override
		public BusinessCard createFromParcel(Parcel source) {
			return new BusinessCard(source);
		}
	};
}
