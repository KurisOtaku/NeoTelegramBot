/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdateInline_query {

    protected String id;
    protected TelegramUpdateFrom from;
    protected String query;
    protected String offset;


    public TelegramUpdateInline_query(JSONObject update) throws JSONException {
        JSONObject inlinequery = update.getJSONObject("inline_query");
        this.id = inlinequery.getString("id");
        this.from = new TelegramUpdateFrom(inlinequery);
        this.query = inlinequery.getString("query");
        this.offset = inlinequery.getString("offset");
    }

    public String getId() {
        return id;
    }

    public TelegramUpdateFrom getFrom() {
        return from;
    }

    public String getQuery() {
        return query;
    }

    public String getOffset() {
        return offset;
    }
}
