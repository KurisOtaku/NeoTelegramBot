package api.kuris.telegrambot.neo;

import java.sql.Timestamp;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdateCallback_query {

    public String id_callback_query; // Por que uma string?
    public TelegramUpdateFrom from;  //
    public TelegramUpdateMessage messageOfBot; //
    public String chat_instance; // Por que uma string?
    public String data; //"DATA" = "DADOS/INFORMAÇÃO" //callback ou url

    TelegramUpdateCallback_query(JSONObject callback_query) {
        
        this.id_callback_query = callback_query.getString("id");
        this.data = callback_query.getString("data");
        this.chat_instance = callback_query.getString("chat_instance");
        this.from = new TelegramUpdateFrom(callback_query);
        this.messageOfBot = new TelegramUpdateMessage(callback_query.getJSONObject("message"));
    }

}
