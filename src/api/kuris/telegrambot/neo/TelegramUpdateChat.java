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
public class TelegramUpdateChat {

    protected long id_chat;            // ID do chat (grupo ou user)
    protected String first_name;      // se repete
    protected String last_name;       // se repete
    protected String username;        // se repete
    protected String type;            // tipo de chat (protected, group...)
    protected String title;           // para grupos

    public TelegramUpdateChat(JSONObject message) throws JSONException {
        JSONObject chat = message.getJSONObject("chat");
        this.id_chat = chat.getLong("id");
        this.type = chat.getString("type");
        try {
            this.first_name = chat.getString("first_name");
        } catch (Exception e) {
            this.first_name = "#null";
        }
        try {
            this.last_name = chat.getString("last_name");
        } catch (Exception e) {
            this.last_name = "#null";
        }
        try {
            this.username = chat.getString("username");
        } catch (Exception e) {
            this.username = "#null";
        }

        try {
            this.title = chat.getString("title");
        } catch (JSONException t2) {
            this.title = "#null";
        }
    }

    public long getId_chat() {
        return id_chat;
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

    public String getType() {
        return type;
    }
}
