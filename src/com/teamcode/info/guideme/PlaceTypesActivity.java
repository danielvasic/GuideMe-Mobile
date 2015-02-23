package com.teamcode.info.guideme;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Controller.PlaceTypes;
import com.teamcode.info.guideme.Model.PlaceType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PlaceTypesActivity extends Activity {
  ListView list;
  
  String[] placeNames;
  String[] placeDescs;
  String[] placeUrls;

  Integer[] placeTypeIDs;
  Integer[] imageId = {
      R.drawable.im1,
      R.drawable.im2,
      R.drawable.im3,
      R.drawable.im4
  };
  
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list);
    
    List<Integer> plTypeID = new ArrayList<Integer>();    
    List<String> plTypeName = new ArrayList<String>();

    
    PlaceTypes lds = new PlaceTypes(getApplicationContext());
    try{
    //Get locations from database        
    final List<PlaceType> lats = lds.getPlaceTypes();
	    for (PlaceType l : lats) {
	    	plTypeID.add(l.getPlaceTypeID()); 
	    	plTypeName.add(l.getPlaceTypeName());  
	    }     
    }
    catch (Exception e) {Log.wtf("Exception","Vjerovatno prazna tablica - PlaceTypes");}
    
    placeTypeIDs = new Integer[plTypeID.size()];
    placeTypeIDs = plTypeID.toArray(placeTypeIDs);
    
    placeNames = new String[plTypeName.size()];
    placeNames = plTypeName.toArray(placeNames);
    
    
    CustomListPlaceTypes adapter = new
        CustomListPlaceTypes(PlaceTypesActivity.this, placeNames, imageId);
    	list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                	
                	Intent i = new Intent(PlaceTypesActivity.this, PlacesActivity.class);
                		i.putExtra("placeTypeID", placeTypeIDs[+position]);             	
                	startActivity(i);                
                	
                	// Toast.makeText(PlacesActivity.this, "Place - " + placeNames[+ position], Toast.LENGTH_SHORT).show();
                }
            });
  }
}