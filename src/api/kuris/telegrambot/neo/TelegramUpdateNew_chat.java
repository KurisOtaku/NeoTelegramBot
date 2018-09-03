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
public class TelegramUpdateNew_chat {

    protected int id;
    protected boolean is_bot;
    protected String first_name;
    protected String username;

    public TelegramUpdateNew_chat(JSONObject message) throws JSONException {
        JSONObject new_chat =  message.getJSONObject("new_chat_member");
        this.id = new_chat.getInt("id");
        this.is_bot = new_chat.getBoolean("is_bot");
        this.first_name = new_chat.getString("first_name");
        this.username = new_chat.getString("username");
    }

    public int getId() {
        return id;
    }

    public boolean isIs_bot() {
        return is_bot;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getUsername() {
        return username;
    }
    
    
}
