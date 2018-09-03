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
public class TelegramResponseSendGif {

    protected String token;
    protected boolean exist_messages;
    protected int update_id;
    protected TelegramUpdateMessage message;
    protected TelegramUpdateEdited_message edited_message;
    protected TelegramUpdateInline_query inline_query;
    protected TelegramUpdateCallback_query callback_query;

    public String Token() {
        return token;
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
            return callback_query.from().id_user;
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
    
    public TelegramResponseSendGif(String json, String token) throws JSONException {
        System.out.println(json);
        /*
{
  "ok": true,
  "result": {
    "message_id": 2722,
    "from": {
      "id": 304076906,
      "is_bot": true,
      "first_name": "Newaii 😴",
      "username": "New_AI_bot"
    },
    "chat": {
      "id": 52022517,
      "first_name": "🇰 🇺 🇷 🇮 🇸",
      "last_name": "クリス",
      "username": "Kuris",
      "type": "private"
    },
    "date": 1535826715,
    "animation": {
      "file_name": "giphy.mp4",
      "mime_type": "video/mp4",
      "duration": 3,
      "width": 480,
      "height": 272,
      "thumb": {
        "file_id": "AAQEABPqWmEZAATK5Ur2Q8m0tw0fAQABAg",
        "file_size": 2273,
        "width": 90,
        "height": 51
      },
      "file_id": "CgADBAADj4sAAugaZAfrx4Ee7eHbvgI",
      "file_size": 238798
    },
    "document": {
      "file_name": "giphy.mp4",
      "mime_type": "video/mp4",
      "thumb": {
        "file_id": "AAQEABPqWmEZAATK5Ur2Q8m0tw0fAQABAg",
        "file_size": 2273,
        "width": 90,
        "height": 51
      },
      "file_id": "CgADBAADj4sAAugaZAfrx4Ee7eHbvgI",
      "file_size": 238798
    }
  }
}
        */
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
                    this.message = new TelegramUpdateMessage(updates.getJSONObject("message"), token);
                    this.edited_message = null;
                } catch (Exception tr4) {
                    this.message = null;
                    this.edited_message = new TelegramUpdateEdited_message(updates.getJSONObject("edited_message"), token);
                }
            } catch (Exception tr6) {
                this.message = null;
                this.edited_message = null;
            }
            try {
                this.callback_query = new TelegramUpdateCallback_query(updates.getJSONObject("callback_query"), token);
            } catch (Exception tr9) {
                this.callback_query = null;
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
