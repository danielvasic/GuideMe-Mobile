package com.teamcode.info.guideme.Model;

public class Article {
	
    private int article_id;
    private String article_title;
    private String article_desc;
    private String article_content;   
    private int article_place_id;
    private String article_created_at;
    private String article_updated_at;
    private int article_created_by;
    private int article_updated_by;
    
    public int getArticleID () {return article_id;}
    public void setArticleID (int article_id) {this.article_id = article_id;}

    public String getArticleTitle () {return article_title;}
    public void setArticleTitle (String article_title) {this.article_title = article_title;}

    public String getArticleDesc () {return article_desc;}
    public void setArticleDesc (String article_desc) {this.article_desc = article_desc;}

    public String getArticleContent () {return article_content;}
    public void setArticleContent (String article_content) {this.article_content = article_content;}

    public int getArticlePlaceID () {return article_place_id;}
    public void setArticlePlaceID (int article_place_id) {this.article_place_id = article_place_id;}
    
    public String getArticleCreatedAt () {return article_created_at;}
    public void setArticleCreatedAt (String article_created_at) {this.article_created_at = article_created_at;}

    public String getArticleUpdatedAt () {return article_updated_at;}
    public void setArticleUpdatedAt (String article_updated_at) {this.article_updated_at = article_updated_at;}

    public int getArticleCreatedBy () {return article_created_by;}
    public void setArticleCreatedBy (int article_created_by) {this.article_created_by = article_created_by;}
    
    public int getArticleUpdatedBy () {return article_updated_by;}
    public void setArticleUpdatedBy (int article_updated_by) {this.article_updated_by = article_updated_by;}

}
