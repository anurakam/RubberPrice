package com.psu.rbt;

import java.util.Calendar;



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatePrice extends Activity {
	private Button list_save_btn;
	private Button cal_btn;
	private Button save_btn;
	private TextView mDateDisplay;
	private Button mPickDate;
	
	private DataCalculate dataCalculate;
	//private TextView DateDisplay;
	
	private TextView Total;
	private EditText amountWater ;
	private EditText amountSheet ;
	private EditText amountLump ;
	private EditText amountScrap ;
	
	private TextView TotalEmployee;
	private EditText percentEmployee;
	private TextView TotalBoss;
	private EditText percentBoss;
	
	private double cal,calPercentEmployee,calPercentBoss;
	private int Year;
	private int Month;
	private int Day;
	
	private String amountWater_value,amountSheet_value,amountLump_value,amountScrap_value;
	private String price_Water,price_Sheet,price_Lump,price_Scrap;
	private String percent_Employee,percent_Boss;
	private String total,totalEmployee,totalBoss;
	
	private String date,water_value,sheet_value,lump_value,scrap_value,percent_em,percent_boss,total_price,total_em,total_boss;
	static final int DATE_DIALOG_ID = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitie_calculate_price);
		
		dataCalculate = new DataCalculate(this);
		//DateDisplay = (TextView) findViewById(R.id.dateDisplay);
		amountWater = (EditText) findViewById(R.id.amount_water);
		amountSheet = (EditText) findViewById(R.id.amount_sheet);
		amountLump = (EditText) findViewById(R.id.amount_lump);
		amountScrap = (EditText) findViewById(R.id.amount_scrap);
		
		percentEmployee = (EditText) findViewById(R.id.PercentEmployee);
		percentBoss = (EditText) findViewById(R.id.PercentBoss);
		
		Total = (TextView) findViewById(R.id.Total);
		TotalEmployee = (TextView) findViewById(R.id.TotalEmployee);
		TotalBoss = (TextView) findViewById(R.id.TotalBoss);
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    
	    
	    list_save_btn = (Button)findViewById(R.id.List_Save_btn);
	    list_save_btn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 Intent intent = new Intent(CalculatePrice.this, ViewListSave.class);
					 startActivity(intent);
				}});
	    cal_btn = (Button)findViewById(R.id.Cal_btn);
	    cal_btn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				amountWater_value = amountWater.getText().toString();
				amountSheet_value = amountSheet.getText().toString();
				amountLump_value = amountLump.getText().toString();
				amountScrap_value = amountScrap.getText().toString();
				percent_Employee = percentEmployee.getText().toString();
				percent_Boss = percentBoss.getText().toString();
				
				if(amountWater_value.length()==0 || amountSheet_value.length()==0 || 
						amountLump_value.length()==0 || amountScrap_value.length()==0 
						|| percent_Employee.length()==0 || percent_Boss.length()==0){
					
					DialogCheckEmpty();
					
				}
				else{
				
				double AW = Double.parseDouble(amountWater_value);				
				double AS = Double.parseDouble(amountSheet_value);				
				double AL = Double.parseDouble(amountLump_value);
				double ASc = Double.parseDouble(amountScrap_value);
				
				price_Water = getIntent().getExtras().getString("price_Water");
				double PW = Double.parseDouble(price_Water);
				price_Sheet = getIntent().getExtras().getString("price_Sheet");
				double PS = Double.parseDouble(price_Sheet);
				price_Lump = getIntent().getExtras().getString("price_Lump");
				double PL = Double.parseDouble(price_Lump);
				price_Scrap = getIntent().getExtras().getString("price_Scrap");
				double PSc = Double.parseDouble(price_Scrap);
				
				cal = (AW*PW) + (AS*PS) + (AL*PL) + (ASc*PSc);
				
				total = String.valueOf(cal);
				Total.setText(total);
				
				
				double PerEm = Double.parseDouble(percent_Employee);
				
				calPercentEmployee = (PerEm/100)*cal;
				
				totalEmployee = String.valueOf(calPercentEmployee);
				TotalEmployee.setText(totalEmployee);
				
				
				double PerBoss = Double.parseDouble(percent_Boss);
				
				calPercentBoss = (PerBoss/100)*cal;
				
				totalBoss = String.valueOf(calPercentBoss);
				TotalBoss.setText(totalBoss);
				}
				
			}});
	    save_btn = (Button)findViewById(R.id.Save_btn);
	    save_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				date = mDateDisplay.getText().toString();
				water_value = amountWater_value;
				sheet_value = amountSheet_value;
				lump_value = amountLump_value;
				scrap_value = amountScrap_value;
				percent_em = percent_Employee;
				percent_boss = percent_Boss;
				total_price = total;
				total_em = totalEmployee;
				total_boss = totalBoss;
				addCalculate(date,water_value,sheet_value,lump_value,scrap_value,percent_em,percent_boss,total_price,total_em,total_boss);
				Toast.makeText(CalculatePrice.this, "บันทึกเสร็จสิ้น",
						Toast.LENGTH_LONG).show();
			}

			private void addCalculate(String date, String water_value,
					String sheet_value, String lump_value, String scrap_value,
					String percent_em, String percent_boss, String total_price,
					String total_em, String total_boss) {
				// TODO Auto-generated method stub
				ContentValues values = new ContentValues();
				values.put("date",date);
				values.put("water_value",water_value);
				values.put("sheet_value",sheet_value);
				values.put("lump_value",lump_value);
				values.put("scrap_value",scrap_value);
				values.put("percent_em",percent_em);
				values.put("percent_boss",percent_boss);
				values.put("total_price",total_price);
				values.put("total_em",total_em);
				values.put("total_boss",total_boss);
				
				SQLiteDatabase db = dataCalculate.getWritableDatabase();
				db.insertOrThrow("calculatedata", null, values);
			}
		});
	    
	    mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);
        
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        
        final Calendar c = Calendar.getInstance();
        Year = c.get(Calendar.YEAR);
        Month = c.get(Calendar.MONTH);
        Day = c.get(Calendar.DAY_OF_MONTH);

        // display the current date (this method is below)
        updateDisplay();

	}
	@Override
    protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    Year, Month, Day);
	    }
	    return null;
	}
	
    // updates the date we display in the TextView
    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(Month + 1).append("-")
                    .append(Day).append("-")
                    .append(Year).append(" "));
    }
	
    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    Year = year;
                    Month = monthOfYear;
                    Day = dayOfMonth;
                    updateDisplay();
                }
            };
            
    private void DialogCheckEmpty(){
    	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("กรุณากรอกข้อมูลให้ครบทุกช่อง");
        dialog.setPositiveButton("ตกลง", new OnClickListener() {
        	@Override
            public void onClick(DialogInterface dialog, int which) {
        		dialog.cancel();
            }
        });
        
        dialog.show();
    }
    
            
  
}
