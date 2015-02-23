package com.teamcode.info.guideme.Model;

public class Video {
	
    private int video_id;
    private String video_title;
    private String video_desc;
    private String video_filename;   
    private int video_place_id;
    private String video_created_at;
    private String video_updated_at;
    private int video_created_by;
    private int video_updated_by;
    
    public int getVideoID () {return video_id;}
    public void setVideoID (int video_id) {this.video_id = video_id;}

    public String getVideoTitle () {return video_title;}
    public void setVideoTitle (String video_title) {this.video_title = video_title;}

    public String getVideoDesc () {return video_desc;}
    public void setVideoDesc (String video_desc) {this.video_desc = video_desc;}

    public String getVideoFileName () {return video_filename;}
    public void setVideoFileName (String video_filename) {this.video_filename = video_filename;}

    public int getVideoPlaceID () {return video_place_id;}
    public void setVideoPlaceID (int video_place_id) {this.video_place_id = video_place_id;}
    
    public String getVideoCreatedAt () {return video_created_at;}
    public void setVideoCreatedAt (String video_created_at) {this.video_created_at = video_created_at;}

    public String getVideoUpdatedAt () {return video_updated_at;}
    public void setVideoUpdatedAt (String video_updated_at) {this.video_updated_at = video_updated_at;}

    public int getVideoCreatedBy () {return video_created_by;}
    public void setVideoCreatedBy (int video_created_by) {this.video_created_by = video_created_by;}
    
    public int getVideoUpdatedBy () {return video_updated_by;}
    public void setVideoUpdatedBy (int video_updated_by) {this.video_updated_by = video_updated_by;}

}
