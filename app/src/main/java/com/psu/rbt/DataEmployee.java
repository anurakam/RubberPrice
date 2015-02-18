package com.psu.rbt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataEmployee extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Employee.db";
	private static final int DATABASE_VERSION = 1;

	public DataEmployee(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE employeedata ("
				+ "_id  INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "fname TEXT NOT NULL,"
				+ "lname TEXT NOT NULL,"
				+ "phone TEXT NOT NULL,"
				+ "address TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS employeedata");
		onCreate(db);
	}

}
