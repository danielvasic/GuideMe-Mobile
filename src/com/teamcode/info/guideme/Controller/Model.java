package com.teamcode.info.guideme.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Model extends SQLiteOpenHelper {
    public static final String DB_NAME = "guideme.db";
    public static final int DB_VERSION = 1;

    //table PLACE
    public static String PLACE_TBL_NAME = "PLACE";
   
    //attributes of table PLACE
    public static String PLACE_COL_ID = "place_id";
    public static String PLACE_COL_NAME = "place_name";
    public static String PLACE_COL_DESC = "place_desc";
    public static String PLACE_COL_ADDRESS = "place_address";
    public static String PLACE_COL_EMAIL = "place_email";
    public static String PLACE_COL_URL = "place_url";
    public static String PLACE_COL_PHONE = "place_phone";
    public static String PLACE_COL_LAT = "place_lat";
    public static String PLACE_COL_LNG = "place_lng";
    public static String PLACE_COL_TYPE_ID = "place_type_id";
    public static String PLACE_COL_CREATED_AT = "place_created_at";
    public static String PLACE_COL_UPDATED_AT = "place_updated_at";
    public static String PLACE_COL_CREATED_BY = "place_created_by";
    public static String PLACE_COL_UPDATED_BY = "place_updated_by";
    
    //Query for creating table PLACE
    public static String PLACE_TBL_CREATE = "CREATE TABLE " + PLACE_TBL_NAME + "("
            + PLACE_COL_ID + " INTEGER PRIMARY KEY, "
            + PLACE_COL_NAME + " TEXT NOT NULL, "
            + PLACE_COL_DESC + " TEXT,"
            + PLACE_COL_ADDRESS + " TEXT,"
            + PLACE_COL_EMAIL + " TEXT,"
            + PLACE_COL_URL + " TEXT,"
            + PLACE_COL_PHONE + " TEXT,"
            + PLACE_COL_LAT + " DOUBLE,"
            + PLACE_COL_LNG + " DOUBLE,"
            + PLACE_COL_TYPE_ID + " INTEGER NOT NULL,"
            + PLACE_COL_CREATED_AT + " TEXT,"
            + PLACE_COL_UPDATED_AT + " TEXT,"
            + PLACE_COL_CREATED_BY + " INT,"
            + PLACE_COL_UPDATED_BY + " INT" + ")";
   
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //table PLACE_TYPE
    public static String PLACE_TYPE_TBL_NAME = "PLACE_TYPE";
   
    //attributes of table PLACE_TYPE
    public static String PLACE_TYPE_COL_ID = "place_type_id";
    public static String PLACE_TYPE_COL_NAME = "place_type_name";
    public static String PLACE_TYPE_COL_CREATED_AT = "place_type_created_at";
    public static String PLACE_TYPE_COL_UPDATED_AT = "place_type_updated_at";
      
    //Query for creating table PLACE_TYPE
    public static String PLACE_TYPE_TBL_CREATE = "CREATE TABLE " + PLACE_TYPE_TBL_NAME + "("
            + PLACE_TYPE_COL_ID + " INTEGER PRIMARY KEY, "
            + PLACE_TYPE_COL_NAME + " TEXT NOT NULL, "
            + PLACE_TYPE_COL_CREATED_AT + " TEXT,"
            + PLACE_TYPE_COL_UPDATED_AT + " TEXT" + ")";
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //table USER
    public static String USER_TBL_NAME = "USER";
   
    //attributes of table USER
    public static String USER_COL_ID = "user_id";
    public static String USER_COL_NAME = "user_name";
       
    //Query for creating table USER
    public static String USER_TBL_CREATE = "CREATE TABLE " + USER_TBL_NAME + "("
            + USER_COL_ID + " INTEGER PRIMARY KEY, "
            + USER_COL_NAME + " TEXT NOT NULL" + ")";
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //table ARTICLE
    public static String ARTICLE_TBL_NAME = "ARTICLE";
    
    //attributes of table ARTICLE
    public static String ARTICLE_COL_ID = "article_id";
    public static String ARTICLE_COL_TITLE = "article_title";
    public static String ARTICLE_COL_DESC = "article_desc";
    public static String ARTICLE_COL_CONTENT = "article_content";
    public static String ARTICLE_COL_PLACE_ID = "article_place_id";
    public static String ARTICLE_COL_CREATED_AT = "article_created_at";
    public static String ARTICLE_COL_UPDATED_AT = "article_updated_at";
    public static String ARTICLE_COL_CREATED_BY = "article_created_by";
    public static String ARTICLE_COL_UPDATED_BY = "article_updated_by";

    //Query for creating table ARTICLE
    public static String ARTICLE_TBL_CREATE = "CREATE TABLE " + ARTICLE_TBL_NAME + "("
            + ARTICLE_COL_ID + " INTEGER PRIMARY KEY, "
            + ARTICLE_COL_TITLE + " TEXT NOT NULL, "
            + ARTICLE_COL_DESC + " TEXT,"
            + ARTICLE_COL_CONTENT + " TEXT,"
            + ARTICLE_COL_PLACE_ID + " INTEGER NOT NULL,"
            + ARTICLE_COL_CREATED_AT + " TEXT,"
            + ARTICLE_COL_UPDATED_AT + " TEXT,"
            + ARTICLE_COL_CREATED_BY + " INT,"
            + ARTICLE_COL_UPDATED_BY + " INT" + ")";
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //table AUDIO
    public static String AUDIO_TBL_NAME = "AUDIO";
    
    //attributes of table AUDIO
    public static String AUDIO_COL_ID = "audio_id";
    public static String AUDIO_COL_TITLE = "audio_title";
    public static String AUDIO_COL_DESC = "audio_desc";
    public static String AUDIO_COL_FILENAME = "audio_filename";
    public static String AUDIO_COL_PLACE_ID = "audio_place_id";
    public static String AUDIO_COL_CREATED_AT = "audio_created_at";
    public static String AUDIO_COL_UPDATED_AT = "audio_updated_at";
    public static String AUDIO_COL_CREATED_BY = "audio_created_by";
    public static String AUDIO_COL_UPDATED_BY = "audio_updated_by";

    //Query for creating table AUDIO
    public static String AUDIO_TBL_CREATE = "CREATE TABLE " + AUDIO_TBL_NAME + "("
            + AUDIO_COL_ID + " INTEGER PRIMARY KEY, "
            + AUDIO_COL_TITLE + " TEXT NOT NULL, "
            + AUDIO_COL_DESC + " TEXT,"
            + AUDIO_COL_FILENAME + " TEXT,"
            + AUDIO_COL_PLACE_ID + " INTEGER NOT NULL,"
            + AUDIO_COL_CREATED_AT + " TEXT,"
            + AUDIO_COL_UPDATED_AT + " TEXT,"
            + AUDIO_COL_CREATED_BY + " INT,"
            + AUDIO_COL_UPDATED_BY + " INT" + ")";
        
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //table VIDEO
    public static String VIDEO_TBL_NAME = "VIDEO";
    
    //attributes of table VIDEO
    public static String VIDEO_COL_ID = "video_id";
    public static String VIDEO_COL_TITLE = "video_title";
    public static String VIDEO_COL_DESC = "video_desc";
    public static String VIDEO_COL_FILENAME = "video_filename";
    public static String VIDEO_COL_PLACE_ID = "video_place_id";
    public static String VIDEO_COL_CREATED_AT = "video_created_at";
    public static String VIDEO_COL_UPDATED_AT = "video_updated_at";
    public static String VIDEO_COL_CREATED_BY = "video_created_by";
    public static String VIDEO_COL_UPDATED_BY = "video_updated_by";

    //Query for creating table VIDEO
    public static String VIDEO_TBL_CREATE = "CREATE TABLE " + VIDEO_TBL_NAME + "("
            + VIDEO_COL_ID + " INTEGER PRIMARY KEY, "
            + VIDEO_COL_TITLE + " TEXT NOT NULL, "
            + VIDEO_COL_DESC + " TEXT,"
            + VIDEO_COL_FILENAME + " TEXT,"
            + VIDEO_COL_PLACE_ID + " INTEGER NOT NULL,"
            + VIDEO_COL_CREATED_AT + " TEXT,"
            + VIDEO_COL_UPDATED_AT + " TEXT,"
            + VIDEO_COL_CREATED_BY + " INT,"
            + VIDEO_COL_UPDATED_BY + " INT" + ")";
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //table IMAGE
    public static String IMAGE_TBL_NAME = "IMAGE";
    
    //attributes of table IMAGE
    public static String IMAGE_COL_ID = "image_id";
    public static String IMAGE_COL_TITLE = "image_title";
    public static String IMAGE_COL_DESC = "image_desc";
    public static String IMAGE_COL_FILENAME = "image_filename";
    public static String IMAGE_COL_PLACE_ID = "image_place_id";
    public static String IMAGE_COL_CREATED_AT = "image_created_at";
    public static String IMAGE_COL_UPDATED_AT = "image_updated_at";
    public static String IMAGE_COL_CREATED_BY = "image_created_by";
    public static String IMAGE_COL_UPDATED_BY = "image_updated_by";

    //Query for creating table IMAGE
    public static String IMAGE_TBL_CREATE = "CREATE TABLE " + IMAGE_TBL_NAME + "("
            + IMAGE_COL_ID + " INTEGER PRIMARY KEY, "
            + IMAGE_COL_TITLE + " TEXT NOT NULL, "
            + IMAGE_COL_DESC + " TEXT,"
            + IMAGE_COL_FILENAME + " TEXT,"
            + IMAGE_COL_PLACE_ID + " INTEGER NOT NULL,"
            + IMAGE_COL_CREATED_AT + " TEXT,"
            + IMAGE_COL_UPDATED_AT + " TEXT,"
            + IMAGE_COL_CREATED_BY + " INT,"
            + IMAGE_COL_UPDATED_BY + " INT" + ")";
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public Model(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        
        
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ARTICLE_TBL_CREATE);
        sqLiteDatabase.execSQL(AUDIO_TBL_CREATE);
        sqLiteDatabase.execSQL(IMAGE_TBL_CREATE);
        sqLiteDatabase.execSQL(PLACE_TBL_CREATE);
        sqLiteDatabase.execSQL(PLACE_TYPE_TBL_CREATE);
        sqLiteDatabase.execSQL(USER_TBL_CREATE);
        sqLiteDatabase.execSQL(VIDEO_TBL_CREATE);    

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ARTICLE_TBL_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AUDIO_TBL_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IMAGE_TBL_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PLACE_TBL_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PLACE_TYPE_TBL_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TBL_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VIDEO_TBL_NAME);
        onCreate(sqLiteDatabase);
    }
}

//Hrvoje Ljubic - 15. veljace, 2015