/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import org.json.*;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdatePhoto {

    protected String file_id;

    protected String caption;
    protected String format;

    public TelegramUpdatePhoto(JSONObject message, String token) throws JSONException {
        JSONArray photo_array = message.getJSONArray("photo");
        JSONObject photo_object = photo_array.getJSONObject(photo_array.length() - 1);
        this.file_id = photo_object.getString("file_id");
        photo_object = photo_array.getJSONObject(0);
        String pathFile = ApiNeoBot.getPathFile(token, file_id);
        String[] temp = pathFile.split("\\.");
        this.format = temp[temp.length - 1];
        this.caption = message.optString("caption", "");
    }

    public String getFile_id() {
        return file_id;
    }

    public void caption(String caption) {
        this.caption = caption;
    }

    public String getFormat() {
        return format;
    }

    public String getCaption() {
        return caption;
    }

}
