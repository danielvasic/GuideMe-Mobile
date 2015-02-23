package com.teamcode.info.guideme.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Model.Place;

/*
 * Created by Hrvoje on 19.1.2015.
 * Klasa za 'upravljanje' bazom.
 */

public class Places extends Model {
    private Context ctx;

    public Places(Context context) {
        super(context);
        ctx = context;
    }

    public Place save (Place l) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();        
        cv.put(PLACE_COL_ID, l.getPlaceID());
        cv.put(PLACE_COL_NAME, l.getPlaceName());
        cv.put(PLACE_COL_DESC, l.getPlaceDesc());
        cv.put(PLACE_COL_ADDRESS, l.getPlaceAddress());
        cv.put(PLACE_COL_EMAIL, l.getPlaceEmail());
        cv.put(PLACE_COL_URL, l.getPlaceUrl());
        cv.put(PLACE_COL_PHONE, l.getPlacePhone());
        cv.put(PLACE_COL_LAT, l.getPlaceLat());
        cv.put(PLACE_COL_LNG, l.getPlaceLng());
        cv.put(PLACE_COL_TYPE_ID, l.getPlaceTypeID());
        cv.put(PLACE_COL_CREATED_AT, l.getPlaceCreatedAt());
        cv.put(PLACE_COL_UPDATED_AT, l.getPlaceUpdatedAt());
        cv.put(PLACE_COL_CREATED_BY, l.getPlaceCreatedBy());
        cv.put(PLACE_COL_UPDATED_BY, l.getPlaceUpdatedBy());
        
        l.setPlaceID((int) db.insert(PLACE_TBL_NAME, null, cv));        
        db.close();
        return l;
    };

    public List<Place> getPlaces () {
        SQLiteDatabase db = getReadableDatabase();
        String cols[] = {PLACE_COL_ID, PLACE_COL_NAME,PLACE_COL_DESC,PLACE_COL_ADDRESS,PLACE_COL_EMAIL,PLACE_COL_URL,PLACE_COL_PHONE, PLACE_COL_LAT,PLACE_COL_LNG,PLACE_COL_TYPE_ID, PLACE_COL_CREATED_AT,PLACE_COL_UPDATED_AT, PLACE_COL_CREATED_BY, PLACE_COL_UPDATED_BY};
        Cursor c = db.query(PLACE_TBL_NAME, cols, null, null, null, null, null);
        c.moveToFirst();
        List<Place> places = new ArrayList<Place>();
        do {
            places.add(CursorPlace(c));
        }while (c.moveToNext());
        c.close();
        db.close();
        return places;
    };

    public Place getPlaceByID (int id) {
        List<Place> places = new ArrayList<Place>();
        places = getPlaces();
        for (Place place : places) {
            if (place.getPlaceID() == id) return place;
        }
        return null;
    };
    
    public List<Place> getPlaceByTypeID (int place_type_id) {
        SQLiteDatabase db = getReadableDatabase();
        String cols[] = {PLACE_COL_ID, PLACE_COL_NAME,PLACE_COL_DESC,PLACE_COL_ADDRESS,PLACE_COL_EMAIL,PLACE_COL_URL,PLACE_COL_PHONE, PLACE_COL_LAT,PLACE_COL_LNG,PLACE_COL_TYPE_ID, PLACE_COL_CREATED_AT,PLACE_COL_UPDATED_AT, PLACE_COL_CREATED_BY, PLACE_COL_UPDATED_BY};
        Cursor c = db.query(PLACE_TBL_NAME, cols, PLACE_COL_TYPE_ID + "=" + place_type_id, null, null, null, null);
        c.moveToFirst();
        List<Place> places = new ArrayList<Place>();
        do {
            places.add(CursorPlace(c));
        }while (c.moveToNext());
        c.close();
        db.close();
        return places;
    };
    
    public void deletePlace (Place l) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PLACE_TBL_NAME, PLACE_COL_ID + "=" + l.getPlaceID(), null);
        db.close();
    };
    
    public int PlaceTableCount(){
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor mCount= db.rawQuery("SELECT COUNT(*) FROM " + PLACE_TBL_NAME, null);
    	mCount.moveToFirst();
    	int count= mCount.getInt(0);
    	mCount.close();
    	db.close();
    	return count;
    }

    private Place CursorPlace (Cursor c) {

    	Place place = new Place();    	
        place.setPlaceID(c.getInt(0));
        place.setPlaceName(c.getString(1));
        place.setPlaceDesc(c.getString(2));
        place.setPlaceAddress(c.getString(3));
        place.setPlaceEmail(c.getString(4));
        place.setPlaceUrl(c.getString(5));
        place.setPlacePhone(c.getString(6));
        place.setPlaceLat(c.getDouble(7));
        place.setPlaceLng(c.getDouble(8));
        place.setPlaceTypeID(c.getInt(9));
        place.setPlaceCreatedAt(c.getString(10));
        place.setPlaceUpdatedAt(c.getString(11));
        place.setPlaceCreatedBy(c.getInt(12));
        place.setPlaceUpdatedBy(c.getInt(13));
        
        return place;
    };

    public void clearDatabase() {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.delete(PLACE_TBL_NAME, null, null);
    }
}
