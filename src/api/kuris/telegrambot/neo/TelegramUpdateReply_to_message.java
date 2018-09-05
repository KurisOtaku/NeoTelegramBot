/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import java.util.Date;
import org.json.*;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdateReply_to_message extends TelegramUpdateMessage {

    public TelegramUpdateReply_to_message(JSONObject message, String token) throws JSONException {
        super(message.getJSONObject("reply_to_message"),token );
    }

    public int getMessage_id() {
        return message_id;
    }

    public long getDate_long() {
        return date_long;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public int getForward_date() {
        return forward_date;
    }

    public TelegramUpdateFrom getFrom() {
        return from;
    }

    public TelegramUpdateForward_from getForward_from() {
        return forward_from;
    }

    public TelegramUpdateChat getChat() {
        return chat;
    }

    public TelegramUpdateEntities getEntities() {
        return entities;
    }

    public TelegramUpdatePhoto getPhoto() {
        return photo;
    }

    public TelegramUpdateReply_to_message getReply_to_message() {
        return reply_to_message;
    }

    public TelegramUpdateSticker getSticker() {
        return sticker;
    }

    public TelegramUpdateDocument getDocument() {
        return document;
    }

    public TelegramUpdateLeft_chat getLeft_chat() {
        return left_chat;
    }
}
