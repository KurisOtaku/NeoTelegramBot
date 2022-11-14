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
public class TelegramResponseSend {

    protected int error_code;
    protected String error_description;

    protected TelegramUpdateFrom from;
    protected TelegramUpdateChat chat;
    protected int date;
    protected TelegramUpdateDice dice;

    protected String text;
    protected int message_id;
    protected boolean ok;

    protected TelegramUpdateReply_to_message reply_to_message;

    protected String token;

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

    public String token() {
        return token;
    }

    public void Message_id(int message_id) {
        this.message_id = message_id;
    }

    public boolean isOk() {
        return ok;
    }

    public void Ok(boolean ok) {
        this.ok = ok;
    }

    public TelegramUpdateReply_to_message Reply_to_message() {
        return reply_to_message;
    }

    public void Reply_to_message(TelegramUpdateReply_to_message reply_to_message) {
        this.reply_to_message = reply_to_message;
    }

    public TelegramResponseSend(String json, String token) throws JSONException {
        if (json != null) {
            JSONObject jsonObj = new JSONObject(json);
            boolean ok = jsonObj.getBoolean("ok");
            if (ok) {
                try {
                    JSONObject result;
                    boolean b_result;
                    try {
                        result = jsonObj.getJSONObject("result");
                        this.message_id = result.getInt("message_id");
                        this.from = new TelegramUpdateFrom(result);
                        this.chat = new TelegramUpdateChat(result);
                        this.date = result.getInt("date");

                        try {
                            this.text = result.getString("text");
                        } catch (JSONException jSONException) {
                            this.text = null;
                        }

                        try {
                            this.reply_to_message = new TelegramUpdateReply_to_message(result, token);
                        } catch (Exception replyError) {
                            this.reply_to_message = null;
                        }
                        try {
                            this.dice = new TelegramUpdateDice(result);
                        } catch (Exception replyError) {
                            this.dice = null;
                        }
                        this.error_code = 0;
                        this.error_description = "#null";
                        this.token = token;
                    } catch (Exception ex) {
                        b_result = jsonObj.getBoolean("result");
                        this.ok = b_result;
                    }
                    this.ok = true;
                } catch (Exception pegaErro1) {
                    this.ok = false;
                    this.message_id = 0;
                    this.from = null;
                    this.chat = null;
                    this.dice = null;
                    this.date = 0;
                    this.text = "#null";
                    this.reply_to_message = null;
                    this.error_code = new JSONObject(json).getInt("error_code");
                    this.error_description = new JSONObject(json).getString("description");
                    this.token = token;
                }
            } else {
                this.ok = false;
                this.token = token;
                this.error_code = jsonObj.getInt("error_code");
                this.error_description = jsonObj.getString("description");
            }
        } else {
            this.ok = false;
            this.token = token;
            this.error_code = 0;
            this.error_description = "not-json";
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

    public TelegramUpdateDice getDice() {
        return dice;
    }

    public String getText() {
        return text;
    }
}
