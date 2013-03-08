package com.example.fragmentsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

public class StaticFragmentDemoActivity extends FragmentActivity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void showOther(View v){
		Log.d("OTHER ACTIVITY CALLED","OTHER ACTIVITY CALLED");
		Intent i = new Intent(this, OtherActivity.class);
		startActivity(i);
	}
}
