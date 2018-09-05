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
public class TelegramUpdateLeft_chat {
/*
    int id_removed;
    boolean is_bot_removed;
    String first_name_removed;
    String username_removed;
*/
    protected int id_remover;
    protected boolean is_bot_remover;
    protected String first_name_remover;
    protected String username_remover;

    public TelegramUpdateLeft_chat(JSONObject message)  throws JSONException{
        JSONObject left_chat_remover = message.getJSONObject("left_chat_member");
        this.id_remover = left_chat_remover.getInt("id");
        this.is_bot_remover = left_chat_remover.getBoolean("is_bot");
        this.first_name_remover =  left_chat_remover.getString("first_name");
        this.username_remover =  left_chat_remover.getString("username");
    }

    public int getId_remover() {
        return id_remover;
    }

    public boolean isIs_bot_remover() {
        return is_bot_remover;
    }

    public String getFirst_name_remover() {
        return first_name_remover;
    }

    public String getUsername_remover() {
        return username_remover;
    }

}
