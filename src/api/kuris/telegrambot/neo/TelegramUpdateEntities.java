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
public class TelegramUpdateEntities {

    protected int offset;
    protected int length;
    protected String type; // se repete mas com outros valores

    public TelegramUpdateEntities(JSONObject message) throws JSONException {
        JSONArray entities_array = message.getJSONArray("entities");
        JSONObject entities_object = entities_array.getJSONObject(0);
        try {
            this.offset = entities_object.getInt("offset");
        } catch (Exception erroOfset) {
            this.offset = 0;
        }
        try {
            this.length = entities_object.getInt("length");
        } catch (Exception erroLength) {
            this.length = 0;
        }
        try {
            this.type = entities_object.getString("type");
        } catch (Exception erroType) {
            this.type = null;
        }

    }

    public int getOffset() {
        return offset;
    }

    public int length() {
        return length;
    }

    public String getType() {
        return type;
    }

}
