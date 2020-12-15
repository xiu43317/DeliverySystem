package com.example.receivemessage;

import java.util.Timer;
import java.util.TimerTask;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.R.integer;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MyService extends Service {
	
	private final String url = "http://52.198.133.251/id.php";
    private Timer timer = new Timer();
    private RequestQueue queue;
    private NotificationManager mgr;
    private String str = "begin";
    private int nid;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("Rock", "onCreate()");
        queue = Volley.newRequestQueue(this);

        mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				StringRequest request = new StringRequest(url, new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.d("Rock", response);
						Log.d("Rock", str);
						try{
							if(!str.equals(response)){
					              //new一個intent物件，並指定Activity切換的class
					              Intent intent = new Intent("FilterString");					              
					              //new一個Bundle物件，並將要傳遞的資料傳入
					              Bundle bundle = new Bundle();
					              bundle.putString("id",response);
					              //將Bundle物件assign給intent
					              intent.putExtras(bundle);
					              //切換Activity
					              sendBroadcast(intent);
					              sendNotice();
								  str = response;
							}else{
								
								Log.d("Rock", response);
							}
						}catch(Exception e){
							str = response.substring(0,2);
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				queue.add(request);
			}
		}, 0, 3000);
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();
		Log.d("Rock","onDestory()");
	}
    private void sendNotice(){
//        Intent nextIntent = new Intent(this, MainActivity.class);


//        PendingIntent pending =
//                PendingIntent.getActivity(this,0,nextIntent,0);  
        

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(android.R.drawable.stat_sys_warning);
        builder.setTicker("新通知");
        builder.setLargeIcon(
                BitmapFactory.decodeResource(getResources(), R.drawable.attention));
        builder.setAutoCancel(true);
        builder.setContentInfo("Info");
        builder.setContentText("請查看內容");
        builder.setContentTitle("有新進訂單");
        builder.setWhen(System.currentTimeMillis());
//        builder.setContentIntent(pending);
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notify);
        builder.setSound(sound);
 


        int dot = 200;      // Length of a Morse Code "dot" in milliseconds
        int dash = 500;     // Length of a Morse Code "dash" in milliseconds
        int short_gap = 200;    // Length of Gap Between dots/dashes
        int medium_gap = 500;   // Length of Gap Between Letters
        int long_gap = 1000;    // Length of Gap Between Words
        long[] pattern = {
                0,  // Start immediately
                dot, short_gap, dot, short_gap, dot,    // s
                medium_gap,
                dash, short_gap, dash, short_gap, dash, // o
                medium_gap,
                dot, short_gap, dot, short_gap, dot,    // s
                long_gap
        };

        builder.setVibrate(pattern);


        // API Level 11+
//		Notification notification = builder.getNotification();
        // API Level 16+ (4.1.2+)
        Notification notification = builder.build();


        mgr.notify(nid++, notification);

    }
}
