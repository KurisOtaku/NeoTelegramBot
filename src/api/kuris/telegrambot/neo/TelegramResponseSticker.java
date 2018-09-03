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


    protected boolean ok;

    // APENAS QUANDO DER ERRO
    private int error_code;
    private String error_description;

    ///////////////////////////////////////////////////////////////
    private int message_id;
    private int date;
    private TelegramUpdateFrom from;
    private TelegramUpdateChat chat;
    private TelegramUpdateSticker sticker;

    private TelegramUpdateReply_to_message reply_to_message;

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

    public int Message_id() {
        return message_id;
    }

    public void Message_id(int message_id) {
        this.message_id = message_id;
    }

    public TelegramUpdateSticker Sticker() {
        return sticker;
    }

    public void Sticker(TelegramUpdateSticker sticker) {
        this.sticker = sticker;
    }

    public TelegramUpdateReply_to_message Reply_to_message() {
        return reply_to_message;
    }

    public void Reply_to_message(TelegramUpdateReply_to_message reply_to_message) {
        this.reply_to_message = reply_to_message;
    }

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
