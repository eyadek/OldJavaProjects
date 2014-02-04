package com.deve.zekker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Settings extends Activity {
	RadioButton myOption1, myOption2;
	 SharedPreferences settingsa;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
	      myOption1 = (RadioButton)findViewById(R.id.radioButton1);
	      myOption2 = (RadioButton)findViewById(R.id.radioButton2);
	      settingsa= getSharedPreferences("Settings", 0);
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
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent (Settings.this,MainActivity.class);
		startActivity(i);
	}
			
}