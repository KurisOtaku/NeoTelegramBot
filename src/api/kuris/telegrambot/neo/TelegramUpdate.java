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
public class TelegramUpdate {

    protected String token;
    protected boolean exist_messages;
    protected int update_id;
    protected TelegramUpdateMessage message;
    protected TelegramUpdateEdited_message edited_message;
    protected TelegramUpdateInline_query inline_query;
    protected TelegramUpdateCallback_query callback_query;
    protected boolean callback;
    protected boolean textMessage;
    protected boolean inline;
    protected TypeMessage typemessage;

    public TypeMessage getTypemessage() {
        return typemessage;
    }

    public boolean isInline() {
        return inline;
    }

    public String Token() {
        return token;
    }

    public boolean isCallback() {
        return callback;
    }

    public boolean isTextMessage() {
        return textMessage;
    }

    public void Token(String token) {
        this.token = token;
    }

    public boolean isExist_messages() {
        return exist_messages;
    }

    public void Exist_messages(boolean exist_messages) {
        this.exist_messages = exist_messages;
    }

    public TelegramUpdateCallback_query Callback_query() {
        return callback_query;
    }

    public void Callback_query(TelegramUpdateCallback_query callback_query) {
        this.callback_query = callback_query;
    }

    public int Update_id() {
        return update_id;
    }

    public TelegramUpdateMessage Message() {
        return message;
    }

    public TelegramUpdateEdited_message Edited_message() {
        return edited_message;
    }

    public TelegramUpdateInline_query Inline_query() {
        return inline_query;
    }

    // s DE OBJETOS INFERIORES
    public long userID() {
        return message.from.id_user;
    }

    public long chatID() {
        if (callback_query != null) {
            return callback_query.messageOfBot.chat.id_chat;
        } else {
            return message.chat.id_chat;
        }
    }

    public void text(String text) {
        this.message.text = text;
    }

    public String text() {
        if (message == null) {
            return callback_query.Data();
        } else {
            return message.text;
        }
    }

    public int messageId() {
        if (message != null) {
            return message.message_id;
        } else if (callback_query != null) {
            return callback_query.messageOfBot.message_id;
        } else {
            return 0;
        }
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
    public TelegramUpdate(String json, String token) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        try {
            JSONArray results = jsonObj.getJSONArray("result");
            JSONObject updates = results.getJSONObject(0);
            try {
                this.update_id = updates.getInt("update_id");
            } catch (Exception tr_update) {
                this.update_id = 0;
            }
            this.textMessage = false;
            typemessage = TypeMessage.Text;
            this.callback = false;
            this.inline = false;
            try {
                try {
                    this.message = new TelegramUpdateMessage(updates.getJSONObject("message"), token);
                    this.edited_message = null;
                } catch (Exception tr4) {
                    this.message = null;
                    this.edited_message = new TelegramUpdateEdited_message(updates.getJSONObject("edited_message"), token);
                }
                this.textMessage = true;
                typemessage = TypeMessage.Text;
            } catch (Exception tr6) {
                this.message = null;
                this.edited_message = null;
            }
            try {
                this.callback_query = new TelegramUpdateCallback_query(updates.getJSONObject("callback_query"), token);
                this.callback = true;
                typemessage = TypeMessage.ButtonFly;
            } catch (Exception tr9) {
                this.callback_query = null;
            }
            try {
                this.inline_query = new TelegramUpdateInline_query(updates);
                this.inline = true;
                typemessage = TypeMessage.InLine;
            } catch (Exception tr5) {
                this.inline_query = null;
            }
            this.exist_messages = true;
        } catch (Exception tr8) {
            this.exist_messages = false;
        }

    }

    public boolean haveDice() {
        if (this.message.dice != null) {
            return this.message.dice.isExist();
        } else {
            return false;
        }
    }

    public TelegramUpdateDice dice() {
        return this.message.dice;
    }
}
