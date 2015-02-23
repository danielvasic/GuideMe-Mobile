package com.teamcode.info.guideme;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomListPlaceTypes extends ArrayAdapter<String>{
private final Activity context;

private final String[] placeName;
private final Integer[] imageId;

	public CustomListPlaceTypes(Activity context,
		String[] placeName, Integer[] imageId) {
		super(context, R.layout.list_single_place_types, placeName);
		this.context = context;
		this.placeName = placeName;
		this.imageId = imageId;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.list_single_place_types, null, true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		// ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		
		txtTitle.setText(placeName[position]);		
		// imageView.setImageResource(imageId[position]);
		
	return rowView;
	
	}
}
