package com.teamcode.info.guideme.Model;

public class PlaceType {
	
    private int place_type_id;
    private String place_type_name;
    private String place_type_created_at;
    private String place_type_updated_at;
  
    public int getPlaceTypeID () {return place_type_id;}
    public void setPlaceTypeID (int place_type_id) {this.place_type_id = place_type_id;}

    public String getPlaceTypeName () {return place_type_name;}
    public void setPlaceTypeName (String place_type_name) {this.place_type_name = place_type_name;}
    
    public String getPlaceTypeCreatedAt () {return place_type_created_at;}
    public void setPlaceTypeCreatedAt (String place_type_created_at) {this.place_type_created_at = place_type_created_at;}

    public String getPlaceTypeUpdatedAt () {return place_type_updated_at;}
    public void setPlaceTypeUpdatedAt (String place_type_updated_at) {this.place_type_updated_at = place_type_updated_at;}

}
