/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdate {

    public String token;
    public boolean exist_messages;
    public int update_id;
//    TelegramUpdateMessage message;
    public TelegramUpdateMessage message;
    public TelegramUpdateEdited_message edited_message;
    public TelegramUpdateInline_query inline_query;

    public int getUpdate_id() {
        return update_id;
    }

    public TelegramUpdateMessage getMessage() {
        return message;
    }

    public TelegramUpdateEdited_message getEdited_message() {
        return edited_message;
    }

    public TelegramUpdateInline_query getInline_query() {
        return inline_query;
    }

    // GETs DE OBJETOS INFERIORES
    public long userID() {
        return message.from.id_user;
    }

    public long chatID() {
        return message.chat.id_chat;
    }

    public String text() {
        return message.text;
    }

    public int messageId() {
        return message.message_id;
    }

    public String entiteType() {
        if (message.entities == null) {
            return null;
        } else {
            return message.entities.type;
        }
    }

    public Date date() {
        return message.date;
    }

    public TelegramUpdateFrom userInfo() {
        return message.from;
    }

    /*    public JSONObject getMessage() {
        return message;
    }
     */
    public TelegramUpdate(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        try {
            JSONArray results = jsonObj.getJSONArray("result");
            JSONObject updates = results.getJSONObject(0);
            try {
                this.update_id = updates.getInt("update_id");
            } catch (Exception tr_update) {
                this.update_id = 0;
            }
            try {
                try {
                    this.message = new TelegramUpdateMessage(updates.getJSONObject("message"));
                    this.edited_message = null;
                } catch (Exception tr4) {
                    this.message = null;
                    this.edited_message = new TelegramUpdateEdited_message(updates.getJSONObject("edited_message"));
                }
            } catch (Exception tr6) {
                this.message = null;
                this.edited_message = null;
            }
            try {
                this.inline_query = new TelegramUpdateInline_query(updates);
            } catch (Exception tr5) {
                this.inline_query = null;
            }
            this.exist_messages = true;
        } catch (Exception tr8) {
            this.exist_messages = false;
        }

    }

}
