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

public class TelegramUpdateForward_from  extends  TelegramUpdateFrom {

    public TelegramUpdateForward_from(JSONObject message) throws JSONException {
        super(message.getJSONObject("forward_form"));
    }

    public long getId_user() {
        return id_user;
    }

    public boolean isIs_bot() {
        return is_bot;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public String getLanguage_code() {
        return language_code;
    }
}
