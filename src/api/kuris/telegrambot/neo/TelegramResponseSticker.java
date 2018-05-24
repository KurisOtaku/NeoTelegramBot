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
public class TelegramResponseSticker {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    /**
     *
     * @author cristiano.rosa
     */
    public boolean ok;

    // APENAS QUANDO DER ERRO
    public int error_code;
    public String error_description;

    ///////////////////////////////////////////////////////////////
    public int message_id;
    public int date;
    public TelegramUpdateFrom from;
    public TelegramUpdateChat chat;
    public TelegramUpdateSticker sticker;

    public TelegramUpdateReply_to_message reply_to_message;

    public TelegramResponseSticker(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        JSONObject result = jsonObj.getJSONObject("result");
        try {
            this.message_id = result.getInt("message_id");
            this.from = new TelegramUpdateFrom(result);
            this.chat = new TelegramUpdateChat(result);
            this.date = result.getInt("date");
            this.error_code = 0;
            this.error_description = "#null";
            this.ok = true;
        } catch (Exception pegaErro1) {
            this.message_id = 0;
            this.from = null;
            this.chat = null;
            this.date = 0;
            this.reply_to_message = null;
            this.error_code = new JSONObject(json).getInt("error_code");
            this.error_description = new JSONObject(json).getString("description");
            this.ok = false;
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

}
