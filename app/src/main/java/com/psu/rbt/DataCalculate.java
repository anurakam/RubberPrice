package com.psu.rbt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataCalculate extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Calculate.db";
	private static final int DATABASE_VERSION = 1;

	public DataCalculate(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE calculatedata ("
				+ "_id  INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "date TEXT NOT NULL,"
				+ "water_value TEXT NOT NULL,"
				+ "sheet_value TEXT NOT NULL,"
				+ "lump_value TEXT NOT NULL,"
				+ "scrap_value TEXT NOT NULL,"
				+ "percent_em TEXT NOT NULL,"
				+ "percent_boss TEXT NOT NULL,"
				+ "total_price TEXT NOT NULL,"
				+ "total_em TEXT NOT NULL,"
				+ "total_boss TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS calculatedata");
		onCreate(db);
	}

}
