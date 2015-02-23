package com.teamcode.info.guideme.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Model.Video;

/* Created by Hrvoje on 15.2.2015.
 * Klasa za 'upravljanje' bazom. */

public class Videos extends Model {
    private Context ctx;

    public Videos(Context context) {
        super(context);
        ctx = context;
    }

    public Video save (Video l) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put(VIDEO_COL_ID, l.getVideoID());
        cv.put(VIDEO_COL_TITLE, l.getVideoTitle());
        cv.put(VIDEO_COL_DESC, l.getVideoDesc());
        cv.put(VIDEO_COL_FILENAME, l.getVideoFileName());
        cv.put(VIDEO_COL_PLACE_ID, l.getVideoPlaceID());
        cv.put(VIDEO_COL_CREATED_AT, l.getVideoCreatedAt());
        cv.put(VIDEO_COL_UPDATED_AT, l.getVideoUpdatedAt());
        cv.put(VIDEO_COL_CREATED_BY, l.getVideoCreatedBy());
        cv.put(VIDEO_COL_UPDATED_BY, l.getVideoUpdatedBy());
        
        l.setVideoID((int) db.insert(VIDEO_TBL_NAME, null, cv));        
        db.close();
        return l;
    };

    public List<Video> getVideos () {
        SQLiteDatabase db = getReadableDatabase();
        String cols[] = {VIDEO_COL_ID, VIDEO_COL_TITLE, VIDEO_COL_DESC, VIDEO_COL_FILENAME, VIDEO_COL_PLACE_ID, VIDEO_COL_CREATED_AT,VIDEO_COL_UPDATED_AT, VIDEO_COL_CREATED_BY, VIDEO_COL_UPDATED_BY};
        Cursor c = db.query(VIDEO_TBL_NAME, cols, null, null, null, null, null);
        c.moveToFirst();
        List<Video> videos = new ArrayList<Video>();
        do {
            videos.add(CursorVideo(c));
        }while (c.moveToNext());
        c.close();
        db.close();
        return videos;
    };

    public Video getVideoByID (int id) {
        List<Video> videos = new ArrayList<Video>();
        videos = getVideos();
        for (Video video : videos) {
            if (video.getVideoID() == id) return video;
        }
        return null;
    };
    
    public void deleteVideo (Video l) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(VIDEO_TBL_NAME, VIDEO_COL_ID + "=" + l.getVideoID(), null);
        db.close();
    };
    
    public int VideoTableCount(){
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor mCount= db.rawQuery("SELECT COUNT(*) FROM " + VIDEO_TBL_NAME, null);
    	mCount.moveToFirst();
    	int count= mCount.getInt(0);
    	mCount.close();
    	db.close();
    	return count;
    }

    private Video CursorVideo (Cursor c) {
    	
    	Video video = new Video();    	
        video.setVideoID(c.getInt(0));
        video.setVideoTitle(c.getString(1));
        video.setVideoDesc(c.getString(2));
        video.setVideoFileName(c.getString(3));
        video.setVideoPlaceID(c.getInt(4));
        video.setVideoCreatedAt(c.getString(5));
        video.setVideoUpdatedAt(c.getString(6));
        video.setVideoCreatedBy(c.getInt(7));
        video.setVideoUpdatedBy(c.getInt(8));
        
        return video;
    };

    public void clearDatabase() {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.delete(VIDEO_TBL_NAME, null, null);
    }
}
