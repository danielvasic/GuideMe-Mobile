package com.teamcode.info.guideme;

import com.teamcode.info.guideme.Controller.Places;
import com.teamcode.info.guideme.Model.Place;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class PlaceDetails extends Activity {
	private int id;
	
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.place_details);

	     Bundle extras = getIntent().getExtras();
	     id = extras.getInt("placeID");
	     
		 TextView placeNameTxt = (TextView) findViewById(R.id.placeNameTxt);
		 TextView placeAddressTxt = (TextView) findViewById(R.id.placeAddressTxt);
		 TextView placeCreatedAtTxt = (TextView) findViewById(R.id.placeCreatedAtTxt);
		 TextView placeDescTxt = (TextView) findViewById(R.id.placeDescTxt);
		 
		 Places p = new Places (getApplicationContext());		 
		 Place place = new Place();
		 
		 place = p.getPlaceByID(id);	
		 
		 placeNameTxt.setText(place.getPlaceName());
		 placeAddressTxt.setText("Address: " + place.getPlaceAddress());
		 placeCreatedAtTxt.setText("Created at: " + place.getPlaceCreatedAt());
		 placeDescTxt.setText(place.getPlaceDesc());
	}
	
}
