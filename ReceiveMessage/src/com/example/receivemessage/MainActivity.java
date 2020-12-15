package com.example.receivemessage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Switch toggleBtn1;
	private ListView listview;
	ArrayAdapter<String> myArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview = (ListView) findViewById(R.id.listView1);
		myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		listview.setAdapter(myArrayAdapter);
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showDialog2(position);
				return false;
			}
		});
		toggleBtn1 = (Switch) findViewById(R.id.switch1);
		toggleBtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(toggleBtn1.isChecked()){
					Intent startIntent = new Intent(MainActivity.this, MyService.class);
					startService(startIntent);
					Toast.makeText(MainActivity.this, "��ť�Ұ�", Toast.LENGTH_SHORT).show();
				}else{
					Intent stopIntent = new Intent(MainActivity.this, MyService.class);
					stopService(stopIntent);
					Toast.makeText(MainActivity.this, "��ť����", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		BroadcastReceiver receiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				try{
				       //����intent����bundle����
				       Bundle bundle =intent.getExtras();

//				       String order = bundle.getString("order");
				       String id = bundle.getString("id");
				       myArrayAdapter.add("�q�渹:"+id);
				       myArrayAdapter.notifyDataSetChanged();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

		};
        final String Action = "FilterString";
        IntentFilter filter = new IntentFilter(Action);
        // �N BroadcastReceiver �b Activity ���_�ӡC
        registerReceiver(receiver, filter);
	}
	private void showDialog2(final int n){
		AlertDialog alert;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final String strSelectedItem = listview.getItemAtPosition(n).toString();
		builder.setTitle("�R���q��");
		builder.setCancelable(false);
        builder.setPositiveButton("�T�w�R��", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
        		myArrayAdapter.remove(strSelectedItem);
        		myArrayAdapter.notifyDataSetChanged();
        		dialog.dismiss();
        	}
        });
		alert = builder.create();
		alert.show();    
	}
	
}
