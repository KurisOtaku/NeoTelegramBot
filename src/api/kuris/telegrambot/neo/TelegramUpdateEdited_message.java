/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdateEdited_message extends TelegramUpdateMessage {

    public int edit_date;           // Data de edição da mensagem

    public TelegramUpdateEdited_message(JSONObject message) throws JSONException {
        super(message);
        this.edit_date = message.getInt("edit_date");
    }

    public int getEdit_date() {
        return edit_date;
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
