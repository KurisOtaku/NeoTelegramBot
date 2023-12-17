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
public class TelegramButtonsMatrizToSend {


    public static JSONObject montaMatrizTecladoVoador_url(String[] texts,
            String[] urls) throws JSONException {
        JSONObject finalJson = new JSONObject();
        JSONObject obj;
        JSONArray inlineKeyboardButtons = new JSONArray();
        JSONArray inlineKeyboardButton = new JSONArray();
        JSONArray array = null;
        for (int i = 0; i < texts.length; i++) {
            array = new JSONArray();
            obj = new JSONObject();
            obj.put("text", texts[i]);
            obj.put("url", urls[i]);
            //array.put(obj);
            inlineKeyboardButton.put(obj);
        }
        inlineKeyboardButtons.put(inlineKeyboardButton);
        finalJson.put("inline_keyboard", inlineKeyboardButtons);
        return finalJson;
    }

    public static JSONObject montaMatrizTecladoVoador_switch_inline_current_chat(String[] texts,
            String[] query) throws JSONException {
        JSONObject finalJson = new JSONObject();
        JSONObject obj;
        JSONArray inlineKeyboardButtons = new JSONArray();
        JSONArray inlineKeyboardButton = new JSONArray();
        JSONArray array = null;
        for (int i = 0; i < texts.length; i++) {
            array = new JSONArray();
            obj = new JSONObject();
            obj.put("text", texts[i]);
            obj.put("switch_inline_query_current_chat", query[i]);
            //array.put(obj);
            inlineKeyboardButton.put(obj);
        }
        inlineKeyboardButtons.put(inlineKeyboardButton);
        finalJson.put("inline_keyboard", inlineKeyboardButtons);
        return finalJson;
    }
 static JSONObject montaListTecladoVoador_callback_equilibrado(String[] texts, String[] callbacks) {
        JSONObject finalJson = new JSONObject();
        JSONObject obj;
        JSONArray inlineKeyboardButtons = new JSONArray();
        JSONArray inlineKeyboardButton = new JSONArray();
        for (int i = 0; i < texts.length; i++) {
                inlineKeyboardButton = new JSONArray();
                inlineKeyboardButtons.put(inlineKeyboardButton);
            obj = new JSONObject();
            obj.put("text", texts[i]);
            obj.put("callback_data", callbacks[i]);
            inlineKeyboardButton.put(obj);
        }
        finalJson.put("inline_keyboard", inlineKeyboardButtons);
        return finalJson;
    }
 
    static JSONObject montaMatrizTecladoVoador_callback_equilibrado(String[] texts, String[] callbacks) {
        JSONObject finalJson = new JSONObject();
        JSONObject obj;
        int numeroColunas = (int) Math.floor(Math.sqrt(texts.length));
        JSONArray inlineKeyboardButtons = new JSONArray();
        JSONArray inlineKeyboardButton = new JSONArray();
        for (int i = 0; i < texts.length; i++) {
            int posicaoColuna = i % numeroColunas;
            if (posicaoColuna == 0) {
                inlineKeyboardButton = new JSONArray();
                inlineKeyboardButtons.put(inlineKeyboardButton);
            }
            obj = new JSONObject();
            obj.put("text", texts[i]);
            obj.put("callback_data", callbacks[i]);
            inlineKeyboardButton.put(obj);
        }
        finalJson.put("inline_keyboard", inlineKeyboardButtons);
        return finalJson;
    }

    public static JSONObject montaMatrizTecladoVoador_callback_multilines(String[] texts, String[] callbacks) throws JSONException {
        JSONObject finalJson = new JSONObject();
        JSONObject obj;
        JSONArray inlineKeyboardButtons = new JSONArray();
        JSONArray inlineKeyboardButton = new JSONArray();
        JSONArray linhas = new JSONArray();
        for (int i = 0; i < texts.length; i++) {
            obj = new JSONObject();
            obj.put("text", texts[i]);
            obj.put("callback_data", callbacks[i]);
            inlineKeyboardButton = new JSONArray();
            inlineKeyboardButton.put(obj);
            inlineKeyboardButtons.put(inlineKeyboardButton);
        }
        finalJson.put("inline_keyboard", inlineKeyboardButtons);
        return finalJson;
    }

    public static JSONObject montaMatrizTeclado(String[] dados) throws JSONException {
        JSONObject finalJson = new JSONObject();
        int numeroColunas = (int) Math.floor(Math.sqrt(dados.length));
        JSONArray result = new JSONArray();
        JSONArray botoes = null;
        for (int i = 0; i < dados.length; i++) {
            int posicaoColuna = i % numeroColunas;
            if (posicaoColuna == 0) {
                botoes = new JSONArray();
                result.put(botoes);
            }
            botoes.put(dados[i]);
        }
        finalJson.put("keyboard", result);
        finalJson.put("one_time_keyboard", true);
        finalJson.put("resize_keyboard", true);
        return finalJson;
    }

}
