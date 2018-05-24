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
public class TelegramResponseSend {

    public int error_code;
    public String error_description;

    public TelegramUpdateFrom from;
    public TelegramUpdateChat chat;
    public int date;

    public String text;
    public int message_id;
    public boolean ok;

    public TelegramUpdateReply_to_message reply_to_message;

    public TelegramResponseSend(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        try {
            JSONObject result = jsonObj.getJSONObject("result");
            this.message_id = result.getInt("message_id");
            this.from = new TelegramUpdateFrom(result);
            this.chat = new TelegramUpdateChat(result);
            this.date = result.getInt("date");
            this.text = result.getString("text");
            try {
                this.reply_to_message = new TelegramUpdateReply_to_message(result);
            } catch (Exception replyError) {
                this.reply_to_message = null;
            }
            this.error_code = 0;
            this.error_description = "#null";
            this.ok = true;
        } catch (Exception pegaErro1) {
            this.ok = false;
            this.message_id = 0;
            this.from = null;
            this.chat = null;
            this.date = 0;
            this.text = "#null";
            this.reply_to_message = null;
            this.error_code = new JSONObject(json).getInt("error_code");
            this.error_description = new JSONObject(json).getString("description");
        }
    }

    public TelegramUpdateFrom getFrom() {
        return from;
    }

    public TelegramUpdateChat getChat() {
        return chat;
    }

    public int getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
