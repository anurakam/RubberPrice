package com.psu.rbt;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewEmployeeData extends Activity {
	private TextView headerData;
	LinearLayout mainLayout;
	int lent;
	String[] items;
	int count;
	int count1;
	private DataEmployee dataEmployee;
	private SQLiteDatabase EmployeeDB;
	private Cursor EmployeeCursor;
	private TextView viewEmployee;
	private TextView viewLname;
	private TextView viewPhone;
	private TextView viewAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_item);
		
		viewEmployee = (TextView) findViewById(R.id.viewEmployee);
		viewLname = (TextView) findViewById(R.id.viewLname);
		viewPhone = (TextView) findViewById(R.id.viewPhone);
		viewAddress = (TextView) findViewById(R.id.viewAddress);
		Bundle bundle = getIntent().getExtras();
		int index = bundle.getInt("index");
		String fname = bundle.getString("fname");
		headerData = (TextView) findViewById(R.id.headerData);
		headerData.setText(fname);
		dataEmployee = new DataEmployee(this);
		EmployeeDB = dataEmployee.getReadableDatabase();
		EmployeeCursor = EmployeeDB.rawQuery(
				"SELECT * FROM employeedata where _id = ?",
				new String[] { Integer.toString(index) });
		EmployeeCursor.moveToFirst();

		viewEmployee.setText(EmployeeCursor.getString(1));
		viewLname.setText(EmployeeCursor.getString(2));
		viewPhone.setText(EmployeeCursor.getString(3));
		viewAddress.setText(EmployeeCursor.getString(4));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(this, EditEmployee.class);
		startActivity(intent);
		return true;

	}
}