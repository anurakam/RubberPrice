package com.psu.rbt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditEmployee extends Activity {
	private EditText editEmployee;
	private EditText editLname;
	private EditText editPhone;
	private EditText editAddress;
	private DataEmployee dataEmployee;
	private Button save_em_btn;
	private String fname, lname, phone, address;

	private TextView header;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.employee_new);
		
		dataEmployee = new DataEmployee(this);
		editEmployee = (EditText) findViewById(R.id.editEmployee);
		editLname = (EditText) findViewById(R.id.editLname);
		editPhone = (EditText) findViewById(R.id.editPhone);
		editAddress = (EditText) findViewById(R.id.editAddress);
		save_em_btn = (Button) findViewById(R.id.save_em_btn);
		header = (TextView) findViewById(R.id.header);
		header.setText("เพิ่มข้อมูลลูกจ้าง");
		
		
		save_em_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fname = editEmployee.getText().toString();
				lname = editLname.getText().toString();
				phone = editPhone.getText().toString();
				address = editAddress.getText().toString();
				if(fname.length()!=0 || lname.length()!=0 || phone.length()!=0 || address.length()!=0){
					addEmployee(fname, lname, phone, address);
				editEmployee.setText("");
				editLname.setText("");
				editPhone.setText("");
				editAddress.setText("");
				Toast.makeText(EditEmployee.this, "บันทึกเสร็จสิ้น",
						Toast.LENGTH_LONG).show();
				finish();
				}
				else{
					DialogCheckEmpty();
				}
			}
			

			private void addEmployee(String fname, String lname, String phone,
					String address) {
				ContentValues values = new ContentValues();

				values.put("fname", fname);
				values.put("lname", lname);
				values.put("phone", phone);
				values.put("address", address);

				SQLiteDatabase db = dataEmployee.getWritableDatabase();
				db.insertOrThrow("employeedata", null, values);
			}
		});
		

	}
	private void DialogCheckEmpty(){
    	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("กรุณากรอกข้อมูลอย่างน้อยหนึ่งช่อง");
        dialog.setPositiveButton("ตกลง", new OnClickListener() {
        	@Override
            public void onClick(DialogInterface dialog, int which) {
        		dialog.cancel();
            }
        });
        
        dialog.show();
    }

}
