package com.example.fragmentsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContentFragment extends Fragment implements View.OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View result = inflater.inflate(R.layout.mainfrag, container);
		result.findViewById(R.id.btn_mainfrag).setOnClickListener(this);
		return result;
	}

	@Override
	public void onClick(View v) {
		((StaticFragmentDemoActivity)getActivity()).showOther(v);
		
	}

}
