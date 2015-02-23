package com.teamcode.info.guideme.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.teamcode.info.guideme.Model.Article;

/* Created by Hrvoje on 15.2.2015.
 * Klasa za 'upravljanje' bazom. */

public class Articles extends Model {
    private Context ctx;

    public Articles(Context context) {
        super(context);
        ctx = context;
    }

    public Article save (Article l) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put(ARTICLE_COL_ID, l.getArticleID());
        cv.put(ARTICLE_COL_TITLE, l.getArticleTitle());
        cv.put(ARTICLE_COL_DESC, l.getArticleDesc());
        cv.put(ARTICLE_COL_CONTENT, l.getArticleContent());
        cv.put(ARTICLE_COL_PLACE_ID, l.getArticlePlaceID());
        cv.put(ARTICLE_COL_CREATED_AT, l.getArticleCreatedAt());
        cv.put(ARTICLE_COL_UPDATED_AT, l.getArticleUpdatedAt());
        cv.put(ARTICLE_COL_CREATED_BY, l.getArticleCreatedBy());
        cv.put(ARTICLE_COL_UPDATED_BY, l.getArticleUpdatedBy());
        
        l.setArticleID((int) db.insert(ARTICLE_TBL_NAME, null, cv));        
        db.close();
        return l;
    };

    public List<Article> getArticles () {
        SQLiteDatabase db = getReadableDatabase();
        String cols[] = {ARTICLE_COL_ID, ARTICLE_COL_TITLE, ARTICLE_COL_DESC, ARTICLE_COL_CONTENT, ARTICLE_COL_PLACE_ID, ARTICLE_COL_CREATED_AT,ARTICLE_COL_UPDATED_AT, ARTICLE_COL_CREATED_BY, ARTICLE_COL_UPDATED_BY};
        Cursor c = db.query(ARTICLE_TBL_NAME, cols, null, null, null, null, null);
        c.moveToFirst();
        List<Article> articles = new ArrayList<Article>();
        do {
            articles.add(CursorArticle(c));
        }while (c.moveToNext());
        c.close();
        db.close();
        return articles;
    };

    public Article getArticleByID (int id) {
        List<Article> articles = new ArrayList<Article>();
        articles = getArticles();
        for (Article article : articles) {
            if (article.getArticleID() == id) return article;
        }
        return null;
    };
    
    public void deleteArticle (Article l) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ARTICLE_TBL_NAME, ARTICLE_COL_ID + "=" + l.getArticleID(), null);
        db.close();
    };

    public int ArticleTableCount(){
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor mCount= db.rawQuery("SELECT COUNT(*) FROM " + ARTICLE_TBL_NAME, null);
    	mCount.moveToFirst();
    	int count= mCount.getInt(0);
    	mCount.close();
    	db.close();
    	return count;
    	
    	/*String countQuery = "SELECT * FROM " + ARTICLE_TBL_NAME;
    	SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;*/
    }
    
    private Article CursorArticle (Cursor c) {
    	Article article = new Article();
    	
        article.setArticleID(c.getInt(0));
        article.setArticleTitle(c.getString(1));
        article.setArticleDesc(c.getString(2));
        article.setArticleContent(c.getString(3));
        article.setArticlePlaceID(c.getInt(4));
        article.setArticleCreatedAt(c.getString(5));
        article.setArticleUpdatedAt(c.getString(6));
        article.setArticleCreatedBy(c.getInt(7));
        article.setArticleUpdatedBy(c.getInt(8));
        
        return article;
    };

    public void clearDatabase() {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.delete(ARTICLE_TBL_NAME, null, null);
    }
}
