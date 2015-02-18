package com.psu.rbt;

import java.util.ArrayList;





import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class InfoEmployee extends Fragment {
	DataEmployee dataEmployee;
	private SQLiteDatabase EmployeeDB;
	private Cursor EmployeeCursor;

	private TextView textEmployee;
	private ListView listEmployee;
	private ArrayList<String> ArrayEmployee;
	
 private String[] actionTitles = { "Edit", "Delete" };
	    private int[] actionIcons = {
	            android.R.drawable.ic_menu_edit,
	            android.R.drawable.ic_menu_delete };

	    private ActionMode mActionMode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activitie_infoemployee, container, false);
         
        /**/
        return rootView;
    }
    
    @Override
	public void onStart(){
		super.onStart();
		
		changePage();
		
		}
    public void changePage(){
    	
    	listEmployee = (ListView) getView().findViewById(R.id.listEmployee);

		ArrayEmployee = new ArrayList<String>();
		dataEmployee = new DataEmployee(getActivity());
		EmployeeDB = dataEmployee.getReadableDatabase();
		EmployeeCursor = EmployeeDB.rawQuery(
				"SELECT fname FROM employeedata", null);
		EmployeeCursor.moveToFirst();
		while (!EmployeeCursor.isAfterLast()) {
			ArrayEmployee.add(EmployeeCursor.getString(EmployeeCursor
					.getColumnIndex("fname")));
			EmployeeCursor.moveToNext();
		}

		ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(
				getActivity(),
				R.layout.item_listview_employee, ArrayEmployee);
		listEmployee.setAdapter(adapterDir);

		listEmployee.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						ViewEmployeeData.class);
				intent.putExtra("index", arg2 + 1);
				intent.putExtra("fname", ArrayEmployee.get(arg2));
				startActivity(intent);
			}
		});
    
		listEmployee.setOnItemLongClickListener(new OnItemLongClickListener() {
			

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				 if (mActionMode != null) {
                    return false;
                }
				 
                mActionMode = getActivity()
                        .startActionMode(mActionModeCallback);
                
                return true;
			}
        });

	    
    
    }
private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

	        @Override
	        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	            MenuItem item;

	            for (int i = 0; i < actionTitles.length; i++) {
	                item = menu.add(Menu.NONE, i, i, actionTitles[i]);
	                item.setIcon(actionIcons[i]);
	                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
	                        | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	               
	            }
	            return true;
	        }

	        @Override
	        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	            return false;
	        }

	        @Override
	        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	            int actionIndex = item.getItemId();
	            String actionName = actionTitles[actionIndex];
	            
	            TextView text = (TextView) getView().findViewById(R.id.textView1);
	            text.setText("You selected " + actionName );
	          //  if (actionName.equals("Delete")){
	            
	            	
	          //  }

	            mode.finish();
	            return true;
	        }

	        @Override
	        public void onDestroyActionMode(ActionMode mode) {
	            mActionMode = null;
	        }
	    };
}
