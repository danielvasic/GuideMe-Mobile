package com.teamcode.info.guideme.Model;

public class Audio {
	
    private int audio_id;
    private String audio_title;
    private String audio_desc;
    private String audio_filename;   
    private int audio_place_id;
    private String audio_created_at;
    private String audio_updated_at;
    private int audio_created_by;
    private int audio_updated_by;
    
    public int getAudioID () {return audio_id;}
    public void setAudioID (int audio_id) {this.audio_id = audio_id;}

    public String getAudioTitle () {return audio_title;}
    public void setAudioTitle (String audio_title) {this.audio_title = audio_title;}

    public String getAudioDesc () {return audio_desc;}
    public void setAudioDesc (String audio_desc) {this.audio_desc = audio_desc;}

    public String getAudioFileName () {return audio_filename;}
    public void setAudioFileName (String audio_filename) {this.audio_filename = audio_filename;}

    public int getAudioPlaceID () {return audio_place_id;}
    public void setAudioPlaceID (int audio_place_id) {this.audio_place_id = audio_place_id;}
    
    public String getAudioCreatedAt () {return audio_created_at;}
    public void setAudioCreatedAt (String audio_created_at) {this.audio_created_at = audio_created_at;}

    public String getAudioUpdatedAt () {return audio_updated_at;}
    public void setAudioUpdatedAt (String audio_updated_at) {this.audio_updated_at = audio_updated_at;}

    public int getAudioCreatedBy () {return audio_created_by;}
    public void setAudioCreatedBy (int audio_created_by) {this.audio_created_by = audio_created_by;}
    
    public int getAudioUpdatedBy () {return audio_updated_by;}
    public void setAudioUpdatedBy (int audio_updated_by) {this.audio_updated_by = audio_updated_by;}

}
