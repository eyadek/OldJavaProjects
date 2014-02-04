package com.deve.zekker;

import com.example.projectzekker.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;



public class Settings extends Fragment {
	View fragmentView;
	RadioButton myOption1, myOption2;
	 SharedPreferences settingsa;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		fragmentView= new View(getActivity());
		fragmentView=inflater.inflate( R.layout.settings,container, false);
	      myOption1 = (RadioButton)fragmentView.findViewById(R.id.radioButton1);
	      myOption2 = (RadioButton)fragmentView.findViewById(R.id.radioButton2);
	      settingsa= getActivity().getSharedPreferences("Settings", 0);
	      if (settingsa.getInt("mode", 0)==0)
	    	  myOption1.setChecked(true);
	      else
	    	  myOption2.setChecked(true);	
	      myOption1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				
				
					SharedPreferences.Editor ed =   settingsa.edit();
					ed.putInt("mode", 0);
					myOption2.setChecked(false);
					myOption1.setChecked(true);
					ed.commit();
				
			}
		});
	      myOption2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					
						SharedPreferences.Editor ed =   settingsa.edit();
						ed.putInt("mode", 1);
						myOption1.setChecked(false);
						myOption2.setChecked(true);
						ed.commit();
					
				}
			});
	      container.addView(fragmentView);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
