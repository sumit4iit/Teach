package com.example.handlerdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String URL = "http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=en&username=sheikh.aman";
	private String results = "";
	ProgressBar progressBar;
	TextView statusText;
	Button buttonStart;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        progressBar = (ProgressBar)findViewById(R.id.progress);
        statusText = (TextView)findViewById(R.id.status_textview);
        buttonStart = (Button)findViewById(R.id.btn_start);
    }
    
    	    // Need handler for callbacks to the UI thread
    	    final Handler mHandler = new Handler();

    	    // Create runnable for posting
    	    final Runnable mUpdateResults = new Runnable() {
    	        public void run() {
    	            updateResultsInUi();
    	        }
    	    };


    	    public void startLongRunningOperation(View v) {
    	    	
    	    	buttonStart.setVisibility(View.GONE);
    	    	progressBar.setVisibility(View.VISIBLE);
    	    	
    	        // Fire off a thread to do some work that we shouldn't do directly in the UI thread
    	        Thread t = new Thread() {
    	            public void run() {
    	                doSomethingExpensive();
    	                mHandler.post(mUpdateResults);
    	            }
    	        };
    	        t.start();
    	    }

    	    private void updateResultsInUi() {

    	        // Back in the UI thread -- update our UI elements based on the data in mResults
    	    	
    	    	progressBar.setVisibility(View.GONE);
    	    	buttonStart.setVisibility(View.VISIBLE);
    	    	statusText.setText(results);
    	    	results = "";
    	    	Log.d("HandlerSample","Updating UI"+results);
    	    }
    	    
    	    public void doSomethingExpensive(){
    	    	
    	    	try {
    	    		Log.d("HandlerSample","Doing Something Expensive");
					URL url = new URL(URL);
					HttpURLConnection con = (HttpURLConnection)url.openConnection();
					BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String line = "";
					while((line = br.readLine())!=null){
						results+=line;
					}
				} catch (MalformedURLException e) {
					Log.e(this.getPackageName()+"."+getClass().getName(),e.getMessage());
				} catch (IOException e) {
					Log.e(this.getPackageName()+"."+getClass().getName(),e.getMessage());
				}
    	    	
    	    }
    
}
