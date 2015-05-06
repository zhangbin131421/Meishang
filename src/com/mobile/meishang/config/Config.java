package com.mobile.meishang.config;

import android.content.SharedPreferences;

public class Config {
	public static final boolean DEBUG = true;
//	public String urlRoot = "http://121.41.38.198:8888/data";
	public String urlRootApi = "http://121.40.126.98/data/";
	public String urlImage = "";
	public SharedPreferences mPrefs;

	public Config(SharedPreferences mPrefs) {
		this.mPrefs = mPrefs;
	}

	public int getGuideFlag() {
		return mPrefs.getInt(Constants.GUIDEFLAG, 0);
	}

	public boolean setGuideFlag(int flag) {
		return mPrefs.edit().putInt(Constants.GUIDEFLAG, flag).commit();
	}

	public long getLastUpdateTime() {
		return mPrefs.getLong(Constants.Last_PICTURE_Update_Time, 0);
	}

	public boolean setLastUpdateTime(long updateTime) {
		return mPrefs.edit()
				.putLong(Constants.Last_PICTURE_Update_Time, updateTime)
				.commit();
	}

	public void putPreferencesVal(String key, int value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public long getPreferencesVal(String key, int defaultVal) {
		return mPrefs.getInt(key, defaultVal);
	}

	public void putPreferencesVal(String key, long value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public long getPreferencesVal(String key, long defaultVal) {
		return mPrefs.getLong(key, defaultVal);
	}

	public void putPreferencesVal(String key, boolean value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getPreferencesVal(String key, boolean defaultVal) {
		return mPrefs.getBoolean(key, defaultVal);
	}

	public void putPreferencesVal(String key, String value) {
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getPreferencesVal(String key, String defaultVal) {
		return mPrefs.getString(key, defaultVal);
	}
}
