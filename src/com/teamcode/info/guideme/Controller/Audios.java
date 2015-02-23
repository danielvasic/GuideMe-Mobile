package com.teamcode.info.guideme.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Model.Audio;

/* Created by Hrvoje on 15.2.2015.
 * Klasa za 'upravljanje' bazom. */

public class Audios extends Model {
    private Context ctx;

    public Audios(Context context) {
        super(context);
        ctx = context;
    }

    public Audio save (Audio l) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put(AUDIO_COL_ID, l.getAudioID());
        cv.put(AUDIO_COL_TITLE, l.getAudioTitle());
        cv.put(AUDIO_COL_DESC, l.getAudioDesc());
        cv.put(AUDIO_COL_FILENAME, l.getAudioFileName());
        cv.put(AUDIO_COL_PLACE_ID, l.getAudioPlaceID());
        cv.put(AUDIO_COL_CREATED_AT, l.getAudioCreatedAt());
        cv.put(AUDIO_COL_UPDATED_AT, l.getAudioUpdatedAt());
        cv.put(AUDIO_COL_CREATED_BY, l.getAudioCreatedBy());
        cv.put(AUDIO_COL_UPDATED_BY, l.getAudioUpdatedBy());
        
        l.setAudioID((int) db.insert(AUDIO_TBL_NAME, null, cv));        
        db.close();
        return l;
    };

    public List<Audio> getAudios () {
        SQLiteDatabase db = getReadableDatabase();
        String cols[] = {AUDIO_COL_ID, AUDIO_COL_TITLE, AUDIO_COL_DESC, AUDIO_COL_FILENAME, AUDIO_COL_PLACE_ID, AUDIO_COL_CREATED_AT,AUDIO_COL_UPDATED_AT, AUDIO_COL_CREATED_BY, AUDIO_COL_UPDATED_BY};
        Cursor c = db.query(AUDIO_TBL_NAME, cols, null, null, null, null, null);
        c.moveToFirst();
        List<Audio> audios = new ArrayList<Audio>();
        do {
            audios.add(CursorAudio(c));
        }while (c.moveToNext());
        c.close();
        db.close();
        return audios;
    };

    public Audio getAudioByID (int id) {
        List<Audio> audios = new ArrayList<Audio>();
        audios = getAudios();
        for (Audio audio : audios) {
            if (audio.getAudioID() == id) return audio;
        }
        return null;
    };
    
    public void deleteAudio (Audio l) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(AUDIO_TBL_NAME, AUDIO_COL_ID + "=" + l.getAudioID(), null);
        db.close();
    };
    
    public int AudioTableCount(){
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor mCount= db.rawQuery("SELECT COUNT(*) FROM " + AUDIO_TBL_NAME, null);
    	mCount.moveToFirst();
    	int count= mCount.getInt(0);
    	mCount.close();
    	db.close();
    	return count;
    	/*
    	String countQuery = "SELECT  * FROM " + AUDIO_TBL_NAME;
    	SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;*/
    }

    private Audio CursorAudio (Cursor c) {
    	Audio audio = new Audio();
    	
        audio.setAudioID(c.getInt(0));
        audio.setAudioTitle(c.getString(1));
        audio.setAudioDesc(c.getString(2));
        audio.setAudioFileName(c.getString(3));
        audio.setAudioPlaceID(c.getInt(4));
        audio.setAudioCreatedAt(c.getString(5));
        audio.setAudioUpdatedAt(c.getString(6));
        audio.setAudioCreatedBy(c.getInt(7));
        audio.setAudioUpdatedBy(c.getInt(8));
        
        return audio;
    };

    public void clearDatabase() {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.delete(AUDIO_TBL_NAME, null, null);
    }
}
