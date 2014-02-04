package com.deve.zekker;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.projectzekker.R;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainZekker extends SherlockFragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	int currentFragment;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mFragmentTitles;
	 Fragment        fragment;
	 FragmentManager fm ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 fm       = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.content_frame);
        
		mTitle = mDrawerTitle = getTitle();
		mFragmentTitles = getResources().getStringArray(R.array.fragments);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
		mDrawerList = (ListView)findViewById(R.id.drawer_list);
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mFragmentTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){
			public void onDrawerClosed(View v){
				getSupportActionBar().setTitle(mTitle);
				getSupportActionBar().setIcon(R.drawable.ic_launcher);
				supportInvalidateOptionsMenu();
			}
			public void onDrawerOpened(View v){
				getSupportActionBar().setTitle(mDrawerTitle);
				getSupportActionBar().setIcon(R.drawable.ic_launcher);
				supportInvalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null){
			selectItem(0);
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent arg2) {
		// TODO Auto-generated method stub
		
		if (currentFragment==2)
		{	Zekker tmp = new Zekker();
			if (fragment == null) 
			{    
				FragmentTransaction ft = fm.beginTransaction();
            	ft.add(R.id.content_frame, tmp);
            	ft.replace(R.id.content_frame, tmp).commit();
			}
			
			
			if (arg2.getKeyCode() == KeyEvent.KEYCODE_BACK)
			{
				tmp.counterListener(1);
			}
    	
    		if ((arg2.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN)) {  
    			tmp.counterListener(2);
    	    	return true; 
    		}
    		if ((arg2.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP)) {
    			
    			tmp.counterListener(3);
    		}
    	
    }
    	return super.onKeyDown(keyCode, arg2);      
    	}
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu){
//		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//		menu.
//		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
//		return super.onPrepareOptionsMenu(menu);
//	}

	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getSupportMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			if (mDrawerLayout.isDrawerOpen(mDrawerList)){
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			return true;
	///	case R.id.action_settings:
			//Intent i = new Intent(MainActivity.this, Sources.class);
		//	startActivity(i);
		//	return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position, long id){
			selectItem(position);
		}
	}
	
	private void selectItem(int position){
		Fragment newFragment = new Fragment();
		FragmentManager fm = getSupportFragmentManager();
		switch(position){
		case 0:
			newFragment = new MyProfile();
			currentFragment=0;
			break;
		case 1:
			newFragment = new MyZekker();
			currentFragment=1;
			break;
		case 2:
			newFragment = new Zekker();
			currentFragment=2;
			
			break;
		case 3:
			newFragment = new Search();
			currentFragment=3;
			break;
		case 4:
			newFragment = new CreateZekker();
			currentFragment=4;
			break;
		case 5:
			newFragment = new Settings();
			currentFragment=5;
			break;
		}
		fm.beginTransaction().replace(R.id.content_frame, newFragment).commit();
		
		mDrawerList.setItemChecked(position, true);
		setTitle(mFragmentTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	
	@Override
	public void setTitle(CharSequence title){
		mTitle = title;
		getSupportActionBar().setTitle(title);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
