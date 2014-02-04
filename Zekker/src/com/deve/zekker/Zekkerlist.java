package com.deve.zekker;

import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Zekkerlist extends ListActivity {
	ArrayList <String> data = new ArrayList<String>();
	ArrayList <String> dataonlynames = new ArrayList<String>();
	SharedPreferences saveddata;
	ArrayAdapter<String>	myadapter;
	@Override
         protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zekkerlist);
        saveddata= getSharedPreferences("data", 0);
        int cnt =  saveddata.getInt("cnt", 0);
        Log.d(""+cnt, ""+cnt);
        for (int i=1;i<=cnt;i++)
            data.add(saveddata.getString("name-"+i, "")+ "-" +saveddata.getInt("count-"+i, 0)+ "-" +saveddata.getInt("id-"+i, 0));
        for (int i=1;i<=cnt;i++)
            if (data.contains("-0-0"))
                data.remove("-0-0");
        for (int i=0;i<cnt;i++)
        {
            String []a = data.get(i).split("-");
            dataonlynames.add(a[0]);
        }
        myadapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,dataonlynames);
        setListAdapter(myadapter);
        registerForContextMenu(getListView());
    }
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Log.d("position", position+"");
		openContextMenu(v);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		 if (v.getId()==getListView().getId()) {
			    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			    menu.setHeaderTitle(v.getId());
			    String[] menuItems = {" ﬂ„Ì·","Õ–›","≈⁄«œ… «· ”„Ì…"};
			    for (int i = 0; i<menuItems.length; i++) {
			      menu.add(Menu.NONE, i, i, menuItems[i]);
			    }
			  }
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    int index = info.position;
		if (item.getItemId()==0)
				{
					Intent intent = new Intent(this,MainActivity.class);
					intent.putExtra("fulldata", data.get(index));
					startActivity(intent);
				}
		else if (item.getItemId()==1)
			{
				String obj [] = data.get(index).split("-");
                Toast.makeText(this.getApplicationContext(),obj[0]+obj[1]+obj[2],Toast.LENGTH_LONG).show();
				SharedPreferences.Editor editdata = saveddata.edit();
				editdata.remove("name-"+obj[2]);
				editdata.remove("id-"+obj[2]);
				editdata.remove("count-"+obj[2]);
				editdata.commit();
				refreshList();
			}
		else if (item.getItemId()==2)
			{
				AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
				alert.setMessage("");
				EditText tmp = new EditText(this);
				String value = data.get(index);
	            final String obj [] = value.split("-");
				tmp.setText(obj[0]);
				final EditText input = tmp; 
				alert.setView(input);
				input.setText(obj[0]);
			    alert.setPositiveButton("Õ›Ÿ", new DialogInterface.OnClickListener() {  
			    public void onClick(DialogInterface dialog, int whichButton) {  
			        String value = input.getText().toString();
			        Log.d( "", "Pin Value : " + value);
			        SharedPreferences.Editor editor =  saveddata.edit();
			        editor.putString("name-"+obj[2], value);
			        editor.commit();
			        refreshList();
			        return;                  
			       }  
			     });  
			    alert.setNegativeButton("—ÃÊ⁄", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			            // TODO Auto-generated method stub
			            return;   
			        }
			    });	
			    alert.show();
			}
		return super.onContextItemSelected(item);
	}
	void refreshList ()
	{
		Intent in = new Intent(this,Zekkerlist.class);
		startActivity(in);
		Thread T  = new Thread (new Runnable() {
			@Override
			public void run() {
				Zekkerlist.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
					myadapter.notifyDataSetChanged();
					return;
					}
				});
				run();
			}
		});
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
	
}
