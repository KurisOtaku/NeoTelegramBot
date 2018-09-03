package api.kuris.telegrambot.neo;

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

    protected String id_callback_query; // Por que uma string?
    protected TelegramUpdateFrom from;  //
    protected TelegramUpdateMessage messageOfBot; //
    protected String chat_instance; // Por que uma string?
    protected String data; //"DATA" = "DADOS/INFORMAÇÃO" //callback ou url

    public String id_callback_query() {
        return id_callback_query;
    }

    public void Id_callback_query(String id_callback_query) {
        this.id_callback_query = id_callback_query;
    }

    public TelegramUpdateFrom From() {
        return from;
    }

    public void From(TelegramUpdateFrom from) {
        this.from = from;
    }

    public TelegramUpdateMessage MessageOfBot() {
        return messageOfBot;
    }

    public void MessageOfBot(TelegramUpdateMessage messageOfBot) {
        this.messageOfBot = messageOfBot;
    }

    public String Chat_instance() {
        return chat_instance;
    }

    public void Chat_instance(String chat_instance) {
        this.chat_instance = chat_instance;
    }

    public String Data() {
        return data;
    }

    public void Data(String data) {
        this.data = data;
    }

    TelegramUpdateCallback_query(JSONObject callback_query, String token) {
        try {
            this.id_callback_query = callback_query.getString("id");
        } catch (Exception a) {
            this.id_callback_query = null;
        }
        try {
            this.data = callback_query.getString("data");
        } catch (Exception a) {
            this.data = null;
        }
        try {
            this.chat_instance = callback_query.getString("chat_instance");
        } catch (Exception a) {
            this.chat_instance = null;
        }
        try {
            this.from = new TelegramUpdateFrom(callback_query);
        } catch (Exception a) {
            this.from = null;
        }
        try {
            this.messageOfBot = new TelegramUpdateMessage(callback_query.getJSONObject("message"), token);
        } catch (Exception a) {
            this.messageOfBot = null;
        }

    }

    TelegramUpdateFrom from() {
        return this.from;
    }



}
