package com.example.androidservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{
	
	@Override
	public void onCreate(){
		super.onCreate();
		Log.d("MY_SERVICE","SERVICE CREATED");
	}

	//called when the client starts service via startService()
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Log.d("MY_SERVICE","SERVICE STARTED");
		
		for(int i=1;i<10;i++){
			Log.d("MY_SERVICE","SERVICE DOING SOME WORK");
		}
		
		stopSelf();
		//Tells the system to stop service as soon as it is not needed
		return Service.START_NOT_STICKY;
	}
	
	//called when the client starts the service via bindService()
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d("MY_SERVICE","SERVICE DESTROYED");
	}

}
