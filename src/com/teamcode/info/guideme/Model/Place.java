package com.teamcode.info.guideme.Model;

public class Place {
	
    private int place_id;
    private String place_name;
    private String place_desc;
    private String place_address;   
    private String place_email;
    private String place_url;
    private String place_phone;
    private double place_lat;
    private double place_lng;    
    private int place_type_id;
    private String place_created_at;
    private String place_updated_at;
    private int place_created_by;
    private int place_updated_by;
    
    public int getPlaceID () {return place_id;}
    public void setPlaceID (int place_id) {this.place_id = place_id;}

    public String getPlaceName () {return place_name;}
    public void setPlaceName (String place_name) {this.place_name = place_name;}

    public String getPlaceDesc () {return place_desc;}
    public void setPlaceDesc (String place_desc) {this.place_desc = place_desc;}

    public String getPlaceAddress () {return place_address;}
    public void setPlaceAddress (String place_address) {this.place_address = place_address;}

    public String getPlaceEmail () {return place_email;}
    public void setPlaceEmail (String place_email){this.place_email = place_email;}

    public String getPlaceUrl () {return place_url;}
    public void setPlaceUrl (String place_url){this.place_url = place_url;}
    
    public String getPlacePhone () {return place_phone;}
    public void setPlacePhone (String place_phone) {this.place_phone = place_phone;}

    public double getPlaceLat () {return place_lat;}
    public void setPlaceLat (double place_lat) {this.place_lat = place_lat;}    

    public double getPlaceLng () {return place_lng;}
    public void setPlaceLng (double place_lng) {this.place_lng = place_lng;}
    
    public int getPlaceTypeID () {return place_type_id;}
    public void setPlaceTypeID (int place_type_id) {this.place_type_id = place_type_id;}
    
    public String getPlaceCreatedAt () {return place_created_at;}
    public void setPlaceCreatedAt (String place_created_at) {this.place_created_at = place_created_at;}

    public String getPlaceUpdatedAt () {return place_updated_at;}
    public void setPlaceUpdatedAt (String place_updated_at) {this.place_updated_at = place_updated_at;}

    public int getPlaceCreatedBy () {return place_created_by;}
    public void setPlaceCreatedBy (int place_created_by) {this.place_created_by = place_created_by;}
    
    public int getPlaceUpdatedBy () {return place_updated_by;}
    public void setPlaceUpdatedBy (int place_updated_by) {this.place_updated_by = place_updated_by;}

}
