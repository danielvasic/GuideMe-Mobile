package com.teamcode.info.guideme;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomListPlaces extends ArrayAdapter<String>{
private final Activity context;

private final String[] placeName;
private final String[] placeDesc;
private final String[] placeUrl;

private final Integer[] imageId;

	public CustomListPlaces(Activity context,
		String[] placeName, String[] placeDesc, String[] placeUrl, Integer[] imageId) {
		super(context, R.layout.list_single_places, placeName);
		this.context = context;
		this.placeName = placeName;
		this.placeDesc = placeDesc;
		this.placeUrl = placeUrl;
		this.imageId = imageId;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.list_single_places, null, true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		TextView txt1 = (TextView) rowView.findViewById(R.id.txt1);
		TextView txt2 = (TextView) rowView.findViewById(R.id.txt2);
		// ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		
		txtTitle.setText(placeName[position]);
		txt1.setText(placeDesc[position]);
		txt2.setText(placeUrl[position]);
		
		// imageView.setImageResource(imageId[position]);
		
	return rowView;
	
	}
}
