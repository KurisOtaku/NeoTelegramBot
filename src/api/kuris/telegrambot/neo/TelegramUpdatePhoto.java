/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdatePhoto {

    public String file_id;
    public String caption;

    public TelegramUpdatePhoto(JSONObject message)  throws JSONException{
        JSONArray photo_array = message.getJSONArray("photo");
        JSONObject photo_object = photo_array.getJSONObject(0);
        this.file_id = photo_object.getString("file_id");
        try{
            this.caption = photo_object.getString("caption");
        } catch (JSONException tr3){
            this.caption = "";
        }
        
    }

    public String getFile_id() {
        return file_id;
    }
    
    public String getCaption(){
        return caption;
    }
    
}
