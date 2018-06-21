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
public class TelegramResponseGetFile {

    public int error_code;
    public String error_description;

    public String file_id;
    public int file_size;
    public String file_path;
    public boolean ok;

    public TelegramResponseGetFile(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        try {
            JSONObject result = jsonObj.getJSONObject("result");
            this.file_id = result.getString("file_id");
            this.file_size = result.getInt("file_size");
            this.file_path = result.getString("file_path");
            this.error_code = 0;
            this.error_description = "#null";
            this.ok = true;
        } catch (Exception pegaErro1) {
            JSONObject result = jsonObj.getJSONObject("result");
            this.ok = false;
            this.file_id = null;
            this.file_size = 0;
            this.file_path = null;
            this.error_code = new JSONObject(json).getInt("error_code");
            this.error_description = new JSONObject(json).getString("description");
        }
    }

    public int getError_code() {
        return error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public String getFile_id() {
        return file_id;
    }

    public int getFile_size() {
        return file_size;
    }

    public String getFile_path() {
        return file_path;
    }

    public boolean isOk() {
        return ok;
    }

}
