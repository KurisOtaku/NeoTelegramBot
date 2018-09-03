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
public class TelegramResponseDelete {

    protected boolean ok;
    protected int error_code;
    protected String error_description;

    public boolean isOk() {
        return ok;
    }

    public void Ok(boolean ok) {
        this.ok = ok;
    }

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

    public TelegramResponseDelete(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        this.ok = jsonObj.getBoolean("ok");
        try {
            this.error_description = jsonObj.getString("description");
            this.error_code = jsonObj.getInt("error_code");
        } catch (Exception replyError) {
            this.error_description = null;
            this.error_code = 0;
        }

    }
}
