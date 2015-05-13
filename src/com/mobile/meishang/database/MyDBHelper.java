package com.mobile.meishang.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	public MyDBHelper(Context context) {
		super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 数据库升级时需要清空的数据
		// LeShiHuiApplication.getInstance().getmConfig()
		// .putPreferencesVal(DBConstants.CATEGORY_LAST_UPDATE_TIMES, 0L);
		//
		db.beginTransaction();
		try {
			db.execSQL("create table if not exists "
					+ DBConstants.DB_TABLE.TABLE_HOME_MODEl + "("
					+ "_id integer primary key autoincrement,"
					+ DBConstants.Home_model.HOME_MODEL_NAME + " text,"
					+ DBConstants.Home_model.HOME_MODEL_IMAGE + " integer,"
					+ DBConstants.Home_model.HOME_MODEL_FLAG + " integer)");

			// db.execSQL("create table if not exists "
			// + DBConstants.DB_TABLE.TABLE_PROGRAM_LIST + "("
			// + "_id integer primary key autoincrement,"
			// + DBConstants.ProgramList.PROGRAM_ID + " text,"
			// + DBConstants.ProgramList.PROGRAM_NAME + " text,"
			// + DBConstants.ProgramList.PROGRAM_CONTENT + " text,"
			// + DBConstants.ProgramList.PROGRAM_URL + " text,"
			// + DBConstants.ProgramList.ACTOR_ORGANIZE + " text)");
			//
			// db.execSQL("create table if not exists "
			// + DBConstants.DB_TABLE.TABLE_AGENDA_LIST + "("
			// + "_id integer primary key autoincrement,"
			// + DBConstants.AgendaList.CHAPTER_NAME + " text,"
			// + DBConstants.AgendaList.PROGRAM_NAME + " text,"
			// + DBConstants.AgendaList.ACTOR_ORGANIZE + " text)");
			//
			// db.execSQL("create table if not exists "
			// + DBConstants.DB_TABLE.TABLE_LUCK_NUMBER + "("
			// + "_id integer primary key autoincrement,"
			// + DBConstants.LuckNumber.NUMBER + " text,"
			// + DBConstants.LuckNumber.USER + " text,"
			// + DBConstants.LuckNumber.TIME + " text)");

			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "
				+ DBConstants.DB_TABLE.TABLE_HOME_MODEl);
		// db.execSQL("DROP TABLE IF EXISTS "
		// + DBConstants.DB_TABLE.TABLE_PROGRAM_LIST);
		// db.execSQL("DROP TABLE IF EXISTS "
		// + DBConstants.DB_TABLE.TABLE_AGENDA_LIST);
		// db.execSQL("DROP TABLE IF EXISTS "
		// + DBConstants.DB_TABLE.TABLE_LUCK_NUMBER);
		onCreate(db);
	}
}
