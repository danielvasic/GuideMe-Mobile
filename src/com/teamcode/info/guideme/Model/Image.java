package com.teamcode.info.guideme.Model;

public class Image {
	
    private int image_id;
    private String image_title;
    private String image_desc;
    private String image_filename;   
    private int image_place_id;
    private String image_created_at;
    private String image_updated_at;
    private int image_created_by;
    private int image_updated_by;
    
    public int getImageID () {return image_id;}
    public void setImageID (int image_id) {this.image_id = image_id;}

    public String getImageTitle () {return image_title;}
    public void setImageTitle (String image_title) {this.image_title = image_title;}

    public String getImageDesc () {return image_desc;}
    public void setImageDesc (String image_desc) {this.image_desc = image_desc;}

    public String getImageFileName () {return image_filename;}
    public void setImageFileName (String image_filename) {this.image_filename = image_filename;}

    public int getImagePlaceID () {return image_place_id;}
    public void setImagePlaceID (int image_place_id) {this.image_place_id = image_place_id;}
    
    public String getImageCreatedAt () {return image_created_at;}
    public void setImageCreatedAt (String image_created_at) {this.image_created_at = image_created_at;}

    public String getImageUpdatedAt () {return image_updated_at;}
    public void setImageUpdatedAt (String image_updated_at) {this.image_updated_at = image_updated_at;}

    public int getImageCreatedBy () {return image_created_by;}
    public void setImageCreatedBy (int image_created_by) {this.image_created_by = image_created_by;}
    
    public int getImageUpdatedBy () {return image_updated_by;}
    public void setImageUpdatedBy (int image_updated_by) {this.image_updated_by = image_updated_by;}

}
