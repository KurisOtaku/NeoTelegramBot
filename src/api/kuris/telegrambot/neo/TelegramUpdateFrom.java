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
public class TelegramUpdateFrom {

    protected int id_user;            // ID usuário (utilizar como parametro - confiavel)
    protected boolean is_bot;         // indicador de bot (se é que isso é possível)
    protected String first_name;      // primeiro nome do usuário (não usar como parametro)
    protected String last_name;       // sobrenome do usuário (não usar como parametro)
    protected String username;        // @<nome_do_usuario> (não usar como parametro)
    protected String language_code;   // informação sobre linguagem do app do usuário (caso seja necessário)

    public TelegramUpdateFrom(JSONObject message) throws JSONException {
        JSONObject from = message.getJSONObject("from");
        this.id_user = from.getInt("id");
        this.is_bot = from.getBoolean("is_bot");
        this.first_name = from.getString("first_name");
        try {
            this.last_name = from.getString("last_name");
        } catch (JSONException trylastname) {
            this.last_name = "#null";
        }
        try {
            this.username = from.getString("username");
        } catch (Exception tryusername) {
            this.username = "#null";
        }
        try {
            this.language_code = from.getString("language_code");
        } catch (JSONException trylanguage_code) {
            this.language_code = "#null";
        }
    }

    public int getId_user() {
        return id_user;
    }

    public boolean isIs_bot() {
        return is_bot;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public String getLanguage_code() {
        return language_code;
    }

}
