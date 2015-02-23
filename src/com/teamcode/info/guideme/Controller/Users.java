package com.teamcode.info.guideme.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Model.User;

/* Created by Hrvoje on 16.2.2015.
 * Klasa za 'upravljanje' bazom. */

public class Users extends Model {
    private Context ctx;

    public Users(Context context) {
        super(context);
        ctx = context;
    }

    public User save (User l) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put(USER_COL_ID, l.getUserID());
        cv.put(USER_COL_NAME, l.getUserName());
        
        l.setUserID((int) db.insert(USER_TBL_NAME, null, cv));        
        db.close();
        return l;
    };

    public List<User> getUsers () {
        SQLiteDatabase db = getReadableDatabase();
        String cols[] = {USER_COL_ID, USER_COL_NAME};
        Cursor c = db.query(USER_TBL_NAME, cols, null, null, null, null, null);
        c.moveToFirst();
        List<User> users = new ArrayList<User>();
        do {
            users.add(CursorUser(c));
        }while (c.moveToNext());
        c.close();
        db.close();
        return users;
    };

    public User getUserByID (int id) {
        List<User> users = new ArrayList<User>();
        users = getUsers();
        for (User user : users) {
            if (user.getUserID() == id) return user;
        }
        return null;
    };
    
    public void deleteUser (User l) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(USER_TBL_NAME, USER_COL_ID + "=" + l.getUserID(), null);
        db.close();
    };

    public int UserTableCount(){
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor mCount= db.rawQuery("SELECT COUNT(*) FROM " + USER_TBL_NAME, null);
    	mCount.moveToFirst();
    	int count= mCount.getInt(0);
    	mCount.close();
    	db.close();
    	return count;
    }

    
    private User CursorUser (Cursor c) {
    	User user = new User();    	
        user.setUserID(c.getInt(0));
        user.setUserName(c.getString(1));
        return user;
    };

    public void clearDatabase() {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.delete(USER_TBL_NAME, null, null);
    }
}
