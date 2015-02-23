package com.teamcode.info.guideme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

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
import com.teamcode.info.guideme.Model.Article;
import com.teamcode.info.guideme.Model.Audio;
import com.teamcode.info.guideme.Model.Image;
import com.teamcode.info.guideme.Model.Place;
import com.teamcode.info.guideme.Model.PlaceType;
import com.teamcode.info.guideme.Model.User;
import com.teamcode.info.guideme.Model.Video;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SynchronizeTask extends ActionBarActivity {
	String API_LINK = "http://tkordic.net/api/v1/";	
	int howMuch=0; Integer[] difer = new Integer[7];
	private ProgressDialog progressDialog;
	DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
	HttpGet httppost;    HttpResponse response;   HttpEntity entity; BufferedReader reader; StringBuilder sb;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronize); 

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        String updateSt = null;
        try {
        Bundle extras = getIntent().getExtras();
        updateSt = extras.getString("update"); }
        catch (Exception e) {Log.wtf("Crash - SyncTask", "Put extra before calling intent."); updateSt="false";}
       
        boolean update = true; if (updateSt.equals("false")) update = false; 

        new SynchronizeAsyncTask(update).execute();     
}
    
    public void diference(){
    	int i=0;
    	Integer[] counter = numOfRowsTableLocal();    	

    	difer[i] = numOfRowsTableServer("articles", "article") - counter[i];    	i++; 	         	
    	difer[i] = numOfRowsTableServer("audio", "audio") - counter[i];			    i++;
    	difer[i] = numOfRowsTableServer("images", "images") - counter[i];			i++;
    	difer[i] = numOfRowsTableServer("places", "place") - counter[i];			i++;
    	difer[i] = numOfRowsTableServer("place_types", "place_type") - counter[i];	i++;
    	difer[i] = numOfRowsTableServer("users", "user") - counter[i];				i++;
    	difer[i] = numOfRowsTableServer("video", "video") - counter[i];         	
    }
    

public class SynchronizeAsyncTask extends AsyncTask<Void, Integer, Void>    {
   
	boolean update=false;
	int niz=0;
    
	SynchronizeAsyncTask (boolean update){
	    this.update=update;
	}

    @Override
    protected void onPreExecute()
    {    	
    	Log.wtf("Start","Connection open.");
        progressDialog = new ProgressDialog(SynchronizeTask.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Downloading...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    //The code to be executed in a background thread.
    @Override
    protected Void doInBackground(Void... params)
    {
        final Articles articleDS = new Articles(getApplicationContext());
        final Audios audioDS = new Audios(getApplicationContext());
        final Images imageDS = new Images(getApplicationContext());
        final Places placeDS = new Places(getApplicationContext());
        final PlaceTypes placetypeDS = new PlaceTypes(getApplicationContext());
        final Users userDS = new Users(getApplicationContext());
        final Videos videoDS = new Videos(getApplicationContext());
        
        synchronized (this) {       
            String resultArticle = null;
            String resultAudio = null;
            String resultImage = null;
            String resultPlace = null;
            String resultPlaceType = null;
            String resultUser = null;
            String resultVideo = null;
                        
            if(!update) {
            	articleDS.clearDatabase();
            	audioDS.clearDatabase();
            	imageDS.clearDatabase();
            	placeDS.clearDatabase();
            	placetypeDS.clearDatabase();
            	userDS.clearDatabase();
            	videoDS.clearDatabase();
            	
                resultArticle = getPage(API_LINK + "articles");
                resultAudio = getPage(API_LINK + "audio");
                resultImage = getPage(API_LINK + "images");
                resultPlace = getPage(API_LINK + "places");
                resultPlaceType = getPage(API_LINK + "place_types");
                resultUser = getPage(API_LINK + "users");
                resultVideo = getPage(API_LINK + "video");
            }
            else {    
                diference();
            	Integer[] counter = numOfRowsTableLocal(); int i=0;            	
            	for (int j=0;j<7;j++){            	
            		if (difer[j]==0) { difer[j]=counter[j];}
            	}
            	
            	//IF STATEMENTS REDUCE TIME OF EXECUTE FOR almost 70-80%. Update od 1 place was 12 seconds :)
            	
          	 	if (difer[i]!=counter[i]){ 
            		resultArticle = getPage(API_LINK + "articles/?limit=" + difer[i] + "&offset=" + counter[i]); i++; }
            	else { resultArticle = "break"; i++;}
            	
            	if (difer[i]!=counter[i]){ 
            		resultAudio = getPage(API_LINK + "audio/?limit=" + difer[i] + "&offset=" + counter[i]); i++; }
            	else { resultAudio = "break"; i++;}
            	
            	if (difer[i]!=counter[i]){ 
            		resultImage = getPage(API_LINK + "images/?limit=" + difer[i] + "&offset=" + counter[i]); i++; }     
            	else { resultImage = "break"; i++;}
            	
            	if (difer[i]!=counter[i]){ 
            		resultPlace = getPage(API_LINK + "places/?limit=" + difer[i] + "&offset=" + counter[i]); i++;}
            	else { resultPlace = "break"; i++;}
            	
            	if (difer[i]!=counter[i]){ 
            		resultPlaceType = getPage(API_LINK + "place_types/?limit=" + difer[i] + "&offset=" + counter[i]); i++; }
            	else { resultPlaceType = "break"; i++;}
            	
            	if (difer[i]!=counter[i]){ 
            		resultUser = getPage(API_LINK + "users/?limit=" + difer[i] + "&offset=" + counter[i]); i++;  }
            	else { resultUser = "break"; i++;}
            	
            	if (difer[i]!=counter[i]){ 
            		resultVideo = getPage(API_LINK + "video/?limit=" + difer[i] + "&offset=" + counter[i]); }
            	else { resultVideo = "break";}
            	
            	Log.wtf("End","Connection closed.");
            }
            
            resultArticle = "{article:" + resultArticle + "}";
            resultAudio = "{audio:" + resultAudio + "}";
            resultImage = "{image:" + resultImage + "}";
            resultPlace = "{place:" + resultPlace + "}";
            resultPlaceType = "{place_type:" + resultPlaceType + "}";
            resultUser = "{user:" + resultUser + "}";
            resultVideo = "{video:" + resultVideo + "}";
            
            int k=0;            
            try {      
            	if (!resultArticle.equals("{article:break}")){
                JSONObject jObject = new JSONObject(resultArticle);
                JSONArray jArray = jObject.getJSONArray("article");

                niz = jArray.length(); int pomak = pomakFun(14,niz); int broj = 14*k; k++;

                for (int i = 0; i < niz; i++) {
                    JSONObject objekt = jArray.getJSONObject(i);

                    try { 
                    	publishProgress(broj);
                    	broj+=pomak;} 
                    catch (Exception e) {Log.wtf("Exception", "progressPublish. e = "+e.toString());}

                    Article article = new Article();
                    article.setArticleID(objekt.getInt("id"));
                    article.setArticleTitle(objekt.getString("title"));
                    article.setArticleDesc(objekt.getString("description"));
                    article.setArticleContent(objekt.getString("content"));
                    article.setArticlePlaceID(objekt.getInt("place_id"));
                    article.setArticleCreatedAt(objekt.getString("created_at"));
                    article.setArticleUpdatedAt(objekt.getString("updated_at"));
                    //article.setArticleCreatedBy(objekt.getInt("created_by"));
                    //article.setArticleUpdatedBy(objekt.getInt("updated_by"));                       
                    articleDS.save(article);                        
                }
              }

            } catch (JSONException e) {
                e.printStackTrace();
            }     

            try {                           
            	if (!resultAudio.equals("{audio:break}")){
                JSONObject jObject = new JSONObject(resultAudio);
                JSONArray jArray = jObject.getJSONArray("audio");

                niz = jArray.length(); int pomak = pomakFun(14,niz); int broj = 14*k; k++;

                for (int i = 0; i < niz; i++) {
                    JSONObject objekt = jArray.getJSONObject(i);

                    try { 
                    	publishProgress(broj);
                    	broj+=pomak;} 
                    catch (Exception e) {Log.wtf("Exception", "progressPublish. e = "+e.toString());}
                    
                    publishProgress(20);
                    Audio audio = new Audio();
                    audio.setAudioID(objekt.getInt("id"));
                    audio.setAudioTitle(objekt.getString("title"));
                    audio.setAudioDesc(objekt.getString("description"));
                    audio.setAudioFileName(objekt.getString("filename"));
                    audio.setAudioPlaceID(objekt.getInt("place_id"));
                    audio.setAudioCreatedAt(objekt.getString("created_at"));
                    audio.setAudioUpdatedAt(objekt.getString("updated_at"));
                    // audio.setAudioCreatedBy(objekt.getInt("created_by"));
                    // audio.setAudioUpdatedBy(objekt.getInt("updated_by"));                       
                    audioDS.save(audio);                 
                    
                }
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }   
            
            try {       
            	if (!resultImage.equals("{image:break}")){
                JSONObject jObject = new JSONObject(resultImage);
                JSONArray jArray = jObject.getJSONArray("image");

                niz = jArray.length(); int pomak = pomakFun(14,niz); int broj = 14*k; k++;
                publishProgress(broj);
                for (int i = 0; i < niz; i++) {
                    JSONObject objekt = jArray.getJSONObject(i);

                    try { 
                    	publishProgress(broj);
                    	broj+=pomak;} 
                    catch (Exception e) {Log.wtf("Exception", "progressPublish. e = "+e.toString());}
                    
                    Image image = new Image();
                    image.setImageID(objekt.getInt("id"));
                    image.setImageTitle(objekt.getString("title"));
                    image.setImageDesc(objekt.getString("description"));
                    image.setImageFileName(objekt.getString("filename"));
                    image.setImagePlaceID(objekt.getInt("place_id"));
                    image.setImageCreatedAt(objekt.getString("created_at"));
                    image.setImageUpdatedAt(objekt.getString("updated_at"));
                    // image.setImageCreatedBy(objekt.getInt("created_by"));
                    // image.setImageUpdatedBy(objekt.getInt("updated_by"));                       
                    imageDS.save(image);                        
                
                }
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }             
            
            try {            
            	if (!resultPlace.equals("{place:break}")){
                JSONObject jObject = new JSONObject(resultPlace);
                JSONArray jArray = jObject.getJSONArray("place");

                niz = jArray.length(); int pomak = pomakFun(14,niz); int broj = 14*k; k++;

                for (int i = 0; i < niz; i++) {
                    JSONObject objekt = jArray.getJSONObject(i);

                    try { 
                    	publishProgress(broj);
                    	broj+=pomak;} 
                    catch (Exception e) {Log.wtf("Exception", "progressPublish. e = "+e.toString());}
                    
                    Place place = new Place();
                    place.setPlaceID(objekt.getInt("id"));
                    place.setPlaceName(objekt.getString("name"));
                    place.setPlaceDesc(objekt.getString("description"));
                    place.setPlaceAddress(objekt.getString("address"));
                    place.setPlaceEmail(objekt.getString("email"));
                    place.setPlaceUrl(objekt.getString("url"));
                    place.setPlacePhone(objekt.getString("phone"));
                    place.setPlaceLat(objekt.getDouble("lat"));
                    place.setPlaceLng(objekt.getDouble("lng"));
                    place.setPlaceTypeID(objekt.getInt("place_type_id"));
                    place.setPlaceCreatedAt(objekt.getString("created_at"));
                    place.setPlaceUpdatedAt(objekt.getString("updated_at"));
                    //  place.setPlaceCreatedBy(objekt.getInt("created_by"));
                    //  place.setPlaceUpdatedBy(objekt.getInt("updated_by"));                       
                    placeDS.save(place);                        
                
                }
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }     

            
            try {     
            	if (!resultPlaceType.equals("{place_type:break}")){
                JSONObject jObject = new JSONObject(resultPlaceType);
                JSONArray jArray = jObject.getJSONArray("place_type");
                
                niz = jArray.length(); int pomak = pomakFun(14,niz); int broj = 14*k; k++;

                for (int i = 0; i < niz; i++) {
                    JSONObject objekt = jArray.getJSONObject(i);

                    try { 
                    	publishProgress(broj);
                    	broj+=pomak;} 
                    catch (Exception e) {Log.wtf("Exception", "progressPublish. e = "+e.toString());}
                    
                    PlaceType placetype = new PlaceType();
                    placetype.setPlaceTypeID(objekt.getInt("id"));
                    placetype.setPlaceTypeName(objekt.getString("name"));
                    placetype.setPlaceTypeCreatedAt(objekt.getString("created_at"));
                    placetype.setPlaceTypeUpdatedAt(objekt.getString("updated_at"));                      
                    placetypeDS.save(placetype);                        
                
                }            
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }   
            
            
            try {
            	if (!resultUser.equals("{user:break}")){
                JSONObject jObject = new JSONObject(resultUser);
                JSONArray jArray = jObject.getJSONArray("user");
                
                niz = jArray.length(); int pomak = pomakFun(14,niz); int broj = 14*k; k++;

                for (int i = 0; i < niz; i++) {
                    JSONObject objekt = jArray.getJSONObject(i);

                    try { 
                    	publishProgress(broj);
                    	broj+=pomak;} 
                    catch (Exception e) {Log.wtf("Exception", "progressPublish. e = "+e.toString());}
                    
                    User user = new User();
                    user.setUserID(objekt.getInt("id"));
                    user.setUserName(objekt.getString("username"));                   
                    userDS.save(user);                        
                }
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }   
   
            try {                 
            	if (!resultVideo.equals("{video:break}")){
                JSONObject jObject = new JSONObject(resultVideo);
                JSONArray jArray = jObject.getJSONArray("video");
                
                niz = jArray.length(); int pomak = pomakFun(14,niz); int broj = 14*k; k++;
    
                for (int i = 0; i < niz; i++) {
                    JSONObject objekt = jArray.getJSONObject(i);
                    
                    try { 
                    	publishProgress(broj);
                    	broj+=pomak;} 
                    catch (Exception e) {Log.wtf("Exception", "progressPublish. e = "+e.toString());}
                    
                    Video video = new Video();
                    video.setVideoID(objekt.getInt("id"));
                    video.setVideoTitle(objekt.getString("title"));
                    video.setVideoDesc(objekt.getString("description"));
                    video.setVideoFileName(objekt.getString("filename"));
                    video.setVideoPlaceID(objekt.getInt("place_id"));
                    video.setVideoCreatedAt(objekt.getString("created_at"));
                    video.setVideoUpdatedAt(objekt.getString("updated_at"));
                    // video.setVideoCreatedBy(objekt.getInt("created_by"));
                    // video.setVideoUpdatedBy(objekt.getInt("updated_by"));                       
                    videoDS.save(video);                      
                }
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }         
        }   
        
        try { 
        	publishProgress(100);}
        catch (Exception e) {Log.wtf("Exception", "progressPublish. e = "+e.toString());}
        
        return null;
    }
    
    int pomakFun(int broj, int niz){
        int pomak = (int) Math.floor(broj/niz);
        if (broj/niz<0) pomak = (int) Math.floor(niz/broj);
        return pomak;
    }
    
    //Update the progress
    @Override
    protected void onProgressUpdate(Integer... values)
    {
       try {progressDialog.setProgress(values[0]);} catch (Exception e) {Log.wtf("Exception", "last_progrespublish, e= "+e.toString());}
    }

    //after executing the code in the thread
    @Override
    protected void onPostExecute(Void result)
    {
    	Log.wtf("kraj","1");
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Download completed succesfully",Toast.LENGTH_LONG).show();
        
		Intent i = new Intent(SynchronizeTask.this, DownloadActivity.class);
		startActivity(i);

    }
}



public Integer[] numOfRowsTableLocal(){
    Integer[] zadnji = new Integer[7];
    final Articles articleDS = new Articles(getApplicationContext());
    final Audios audioDS = new Audios(getApplicationContext());
    final Images imageDS = new Images(getApplicationContext());
    final Places placeDS = new Places(getApplicationContext());
    final PlaceTypes placetypeDS = new PlaceTypes(getApplicationContext());
    final Users userDS = new Users(getApplicationContext());
    final Videos videoDS = new Videos(getApplicationContext());
    
    try { zadnji[0] = articleDS.ArticleTableCount(); } catch (Exception e) {zadnji[0]=0;}
    try { zadnji[1] = audioDS.AudioTableCount();} catch (Exception e) {zadnji[1]=0;}
    try { zadnji[2] = imageDS.ImageTableCount();} catch (Exception e) {zadnji[2]=0;}
    try { zadnji[3] = placeDS.PlaceTableCount();} catch (Exception e) {zadnji[3]=0;}
    try { zadnji[4] = placetypeDS.PlaceTypeTableCount();} catch (Exception e) {zadnji[4]=0;}
    try { zadnji[5] = userDS.UserTableCount();} catch (Exception e) {zadnji[5]=0;}
    try { zadnji[6] = videoDS.VideoTableCount();} catch (Exception e) {zadnji[6]=0;}

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
    Log.i("URL = ", url);        
        httppost = new HttpGet(url);

        InputStream inputStream = null;
        String result = null;
        try {
            response = httpclient.execute(httppost);
            entity = response.getEntity();
            inputStream = entity.getContent();
            // json is UTF-8 by default
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            sb = new StringBuilder();
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

//Hrvoje Ljubic - 16. veljace, 2015