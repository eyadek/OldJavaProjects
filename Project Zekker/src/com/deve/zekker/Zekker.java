package com.deve.zekker;

import com.example.projectzekker.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class Zekker extends Fragment {
	  int i=0;
	    TextView counter ;
	    SharedPreferences data;
	    int cnt;
	    int vibrateVar;
	    TextView name;
	    String obj[];
	    Button cntbtn;
	    int mode;
	    View fragmentView ;
	    OnKeyListener myLisntener;
	
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    		Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        WindowManager.LayoutParams screen = getActivity().getWindow().getAttributes();
	        setHasOptionsMenu(true);
	        SharedPreferences settings = getActivity().getSharedPreferences("Settings", 0);
	        fragmentView= new View(getActivity());
	        mode=settings.getInt("mode", 0);
	        if (mode==0)
	        	{
	        	fragmentView= inflater.inflate(R.layout.main, container, false);
	        	}
	        else
	        	{
	        	fragmentView = inflater.inflate(R.layout.main2, container, false);
	        	cntbtn = (Button) fragmentView.findViewById(R.id.button1);
	        	}
	        
	         counter=(TextView) fragmentView.findViewById(R.id.tv1);
	         counter.setText("ta7ssen2");
	          data = getActivity().getSharedPreferences("data", 0);
	          cnt = data.getInt("cnt", 0);
	          Log.d(data.getString("name-1","" ), data.getInt("count-1",0 )+"");
	          counter.setText(i+"");
	           name = (TextView) fragmentView.findViewById(R.id.tv2);
	          Bundle extras = getActivity().getIntent().getExtras();
	          if (extras != null) {
	              String value = extras.getString("fulldata");
	               obj = value.split("-");
	              name.setText(obj[0]);
	              i=Integer.parseInt(obj[1]);
	              counter.setText(i+"");
	          }
	          if (mode==1)
	          {
	        	  cntbtn.setOnClickListener(new View.OnClickListener() {
	  				@Override
	  				public void onClick(View arg0) {
	  					// TODO Auto-generated method stub
	  						i++;
	  			    	    counter.setText(i+"");
	  			    	    vibrateVar =data.getInt("vibrateOn", 0);
	  			    	    if (vibrateVar==i)
	  			    	    	{
	  			    	    		Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
	  			    	    		vibe.vibrate(1000);
	  			    	    	}
	  				}
	  			});
	      		cntbtn.setOnLongClickListener(new View.OnLongClickListener() {
	  				@Override
	  				public boolean onLongClick(View arg0) {
	  		    		if(i-1 > -1)
	  	    			{
	  	    		   		i--;
	  	    		   		counter.setText(i+"");
	  	    		   		return true; 
	  	    	    	}
	  					return false;
	  				}
	  			});
	          }
	        
/*	          myLisntener= new OnKeyListener() {
	  			
	  		    @Override
	  		    public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
	  		        if (arg2.getKeyCode() == KeyEvent.KEYCODE_BACK)
	  		        {
	  		            Toast.makeText(getActivity(),"TOASTTTTT hhhsshshshshshshshshshsh",Toast.LENGTH_LONG).show();
	  		        	getActivity().finish();
	  		        }
	  		    	if (mode==0)
	  		    	{ 
	  		    		if ((arg2.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP)) {  
	  		    	    i++;
	  		    	    counter.setText(i+"");
	  		    	    vibrateVar =data.getInt("vibrateOn", 0);
	  		    	    if (vibrateVar==i)
	  		    	    	{
	  		    	    		Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
	  		    	    		vibe.vibrate(1000);
	  		    	    	}
	  		    	    	return true; 
	  		    		}
	  		    		if ((arg2.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN)) {
	  		    		if(i-1 > -1)
	  		    			{
	  		    		   		i--;
	  		    		   		counter.setText(i+"");
	  		    		   		return true; 
	  		    	    	}
	  		    		}
	  		    	}
	  		    	return false;      
	  		    	}
	  		};
  		fragmentView.setOnKeyListener(myLisntener);*/
	          container.addView(fragmentView);
	         counter.setText("this is counter");
	      
	          return super.onCreateView(inflater, container, savedInstanceState);
	    }
	    
//	    @Override
//	    public void onBackPressed() {
//	    //    Toast.makeText(this,"hhhsshshshshshshshshshsh",Toast.LENGTH_LONG).show();
//	        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
//	        startActivity(intent);
//
//	        finish();
//	    }

	    

	   
	    @Override
	    public void   onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    	super.onCreateOptionsMenu(menu, inflater);
	    	inflater.inflate(R.menu.main_menu, menu);
	        
	    }
	    
	    

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	// TODO Auto-generated method stub
		if (item.getItemId()== R.id.vibrateOn)
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());                 
			alert.setTitle("ÇáÅåÊÒÇÒ ÚäÏ");  
			alert.setMessage("ÇáÅåÊÒÇÒ ÚäÏ ÇáæÕæá ÅÇáì:");                
			 // Set an EditText view to get user input   
			  EditText input1 = new EditText(getActivity()); 
			 input1.setText(data.getInt("vibrateOn", 0)+"");
			 final EditText input = input1;
			 alert.setView(input);
			    alert.setPositiveButton("ÍÝÙ", new DialogInterface.OnClickListener() {  
			    public void onClick(DialogInterface dialog, int whichButton) {  
			        String value = input.getText().toString();
			        Log.d( "", "Pin Value : " + value);
			        SharedPreferences.Editor editor = data.edit();
			        editor.putInt("vibrateOn", Integer.parseInt(value));
			        editor.commit();
			        return;                  
			       }  
			     });  
			    alert.setNegativeButton("ÑÌæÚ", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			            // TODO Auto-generated method stub
			            return;   
			        }
			    });
			            alert.show();
		}
		if (item.getItemId()== R.id.startOver)
		{
			i=0;
			counter.setText(i+"");
			name.setText(null);
			obj=null;
		}
		if (item.getItemId()== R.id.save)
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());								
			alert.setMessage("ÇáÚäæÇä");  
			if (name.getText()!=null && name.getText()!="")
		    	{
				EditText tmp = new EditText(getActivity());
				tmp.setText(obj[0]);
				final EditText input = tmp; 
				alert.setView(input);
			    alert.setPositiveButton("ÍÝÙ", new DialogInterface.OnClickListener() {  
			    public void onClick(DialogInterface dialog, int whichButton) {  
			        String value = input.getText().toString();
			        Log.d( "", "Pin Value : " + value);
			        SharedPreferences.Editor editor = data.edit();
			        editor.putString("name-"+obj[2], value);
			        editor.putInt("count-"+obj[2], i);
			        editor.putInt("id-"+obj[2], Integer.parseInt(obj[2]));
			        editor.commit();
			        return;                  
			       }  
			     });  
			    alert.setNegativeButton("ÑÌæÚ", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			            return;   
			        }
			    });
		    	}
			else{           
			final EditText input = new EditText(getActivity()); 
			alert.setView(input);
		    alert.setPositiveButton("ÍÝÙ", new DialogInterface.OnClickListener() {  
		    public void onClick(DialogInterface dialog, int whichButton) {  
		        String value = input.getText().toString();
		        Log.d( "", "Pin Value : " + value);
		        SharedPreferences.Editor editor = data.edit();
		        cnt++;
		        editor.putString("name-"+cnt, value);
		        editor.putInt("count-"+cnt, i);
		        editor.putInt("id-"+cnt, cnt);
		        editor.putInt("cnt", cnt);
		        editor.commit();
		        return;                  
		       }  
		     });  

		    alert.setNegativeButton("ÑÌæÚ", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		            // TODO Auto-generated method stub
		            return;   
		        }
		    });}
		            alert.show();
		}
		if (item.getItemId()== R.id.azkare)
		{
			Log.d("count",""+ data.getInt("cnt", 0));
			Intent intent = new Intent(getActivity(), MyZekker.class);
			startActivity(intent);
		}
		if (item.getItemId()== R.id.settings)
		{
			Intent i= new Intent(getActivity(), Settings.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
public void counterListener(int ss)
{
	
	if (mode==0)
	switch (ss) {
	case 1: //back button

		break;
	case 2: //Volume Down button
		if(i-1 > -1)
		{
	   		i--;
	   		setCounter(String.valueOf(i)); 
    	}
		break;
	case 3: //volume up button
		 i++;
		//TextView v =(TextView) getView().findViewById(R.id.tv1);
		setCounter(String.valueOf(i));
 	    vibrateVar =data.getInt("vibrateOn", 0);
 	    if (vibrateVar==i)
 	    	{
 	    		Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
 	    		vibe.vibrate(1000);
 	    	}
		break;
	default:
		break;
	}
}
void setCounter (String s)
{
	
//	FragmentManager fragmentManager = getFragmentManager();
//	Fragment zeker =  fragmentManager.findFragmentByTag("content_frame");
//	if (zeker == null)
//	{	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//	
//	fragmentTransaction.replace(R.id.content_frame, new Zekker());
//	fragmentTransaction.commit();
//	Toast.makeText(getActivity(), "this", Toast.LENGTH_LONG).show();
//	}
//	else
//		Toast.makeText(getActivity(), "notthis", Toast.LENGTH_LONG).show();
//	if (getView()==null)
//	{  FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//
//      ft.replace(R.id.content_frame, new Zekker()).commit();
//      ft.commit();}
	Zekker t = new Zekker();
	
((TextView)t.getView().findViewById(R.id.tv1)).setText(s);
}

}
