package com.teamcode.info.guideme.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Model.PlaceType;

/* Created by Hrvoje on 15.2.2015.
 * Klasa za 'upravljanje' bazom. */

public class PlaceTypes extends Model {
    private Context ctx;

    public PlaceTypes(Context context) {
        super(context);
        ctx = context;
    }

    public PlaceType save (PlaceType l) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put(PLACE_TYPE_COL_ID, l.getPlaceTypeID());
        cv.put(PLACE_TYPE_COL_NAME, l.getPlaceTypeName());
        cv.put(PLACE_TYPE_COL_CREATED_AT, l.getPlaceTypeCreatedAt());
        cv.put(PLACE_TYPE_COL_UPDATED_AT, l.getPlaceTypeUpdatedAt());
        
        l.setPlaceTypeID((int) db.insert(PLACE_TYPE_TBL_NAME, null, cv));        
        db.close();
        return l;
    };

    public List<PlaceType> getPlaceTypes () {
        SQLiteDatabase db = getReadableDatabase();
        String cols[] = {PLACE_TYPE_COL_ID, PLACE_TYPE_COL_NAME, PLACE_TYPE_COL_CREATED_AT, PLACE_TYPE_COL_UPDATED_AT};
        Cursor c = db.query(PLACE_TYPE_TBL_NAME, cols, null, null, null, null, null);
        c.moveToFirst();
        List<PlaceType> placetypes = new ArrayList<PlaceType>();
        do {
            placetypes.add(CursorPlaceType(c));
        }while (c.moveToNext());
        c.close();
        db.close();
        return placetypes;
    };

    public PlaceType getPlaceTypeByID (int id) {
        List<PlaceType> placetypes = new ArrayList<PlaceType>();
        placetypes = getPlaceTypes();
        for (PlaceType placetype : placetypes) {
            if (placetype.getPlaceTypeID() == id) return placetype;
        }
        return null;
    };
    
    public void deletePlaceType (PlaceType l) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PLACE_TYPE_TBL_NAME, PLACE_TYPE_COL_ID + "=" + l.getPlaceTypeID(), null);
        db.close();
    };
    
    public int PlaceTypeTableCount(){
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor mCount= db.rawQuery("SELECT COUNT(*) FROM " + PLACE_TYPE_TBL_NAME, null);
    	mCount.moveToFirst();
    	int count= mCount.getInt(0);
    	mCount.close();
    	db.close();
    	return count;
    }


    private PlaceType CursorPlaceType (Cursor c) {
    	
    	PlaceType placetype = new PlaceType();    	
        placetype.setPlaceTypeID(c.getInt(0));
        placetype.setPlaceTypeName(c.getString(1));
        placetype.setPlaceTypeCreatedAt(c.getString(2));
        placetype.setPlaceTypeUpdatedAt(c.getString(3));
        
        return placetype;
    };

    public void clearDatabase() {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.delete(PLACE_TYPE_TBL_NAME, null, null);
    }
}
