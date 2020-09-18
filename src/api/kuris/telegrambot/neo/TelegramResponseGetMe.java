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
public class TelegramResponseGetMe {

    private boolean ok;
    private long id_bot;
    private String first_name;
    private String username;

    TelegramResponseGetMe(String json) {
        JSONObject jsonObj = new JSONObject(json);
        this.ok = jsonObj.getBoolean("ok");
        jsonObj = jsonObj.getJSONObject("result");
        try {
            this.id_bot = jsonObj.getLong("id");
            this.first_name = jsonObj.getString("first_name");
            this.username = jsonObj.getString("username");
        } catch (JSONException replyError) {
            this.ok = false;
            this.id_bot = 0;
            this.first_name = "#null";
            this.username = "#null";
        }
    }

    public long getId_bot() {
        return id_bot;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getUsername() {
        return username;
    }

}
