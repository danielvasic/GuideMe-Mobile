package com.teamcode.info.guideme;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.teamcode.info.guideme.Controller.Articles;
import com.teamcode.info.guideme.Controller.Audios;
import com.teamcode.info.guideme.Controller.Images;
import com.teamcode.info.guideme.Controller.PlaceTypes;
import com.teamcode.info.guideme.Controller.Places;
import com.teamcode.info.guideme.Controller.Users;
import com.teamcode.info.guideme.Controller.Videos;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	String API_LINK = "http://tkordic.net/api/v1/";	
	Integer[] difer = new Integer[7]; int howMuch=0; 
	String updateResult;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        createDirs();
        
        final Button mapsBtn = (Button) findViewById(R.id.mapsBtn);
        final Button locationsBtn = (Button) findViewById(R.id.locationsBtn);
        final Button routeBtn = (Button) findViewById(R.id.routeBtn);
        final Button arBtn = (Button) findViewById(R.id.arBtn);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        mapsBtn.setOnClickListener(new Button.OnClickListener() {  
			@Override
			public void onClick(View v) {				
				//Intent i = new Intent(MainActivity.this, MapsActivity.class);
				//startActivity(i);
			}
        });
    
        locationsBtn.setOnClickListener(new Button.OnClickListener() {  
			@Override
			public void onClick(View v) {				
				Intent i = new Intent(MainActivity.this, PlaceTypesActivity.class);
				startActivity(i);
			}
        });
        
        routeBtn.setOnClickListener(new Button.OnClickListener() {  
			@Override
			public void onClick(View v) {				
				uIzradi("Plan Route");
			}
        });
        
        arBtn.setOnClickListener(new Button.OnClickListener() {  
			@Override
			public void onClick(View v) {				
				uIzradi("AR");
			}
        });
        
        //Check for new UPDATES
       try {    	
    	    Log.wtf("Check updates","Started");
        	Boolean update = new checkUpdatesAsyncTask().execute().get();        	
	        if (update.booleanValue()){
	            showAlertDialog(MainActivity.this, "Synchronization",
	                    "Do you want to sync database?\nNumber of new places is: " + howMuch, true, howMuch);
	            howMuch=0;
	        }	   
	        Log.wtf("Check updates","Ended");
        } 
       
       catch (Exception e) { 
        	Toast.makeText(getApplicationContext(), "You are probbably not connected to internet", Toast.LENGTH_SHORT).show();
        	Log.wtf("Check updates", "Problem - Probabbly not connected to internet.");
        }
       
    }

    
    public class checkUpdatesAsyncTask extends AsyncTask<Void, Integer, Boolean>    {
        
    	checkUpdatesAsyncTask (){ } 	//CONSTRUCTOR    	
    	boolean update;
        @Override
        protected void onPreExecute()
        {            	
        	 Log.i("Check updates", "Pre execute");
        }

		@Override
		protected Boolean doInBackground(Void... params) {
			synchronized (this) {
			// TODO Auto-generated method stub
			update = checkUpdates();
			}
			 Log.i("Check updates", "second");
			return update;
		}
		
		protected boolean onPostExecute(){
			super.onPostExecute(update);
			 Log.i("Check updates", "On post");
			return update;			
		}
		
		//function indicator of update (is needed or not)
		public boolean checkUpdates (){
	    	Integer[] difer = diference1(); 			// Call funtion diference and store the result in array 'difer'   	
	    	
	    	boolean update = false; 
	    	for (int j = 0; j < 7; j++){
	    		howMuch+=difer[j];					 // Global variable howMuch shows how much new items exist on server
	    		if (difer[j] > 0) update = true; 	 // IF any of tables are not equal locally and on server, then do the update
	    		}
	    	return update;
	    }
	    
	    public Integer[] diference(){
	    	int i=0;
	    	
	    	Integer[] counter = numOfRowsTableLocal();    	

	    	difer[i] = numOfRowsTableServer("articles", "article") - counter[i];    	i++; 	         	
	    	difer[i] = numOfRowsTableServer("audio", "audio") - counter[i];			    i++;
	    	difer[i] = numOfRowsTableServer("images", "images") - counter[i];			i++;
	    	difer[i] = numOfRowsTableServer("places", "place") - counter[i];			i++;
	    	difer[i] = numOfRowsTableServer("place_types", "place_type") - counter[i];	i++;
	    	difer[i] = numOfRowsTableServer("users", "user") - counter[i];				i++;
	    	difer[i] = numOfRowsTableServer("video", "video") - counter[i];     
	    	
	    	return difer;
	    }
	    
	    
	    
	  /*  public Integer[] diferr(){
	    	int i=0;
	    	Integer[] counter = numOfRowsTableLocal(); 
	    	numOfRows();
			JSONObject jObject = new JSONObject(result);
	        JSONArray jArray = jObject.getJSONArray(arrayName);   
			return difer;	    	
	    }
	    
	    public void numOfRows() {
	        String result = getPage(API_LINK + "/?count");	        
	        result = "{resources:" + result + "}";
	        
	        try {
				obj = new JSONObject(result);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	                    
	        return;
	    }
	        
*/	    
	    
	    public Integer[] diference1(){
	    	int i=0;
	    	updateResult = getPage(API_LINK + "?count");
	    	
	    	Integer[] counter = numOfRowsTableLocal();    	

	    	difer[i] = numOfRowsTableServer2("articles") - counter[i];    	i++; 	         	
	    	difer[i] = numOfRowsTableServer2("audio") - counter[i];			    i++;
	    	difer[i] = numOfRowsTableServer2("images") - counter[i];			i++;
	    	difer[i] = numOfRowsTableServer2("places") - counter[i];			i++;
	    	difer[i] = numOfRowsTableServer2("place_types") - counter[i];	i++;
	    	difer[i] = numOfRowsTableServer2("users") - counter[i];				i++;
	    	difer[i] = numOfRowsTableServer2("video") - counter[i];     
	    	
	    	return difer;
	    }
	    
	    public int numOfRowsTableServer2(String tablename){           
	    	try {     
                JSONObject jObject = new JSONObject(updateResult);
                JSONArray jArray = jObject.getJSONArray("resources");
                JSONObject objekt;
                for (int i = 0; i < jArray.length(); i++) {
                    objekt = jArray.getJSONObject(i);
				    if (objekt.getString("name").equals(tablename)) {				    	
				    	return objekt.getInt("count");
				    }                 
                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
			return 0;   
	    }
	    
	    public Integer[] numOfRowsTableLocal(){
	        Integer[] zadnji = new Integer[7];
	        
	        Articles articles = new Articles(getApplicationContext());
	        Audios audios = new Audios(getApplicationContext());
	        Images images = new Images(getApplicationContext());
	        Places places = new Places(getApplicationContext());
	        PlaceTypes placeTypes = new PlaceTypes(getApplicationContext());
	        Users users = new Users(getApplicationContext());
	        Videos videos = new Videos(getApplicationContext());
	        
	        try { zadnji[0] = articles.ArticleTableCount(); } catch (Exception e) {zadnji[0]=0;}
	        try { zadnji[1] = audios.AudioTableCount(); } catch (Exception e) {zadnji[1]=0;}
	        try { zadnji[2] = images.ImageTableCount(); } catch (Exception e) {zadnji[2]=0;}
	        try { zadnji[3] = places.PlaceTableCount(); } catch (Exception e) {zadnji[3]=0;}
	        try { zadnji[4] = placeTypes.PlaceTypeTableCount(); } catch (Exception e) {zadnji[4]=0;}
	        try { zadnji[5] = users.UserTableCount(); } catch (Exception e) {zadnji[5]=0;}
	        try { zadnji[6] = videos.VideoTableCount(); } catch (Exception e) {zadnji[6]=0;}
	        	
	        return zadnji;
	    }

	    
	    public int numOfRowsTableServer(String page, String arrayName) {
	        String result = getPage(API_LINK + page + "/count");
	        
	        result = "{" + arrayName + ":[" + result + "]}";
	        int count=0;
	                    
	    		try {
	    			JSONObject objekt;		
	    			JSONObject jObject = new JSONObject(result);
	    	        JSONArray jArray = jObject.getJSONArray(arrayName);   
	    			objekt = jArray.getJSONObject(0);
	    			count = objekt.getInt("count");
	    		} catch (JSONException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	                    
	        return count;
	    }	    
    

	    private String getPage (String url) {
	    	DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());	HttpGet httppost;  
	        Log.i("URL = ", url);
	            httpclient = new DefaultHttpClient(new BasicHttpParams());
	            httppost = new HttpGet(url);
	            // Depends on your web service

	            InputStream inputStream = null;
	            String result = null;
	            try {
	                HttpResponse response = httpclient.execute(httppost);
	                HttpEntity entity = response.getEntity();
	                inputStream = entity.getContent();
	                // json is UTF-8 by default
	                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
	                StringBuilder sb = new StringBuilder();
	                String line = null;
	                while ((line = reader.readLine()) != null)
	                {
	                    sb.append(line + "\n");
	                }
	                result = sb.toString();
	                return result;
	            } catch (Exception e) {
	                e.printStackTrace();

	            }
	            finally {
	                try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
	            }
	            return result;
	        }
    }

	public void createDirs(){
		String[] folderName = {"audio", "video", "images", "other"};
		File folder;
		try {
	    String intStorageDirectory = getFilesDir().toString();
		    for (int i=0;i<folderName.length;i++){
			    folder = new File(intStorageDirectory, folderName[i]);			    
			    if (!folder.exists()) folder.mkdirs(); // check is there folder created already, if not, create it
		    }
	    }
		catch (Exception e){
			Log.i("createDirs","Problem with creating dirs. StackTrace = " + e.toString());
		}
	}
    
	
    public void uIzradi(String opt){
    	Toast.makeText(getApplicationContext(), "Option " + opt + " is not available yet.", Toast.LENGTH_LONG).show();
    }
    
    @SuppressWarnings("deprecation")
    public void showAlertDialog(final Context context, String title, String message, Boolean status, final int broj) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setIcon((status) ? R.drawable.ic_success : R.drawable.ic_fail);
        alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                updateDialog(context, "Update/Reload","Do you want to nadopunit data or download all again ?", true, broj);
            }
        });
        alertDialog.setButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    @SuppressWarnings("deprecation")
    public void updateDialog(Context context, String title, String message, Boolean status, final int broj) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setIcon((status) ? R.drawable.ic_success : R.drawable.ic_fail);
        alertDialog.setButton2("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
        		Intent i = new Intent(MainActivity.this, SynchronizeTask.class);
        			i.putExtra("update", "true"); 
        		startActivity(i);
            }
        });
        alertDialog.setButton("Reload", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
	        	Intent i = new Intent(MainActivity.this, SynchronizeTask.class);
	        		i.putExtra("update", "false"); 
        		startActivity(i);
            }
        });
        alertDialog.show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_sync) {
        	Intent i = new Intent(MainActivity.this, SynchronizeTask.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
