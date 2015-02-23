package com.teamcode.info.guideme.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Model.Image;

/* Created by Hrvoje on 15.2.2015.
 * Klasa za 'upravljanje' bazom. */

public class Images extends Model {
    private Context ctx;

    public Images(Context context) {
        super(context);
        ctx = context;
    }

    public Image save (Image l) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put(IMAGE_COL_ID, l.getImageID());
        cv.put(IMAGE_COL_TITLE, l.getImageTitle());
        cv.put(IMAGE_COL_DESC, l.getImageDesc());
        cv.put(IMAGE_COL_FILENAME, l.getImageFileName());
        cv.put(IMAGE_COL_PLACE_ID, l.getImagePlaceID());
        cv.put(IMAGE_COL_CREATED_AT, l.getImageCreatedAt());
        cv.put(IMAGE_COL_UPDATED_AT, l.getImageUpdatedAt());
        cv.put(IMAGE_COL_CREATED_BY, l.getImageCreatedBy());
        cv.put(IMAGE_COL_UPDATED_BY, l.getImageUpdatedBy());
        
        l.setImageID((int) db.insert(IMAGE_TBL_NAME, null, cv));        
        db.close();
        return l;
    };

    public List<Image> getImages () {
        SQLiteDatabase db = getReadableDatabase();
        String cols[] = {IMAGE_COL_ID, IMAGE_COL_TITLE, IMAGE_COL_DESC, IMAGE_COL_FILENAME, IMAGE_COL_PLACE_ID, IMAGE_COL_CREATED_AT,IMAGE_COL_UPDATED_AT, IMAGE_COL_CREATED_BY, IMAGE_COL_UPDATED_BY};
        Cursor c = db.query(IMAGE_TBL_NAME, cols, null, null, null, null, null);
        c.moveToFirst();
        List<Image> images = new ArrayList<Image>();
        do {
            images.add(CursorImage(c));
        }while (c.moveToNext());
        c.close();
        db.close();
        return images;
    };

    public Image getImageByID (int id) {
        List<Image> images = new ArrayList<Image>();
        images = getImages();
        for (Image image : images) {
            if (image.getImageID() == id) return image;
        }
        return null;
    };
    
    public void deleteImage (Image l) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(IMAGE_TBL_NAME, IMAGE_COL_ID + "=" + l.getImageID(), null);
        db.close();
    };
    
    public int ImageTableCount(){
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor mCount= db.rawQuery("SELECT COUNT(*) FROM " + IMAGE_TBL_NAME, null);
    	mCount.moveToFirst();
    	int count= mCount.getInt(0);
    	mCount.close();
    	db.close();
    	return count;
    }
    
    private Image CursorImage (Cursor c) {
    	
    	Image image = new Image();    	
        image.setImageID(c.getInt(0));
        image.setImageTitle(c.getString(1));
        image.setImageDesc(c.getString(2));
        image.setImageFileName(c.getString(3));
        image.setImagePlaceID(c.getInt(4));
        image.setImageCreatedAt(c.getString(5));
        image.setImageUpdatedAt(c.getString(6));
        image.setImageCreatedBy(c.getInt(7));
        image.setImageUpdatedBy(c.getInt(8));
        
        return image;
    };

    public void clearDatabase() {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.delete(IMAGE_TBL_NAME, null, null);
    }
}
