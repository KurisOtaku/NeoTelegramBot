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

    protected int error_code;
    protected String error_description;

    protected String file_id;
    protected int file_size;
    protected String file_path;
    protected boolean ok;

    public int Error_code() {
        return error_code;
    }

    public void Error_code(int error_code) {
        this.error_code = error_code;
    }

    public String Error_description() {
        return error_description;
    }

    public void Error_description(String error_description) {
        this.error_description = error_description;
    }

    public String File_id() {
        return file_id;
    }

    public void File_id(String file_id) {
        this.file_id = file_id;
    }

    public int File_size() {
        return file_size;
    }

    public void File_size(int file_size) {
        this.file_size = file_size;
    }

    public String File_path() {
        return file_path;
    }

    public void File_path(String file_path) {
        this.file_path = file_path;
    }

    public boolean isOk() {
        return ok;
    }

    public void Ok(boolean ok) {
        this.ok = ok;
    }

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
}
