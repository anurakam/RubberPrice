package com.psu.rbt;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ViewCalculateData extends Activity {
	private TextView viewWater;
	private TextView viewSheet;
	private TextView viewLump;
	private TextView viewScrap;
	private TextView viewPercentEmployee;
	private TextView viewPercentBoss;
	private TextView viewTotal;
	private TextView viewTotalEmployee;
	private TextView viewTotalBoss;
	private TextView headerData;
	private DataCalculate dataCalculate;
	private SQLiteDatabase CalculateDB;
	private Cursor CalculateCursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate_item);
		
		viewWater = (TextView) findViewById(R.id.viewWater);
		viewSheet = (TextView) findViewById(R.id.viewSheet);
		viewLump = (TextView) findViewById(R.id.viewLump);
		viewScrap = (TextView) findViewById(R.id.viewScrap);
		viewPercentEmployee = (TextView) findViewById(R.id.viewPercentEmployee);
		viewPercentBoss = (TextView) findViewById(R.id.viewPercentBoss);
		viewTotal = (TextView) findViewById(R.id.viewTotal);
		viewTotalEmployee = (TextView) findViewById(R.id.viewTotalEmployee);
		viewTotalBoss = (TextView) findViewById(R.id.viewTotalBoss);
		Bundle bundle = getIntent().getExtras();
		int index = bundle.getInt("index");
		String date = bundle.getString("date");
		headerData = (TextView) findViewById(R.id.datehead);
		headerData.setText(date);
		dataCalculate = new DataCalculate(this);
		CalculateDB = dataCalculate.getReadableDatabase();
		CalculateCursor = CalculateDB.rawQuery(
				"SELECT * FROM calculatedata where _id = ?",
				new String[] { Integer.toString(index) });
		CalculateCursor.moveToFirst();
		
		viewWater.setText(CalculateCursor.getString(2));
		viewSheet.setText(CalculateCursor.getString(3));
		viewLump.setText(CalculateCursor.getString(4));
		viewScrap.setText(CalculateCursor.getString(5));
		viewPercentEmployee.setText(CalculateCursor.getString(6));
		viewPercentBoss.setText(CalculateCursor.getString(7));
		viewTotal.setText(CalculateCursor.getString(8));
		viewTotalEmployee.setText(CalculateCursor.getString(9));
		viewTotalBoss.setText(CalculateCursor.getString(10));
		
		
	}
}
