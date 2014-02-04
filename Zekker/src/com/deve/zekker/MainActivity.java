package com.deve.zekker;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

public class MainActivity extends Activity {
    int i=0;
    TextView counter ;
    SharedPreferences data;
    int cnt;
    int vibrateVar;
    TextView name;
    String obj[];
    Button cntbtn;
    int mode;
    
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams screen = getWindow().getAttributes();
        float z =screen.screenBrightness;
       
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        mode=settings.getInt("mode", 0);

        if (mode==0)
        	setContentView(R.layout.main);
        else
        	{
        		setContentView(R.layout.main2);
        		cntbtn = (Button) findViewById(R.id.button1);
        	}
        
         counter=(TextView) findViewById(R.id.tv1);
          data = getSharedPreferences("data", 0);
          cnt = data.getInt("cnt", 0);
          Log.d(data.getString("name-1","" ), data.getInt("count-1",0 )+"");
          counter.setText(i+"");
           name = (TextView) findViewById(R.id.tv2);
          Bundle extras = getIntent().getExtras();
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
  			    	    		Vibrator vibe = (Vibrator) getSystemService(MainActivity.this.VIBRATOR_SERVICE);
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

       
    }
    @Override
    public void onBackPressed() {
    //    Toast.makeText(this,"hhhsshshshshshshshshshsh",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(intent);

        finish();
    }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	super.onKeyUp(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
          //  Toast.makeText(this,"TOASTTTTT hhhsshshshshshshshshshsh",Toast.LENGTH_LONG).show();


           this.finish();
        }
    	if (mode==0)
    	{ 
    		if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {  
    	    i++;
    	    counter.setText(i+"");
    	    vibrateVar =data.getInt("vibrateOn", 0);
    	    if (vibrateVar==i)
    	    	{
    	    		Vibrator vibe = (Vibrator) getSystemService(MainActivity.this.VIBRATOR_SERVICE);
    	    		vibe.vibrate(1000);
    	    	}
    	    	return true; 
    		}
    		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
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
   @Override
public boolean onMenuItemSelected(int featureId, MenuItem item) {
	if (item.getItemId()== R.id.vibrateOn)
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
		alert.setTitle("الإهتزاز عند");  
		alert.setMessage("الإهتزاز عند الوصول إالى:");                

		 // Set an EditText view to get user input   
		  EditText input1 = new EditText(this); 
		 input1.setText(data.getInt("vibrateOn", 0)+"");
		 final EditText input = input1;
		 alert.setView(input);
		    alert.setPositiveButton("حفظ", new DialogInterface.OnClickListener() {  
		    public void onClick(DialogInterface dialog, int whichButton) {  
		        String value = input.getText().toString();
		        Log.d( "", "Pin Value : " + value);
		        SharedPreferences.Editor editor = data.edit();
		        editor.putInt("vibrateOn", Integer.parseInt(value));
		        editor.commit();
		        return;                  
		       }  
		     });  

		    alert.setNegativeButton("رجوع", new DialogInterface.OnClickListener() {

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
		AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
		alert.setMessage("العنوان");  
		if (name.getText()!=null && name.getText()!="")
	    	{
			EditText tmp = new EditText(this);
			tmp.setText(obj[0]);
			final EditText input = tmp; 
			alert.setView(input);
		    alert.setPositiveButton("حفظ", new DialogInterface.OnClickListener() {  
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
		    alert.setNegativeButton("رجوع", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		            // TODO Auto-generated method stub
		            return;   
		        }
		    });
	    	}
		else{           
		final EditText input = new EditText(this); 
		alert.setView(input);
	    alert.setPositiveButton("حفظ", new DialogInterface.OnClickListener() {  
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

	    alert.setNegativeButton("رجوع", new DialogInterface.OnClickListener() {

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
		Intent intent = new Intent(this, Zekkerlist.class);
		
		startActivity(intent);
		
	}
	if (item.getItemId()== R.id.settings)
	{
		Intent i= new Intent(this, Settings.class);
		startActivity(i);
	}
	return super.onMenuItemSelected(featureId, item);
}
 
}
