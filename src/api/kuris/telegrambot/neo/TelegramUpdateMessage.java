/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import java.sql.Timestamp;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdateMessage {

    public int message_id;     // ID da mensagem (pode acabar repetindo)
    public long date_long;           // Data da mensagem
    public Date date;           // Data da mensagem
    public String text;        // Item importante, mas não presente em algumas mensagens
    public int forward_date;        // Item importante, mas não presente em algumas mensagens
    public TelegramUpdateFrom from;
    public TelegramUpdateForward_from forward_from;
    public TelegramUpdateChat chat;
    public TelegramUpdateEntities entities;
    public TelegramUpdatePhoto photo;
    public TelegramUpdateReply_to_message reply_to_message;
    public TelegramUpdateSticker sticker;
    public TelegramUpdateDocument document;
    public TelegramUpdateLeft_chat left_chat;
    public TelegramUpdateLocation location;

    public TelegramUpdateMessage(JSONObject message) throws JSONException {
        this.message_id = message.getInt("message_id");
        this.date_long = message.getLong("date");
        this.date = new Date(new Timestamp(date_long*1000).getTime());
        this.from = new TelegramUpdateFrom(message);
        this.chat = new TelegramUpdateChat(message);
        try {
            this.entities = new TelegramUpdateEntities(message);
        } catch (JSONException try1) {
            this.entities = null;
        }
        try {
            this.photo = new TelegramUpdatePhoto(message);
        } catch (JSONException try2) {
            this.photo = null;
        }
        try {
            this.text = message.getString("text");
        } catch (Exception try0) {
            try{
            if (photo == null) {
                this.text = null;
            } else if (photo != null){
                this.text = photo.caption;
            }else{
                this.text = null;
            }}catch(Exception tryCaption){
                this.text = null;
            }
        }
        try {
            this.reply_to_message = new TelegramUpdateReply_to_message(message);
        } catch (JSONException try3) {
            this.reply_to_message = null;
        }
        try {
            this.sticker = new TelegramUpdateSticker(message);
        } catch (JSONException try4) {
            this.sticker = null;
        }
        try {
            this.document = new TelegramUpdateDocument(message);
        } catch (JSONException try5) {
            this.document = null;
        }
        try {
            this.forward_from = new TelegramUpdateForward_from(message);
            this.forward_date = message.getInt("forward_date");
        } catch (JSONException try6) {
            this.forward_from = null;
            this.forward_date = 0;
        }
        try {
            this.left_chat = new TelegramUpdateLeft_chat(message);
        } catch (JSONException try7) {
            this.left_chat = null;
        }
        try{
            this.location = new TelegramUpdateLocation(message);
        }catch (JSONException try8){
            this.location = null;
        }
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
