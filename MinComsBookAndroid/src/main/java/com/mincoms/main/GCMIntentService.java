package com.mincoms.main;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Handler;

import android.util.Log;

import android.widget.Toast;


import com.google.android.gcm.GCMBaseIntentService;


public class GCMIntentService extends GCMBaseIntentService{

	//GCM에 정상적으로 등록되었을경우 발생하는 메소드

	@Override

	protected void onRegistered(Context arg0, String arg1) {

		// TODO Auto-generated method stub

		Log.d("test","등록ID:"+arg1);

	}

	

	//GCM에 해지하였을경우 발생하는 메소드

	@Override

	protected void onUnregistered(Context arg0, String arg1) {

		Log.d("test","해지ID:"+arg1);

	}

	

	//GCM이 메시지를 보내왔을때 발생하는 메소드

	@Override

	protected void onMessage(Context context, Intent arg1) {

		// TODO Auto-generated method stub
		Log.d("test", "메시지가 왔습니다 : " + arg1.getExtras().getString("message"));
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Activity.NOTIFICATION_SERVICE);

		// 해당 어플을 실행하는 이벤트를 하고싶을 때 아래 주석을 풀어주세요
		 PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0);
		//PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);

		Notification notification = new Notification();
		notification.icon = R.drawable.ic_search;
		notification.tickerText = " 티켓";
		notification.when = System.currentTimeMillis();
		notification.vibrate = new long[] { 500, 100, 500, 100 };
		notification.sound = Uri.parse("/system/media/audio/notifications/20_Cloud.ogg");
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(context, "제에목",  arg1.getExtras().getString("message"), pendingIntent);

		notificationManager.notify(0, notification);

	}

	

	//오류를 핸들링하는 메소드

	@Override

	protected void onError(Context arg0, String arg1) {

		Log.d("test",arg1); 

	}

	

	//서비스 생성자

	public GCMIntentService() {

		super("357588365971");

		Log.d("test","GCM서비스 생성자 실행");

	}

}