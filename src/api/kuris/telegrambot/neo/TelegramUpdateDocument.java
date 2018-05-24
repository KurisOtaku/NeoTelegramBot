/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdateDocument {

    public String file_name;
    public String file_id; // se repete

    public TelegramUpdateDocument(JSONObject message) throws JSONException {
        JSONObject document = message.getJSONObject("document");
        this.file_name = document.getString("file_name");
                this.file_id = document.getString("file_id");
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_id() {
        return file_id;
    }

}
