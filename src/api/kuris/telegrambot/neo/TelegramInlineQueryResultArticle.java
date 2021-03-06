/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramInlineQueryResultArticle {

    public static JSONArray montaInlineQueryResultArticle(String type, String title, String message_text) throws JSONException {
        JSONArray j = new JSONArray();
        JSONObject o = new JSONObject();
        o.put("type", type);
        o.put("id", "1");
        o.put("title", title);
        o.put("input_message_content", new JSONObject().put("message_text", message_text));
        j.put(o);
        return j;
    }

    public static JSONArray montaInlineQueryResultArticle(String type, String title[], String message_text[]) throws JSONException {
        JSONArray j = new JSONArray();
        int i = 0;
        for (String string : title) {
            JSONObject o = new JSONObject();
            o.put("type", type);
            o.put("id", i+1);
            o.put("title", string);
            JSONObject input_message_content = new JSONObject();
            input_message_content.put("message_text", message_text[i++]);
            o.put("input_message_content", input_message_content);
            j.put(o);
        }
        return j;
    }
}
