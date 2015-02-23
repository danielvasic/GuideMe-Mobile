package com.teamcode.info.guideme;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Controller.Places;
import com.teamcode.info.guideme.Model.Place;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PlacesActivity extends Activity {
  ListView list;
  
  String[] placeNames;
  String[] placeDescs;
  String[] placeUrls;

  Integer[] placeIDs;
  Integer[] imageId = {
      R.drawable.im1,
      R.drawable.im2,
      R.drawable.im3,
      R.drawable.im4
  };
  
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list);

    Bundle extras = getIntent().getExtras();
    int placeTypeID = extras.getInt("placeTypeID");
    
    List<Integer> plID = new ArrayList<Integer>();
    
    List<String> plName = new ArrayList<String>();
    List<String> plDesc = new ArrayList<String>();
    List<String> plUrl = new ArrayList<String>();
    
    Places lds = new Places(getApplicationContext());
    try{
    //Get locations from database       
    	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    	//GET PLACE TYPES BY PLACE TYPE ID !!!!!!!!!!!!!!!!!
    	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    	final List<Place> lats = lds.getPlaces();
	    for (Place l : lats) {
	    	plID.add(l.getPlaceID()); 
	    	plName.add(l.getPlaceName());        
	    	plDesc.add(l.getPlaceDesc());
	    	plUrl.add(l.getPlaceAddress());
	    }     
    }
    catch (Exception e) {Log.wtf("Exception", "Vjerovatno prazna tablica - Places");}
    
    placeIDs = new Integer[plID.size()];
    placeIDs = plID.toArray(placeIDs);
    
    placeNames = new String[plName.size()];
    placeNames = plName.toArray(placeNames);
    
    placeDescs = new String[plDesc.size()];
    placeDescs = plDesc.toArray(placeDescs);
    
    placeUrls = new String[plUrl.size()];
    placeUrls = plUrl.toArray(placeUrls);
    
    CustomListPlaces adapter = new
        CustomListPlaces(PlacesActivity.this, placeNames, placeDescs, placeUrls, imageId);
    	list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                	
                	Intent i = new Intent(PlacesActivity.this, PlaceDetails.class);
                		i.putExtra("placeID", placeIDs[+position]);             	
                	startActivity(i);                
                	
                	// Toast.makeText(PlacesActivity.this, "Place - " + placeNames[+ position], Toast.LENGTH_SHORT).show();
                }
            });
  }
}