package com.psu.rbt;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class ViewListSave extends Activity {
	
	private ListView listCalculate;
	private ArrayList<String> ArrayCalculate;
	private DataCalculate dataCalculate;
	private SQLiteDatabase CalculateDB;
	private Cursor CalculateCursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitie_list_save);
		
		listCalculate = (ListView) findViewById(R.id.listCalculate);

		ArrayCalculate = new ArrayList<String>();
		dataCalculate = new DataCalculate(this);
		CalculateDB = dataCalculate.getReadableDatabase();
		CalculateCursor = CalculateDB.rawQuery(
				"SELECT date FROM calculatedata", null);
		CalculateCursor.moveToFirst();
		while (!CalculateCursor.isAfterLast()) {
			ArrayCalculate.add(CalculateCursor.getString(CalculateCursor
					.getColumnIndex("date")));
			CalculateCursor.moveToNext();
		}

		ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(
				this,
				R.layout.item_listview_calculate, ArrayCalculate);
		listCalculate.setAdapter(adapterDir);

		listCalculate.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ViewListSave.this,
						ViewCalculateData.class);
				intent.putExtra("index", arg2 + 1);
				intent.putExtra("date", ArrayCalculate.get(arg2));
				startActivity(intent);
			}
		});
	    
	   

	}
}
