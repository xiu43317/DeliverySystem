package com.example.apporder;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button btn1,btn2,btn3;
	ListView listview;
	String drink,ice,sweet,total,submit="總共:";
	int number,i;
	ArrayAdapter<String> MyArrayAdapter;
	final String url ="http://52.198.133.251/orderadd.php";
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		listview =(ListView) findViewById(R.id.listView1);
		queue = Volley.newRequestQueue(this);
		MyArrayAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		listview.setAdapter(MyArrayAdapter);
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showDialog2(position);
				return false;
			}
		});

		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();

			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				submit = "總共:";
				for(i=0;i<listview.getLastVisiblePosition()-listview.getFirstVisiblePosition()+1;i++){
					submit += listview.getItemAtPosition(i).toString()+",  ";
				}
				StringRequest getRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.d("Rock", response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						
					}
				}){

					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
						Map<String, String>map = new HashMap<String, String>();
						map.put("Order", submit);
						return map;
					}
					
				}; 
				queue.add(getRequest);
				Toast.makeText(MainActivity.this,submit, Toast.LENGTH_LONG).show();
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	private void showDialog(){
		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.activity_edit);
		dialog.setTitle("新增訂單");
		final NumberPicker mNumber;
		final TextView tv4 = (TextView) dialog.findViewById(R.id.textView4);
		final Button btnConfirm = (Button) dialog.findViewById(R.id.button3);
		final Button btnBack = (Button) dialog.findViewById(R.id.buttonBack);
		Spinner mSpnDrink = (Spinner) dialog.findViewById(R.id.spinner1);
		final RadioGroup mRadGrpIce;
		final RadioGroup mRadGrpSweet;
		mRadGrpIce = (RadioGroup) dialog.findViewById(R.id.radGrpIce);
		mRadGrpSweet = (RadioGroup) dialog.findViewById(R.id.radGrpSweet);
		mNumber = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
		mNumber.setMinValue(1);
		mNumber.setMaxValue(20);
		mNumber.setValue(1);
				

		mSpnDrink.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				drink = parent.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		mNumber.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				tv4.setText("請選擇數量:"+String.valueOf(newVal));
			}
		});
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		btnConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					switch (mRadGrpIce.getCheckedRadioButtonId()){
					case R.id.radBtnHot:
						ice = getString(R.string.radBtnHot);
						break;
					case R.id.radBtnLess:
						ice = getString(R.string.radBtnLess);
						break;
					case R.id.radBtnLight:
						ice = getString(R.string.radBtnLight);
						break;
					case R.id.radBtnNo:
						ice = getString(R.string.radBtnNo);
						break;
					}
					switch (mRadGrpSweet.getCheckedRadioButtonId()){
					case R.id.radBtnSugerLess:
						sweet = getString(R.string.radBtnSugerLess);
						break;
					case R.id.radBtnSugerhalf:
						sweet = getString(R.string.radBtnSugerhalf);
						break;
					case R.id.radBtnSugerLight:
						sweet = getString(R.string.radBtnSugerLight);
						break;
					case R.id.radBtnSugerFree:
						sweet = getString(R.string.radBtnSugerFree);
						break;
					}
				  number = mNumber.getValue();
				  total = drink+":"+ice+":"+sweet+":"+number+"杯";
				  MyArrayAdapter.add(total);
				  MyArrayAdapter.notifyDataSetChanged();
				  Toast.makeText(MainActivity.this, "新增成功", Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});	
		dialog.show();
	}
	private void showDialog2(final int n){
		AlertDialog alert;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final String strSelectedItem = listview.getItemAtPosition(n).toString();
		builder.setTitle("刪除訂單");
		builder.setCancelable(false);
        builder.setPositiveButton("確定刪除", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
        		MyArrayAdapter.remove(strSelectedItem);
        		MyArrayAdapter.notifyDataSetChanged();
        		submit = "總共:";
        		dialog.dismiss();
        	}
        });
		alert = builder.create();
		alert.show();    
	}
	
}
