package com.teamcode.info.guideme;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Controller.Audios;
import com.teamcode.info.guideme.Controller.Images;
import com.teamcode.info.guideme.Controller.Videos;
import com.teamcode.info.guideme.Model.Audio;
import com.teamcode.info.guideme.Model.Image;
import com.teamcode.info.guideme.Model.Video;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class DownloadActivity extends Activity {	
	String RES_LINK = "http://tkordic.net/assets/";	
	public static final int progress_bar_type = 0;	
	private ProgressDialog progressDialog;
	
	String[] AudioFileNames;
	String[] VideoFileNames;
	String[] ImageFileNames;
  

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_synchronize);

    Audios audioDS = new Audios(getApplicationContext());
    Videos videoDS = new Videos(getApplicationContext());
    Images imageDS = new Images(getApplicationContext());

    List<String> audFileName = new ArrayList<String>();
    List<String> vidFileName = new ArrayList<String>();
    List<String> imFileName = new ArrayList<String>();

    try {
    	final List<Audio> lats = audioDS.getAudios();
	    for (Audio l : lats) {
	    	audFileName.add(l.getAudioFileName());        
	    }     
    }
    catch (Exception e) {Log.wtf("Exception", "Probably empty table - Audios");}

    try {
    	final List<Video> lats = videoDS.getVideos();
	    for (Video l : lats) {
	    	vidFileName.add(l.getVideoFileName());        
	    }     
    }
    catch (Exception e) {Log.wtf("Exception", "Probably empty table - Videos");}

    try {
    	final List<Image> lats = imageDS.getImages();
	    for (Image l : lats) {
	    	imFileName.add(l.getImageFileName());        
	    }     
    }
    catch (Exception e) {Log.wtf("Exception", "Probably empty table - Images");}
    
    AudioFileNames = new String[audFileName.size()];
    AudioFileNames = audFileName.toArray(AudioFileNames);
    
    VideoFileNames = new String[vidFileName.size()];
    VideoFileNames = vidFileName.toArray(VideoFileNames);
    
    ImageFileNames = new String[imFileName.size()];
    ImageFileNames = imFileName.toArray(ImageFileNames);
    
	new DownloadFileFromURL("audio", AudioFileNames).execute();

  }
  
	class DownloadFileFromURL extends AsyncTask<String, String, String> {
		String folderName; 
		String[] fileNames;

		public DownloadFileFromURL(String folderName, String[] fileNames) {
			this.folderName = folderName;
			this.fileNames = fileNames;
		}

		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        progressDialog = new ProgressDialog(DownloadActivity.this);
	        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        progressDialog.setTitle("Downloading...");
	        progressDialog.setMessage("Please wait...");
	        progressDialog.setCancelable(false);
	        progressDialog.setIndeterminate(false);
	        progressDialog.setMax(100);
	        progressDialog.setProgress(0);
	        progressDialog.show();
	    }


		@Override
	    protected String doInBackground(String... f_url) {
	        int count;

	        try {
			URL url; 
			URLConnection conection;
			InputStream input;
			
		    for (int i=0;i<fileNames.length;i++){
		    	
		        url = new URL(RES_LINK + folderName + "/" + fileNames[i]);
	            conection = url.openConnection();
	            conection.connect();

	            int lenghtOfFile = conection.getContentLength();
	            input = new BufferedInputStream(url.openStream(),
	                    8192);
	           
	            File file = new File(getFilesDir().toString() + "/" + folderName + "/" + fileNames[i]);
	            if (file.exists()) continue;
	            
	            OutputStream output = new FileOutputStream(getFilesDir().toString() + "/" + folderName + "/" + fileNames[i]);

	            byte data[] = new byte[1024];
	            long total = 0;

	            while ((count = input.read(data)) != -1) {
	                total += count;
	                publishProgress("" + (int) ((total * 100) / lenghtOfFile));
	                output.write(data, 0, count);
	            }

	            output.flush();
	            output.close();
	            input.close();
		    }
	        } catch (Exception e) {
	            Log.e("Error: ", e.getMessage());
	        }

	        return null;
	    }


	    protected void onProgressUpdate(String... progress) {
	        // updating progress
	    	progressDialog.setProgress(Integer.parseInt(progress[0]));
	    }

	    @Override
	    protected void onPostExecute(String file_url) {
	    	progressDialog.dismiss();
	    	if (folderName.equals("audio"))
	    		new DownloadFileFromURL("video", VideoFileNames).execute();
	    	
	    	else if (folderName.equals("video"))
	    		new DownloadFileFromURL("images", ImageFileNames).execute();
	    	
	    	else DownloadActivity.this.finish();
	    }
	}  
}