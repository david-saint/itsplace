package com.mincoms.gcm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class GCMIntentService_backup  extends IntentService{
	public GCMIntentService_backup() {
		super("GCMIntentService");
		}
		 
		private static PowerManager.WakeLock sWakeLock;
		private static final Object LOCK = GCMIntentService_backup.class;
		 
		static void runIntentInService(Context context, Intent intent) {
			synchronized(LOCK) {
				if (sWakeLock == null) {
					PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
					sWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "my_wakelock");
				}
			}
			sWakeLock.acquire();
			intent.setClassName(context, GCMIntentService_backup.class.getName());
			context.startService(intent);
		}
		 
		@Override
		public final void onHandleIntent(Intent intent) {
			try {
				String action = intent.getAction();
				if (action.equals("com.google.android.c2dm.intent.REGISTRATION")) {
					handleRegistration(intent);
				} else if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
					handleMessage(intent);
				}
			} finally {
				synchronized(LOCK) {
				sWakeLock.release();
			}
		}
		}
		
		private void handleRegistration(Intent intent) {
			String registrationId = intent.getStringExtra("registration_id");
			String error = intent.getStringExtra("error");
			String unregistered = intent.getStringExtra("unregistered");
			// registration succeeded
			if (registrationId != null) {
			// store registration ID on shared preferences
			// notify 3rd-party server about the registered ID
				Log.i("registrationId", registrationId);
			}
			 
			// unregistration succeeded
			if (unregistered != null) {
			// get old registration ID from shared preferences
			// notify 3rd-party server about the unregistered ID
				Log.i("unregistered", unregistered);
			}
			 
			// last operation (registration or unregistration) returned an error;
			if (error != null) {
				if ("SERVICE_NOT_AVAILABLE".equals(error)) {
				// optionally retry using exponential back-off
				// (see Advanced Topics)
				} else {
				// Unrecoverable error, log it
				Log.i("Received error", "Received error: " + error);
				}
			}
		}
		
		// data 객체에 정보들이 담겨저 온다.
		// ex ) data.msg=“테스트 메세지”;
		public void handleMessage(Intent intent) {
			// server sent 2 key-value pairs, score and time
			String score = intent.getStringExtra("score");
			String time = intent.getStringExtra("time");
			String msg = intent.getStringExtra("msg");
			// generates a system notification to display the score and time
		}
		
}
	