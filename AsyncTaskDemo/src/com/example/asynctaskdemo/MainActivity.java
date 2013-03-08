package com.example.asynctaskdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String URL = "http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=en&username=sheikh.aman";
	ProgressBar progressBar;
	TextView statusText;
	Button buttonStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressBar = (ProgressBar) findViewById(R.id.progress);
		statusText = (TextView) findViewById(R.id.status_textview);
		buttonStart = (Button) findViewById(R.id.btn_start);
	}

	public void startLongRunningOperation(View v) {
		new NetTask().execute();
	}

	/**
	 * AsyncTask class that handles all the network activity in the background.
	 * 
	 * @author aman
	 * 
	 */
	class NetTask extends AsyncTask<Void, Void, String> {

		@Override
		public void onPreExecute() {
			buttonStart.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
		}

		/**
		 * This method is executed in the background, inside another thread
		 */
		@Override
		protected String doInBackground(Void... params) {
			String results = "";
			try {
				Log.d("AsyncTaskDemo", "Doing Something Expensive");
				URL url = new URL(URL);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String line = "";
				while ((line = br.readLine()) != null) {
					results += line;
				}
			} catch (MalformedURLException e) {
				Log.e(MainActivity.this.getPackageName() + "."
						+ getClass().getName(), e.getMessage());
			} catch (IOException e) {
				Log.e(MainActivity.this.getPackageName() + "."
						+ getClass().getName(), e.getMessage());
			}
			return results;
		}

		/**
		 * This method is executed on the UI Thread, when the task is cancelled
		 */
		@Override
		protected void onCancelled() {
			Log.d("AsyncTaskDemo", "Cancelling AsyncTask");
		}

		/**
		 * This method is executed on the UI Thread, when the AsyncTask finishes
		 * working
		 */
		@Override
		protected void onPostExecute(String results) {
			progressBar.setVisibility(View.GONE);
			buttonStart.setVisibility(View.VISIBLE);

			statusText.setText(results);
			Log.d("AsyncTaskDemo", "Results: " + results);

		}
	}

}
