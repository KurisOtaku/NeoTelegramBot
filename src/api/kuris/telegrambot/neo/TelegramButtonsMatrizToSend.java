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
public class TelegramButtonsMatrizToSend {

    public static JSONObject montaMatrizTecladoVoador_url(String[] texts,
            String[] urls) throws JSONException {
        JSONObject finalJson = new JSONObject();
        JSONObject obj;
        JSONArray inlineKeyboardButtons = new JSONArray();
        JSONArray inlineKeyboardButton = new JSONArray();
        for (int i = 0; i < texts.length; i++) {
            obj = new JSONObject();
            obj.put("text", texts[i]);
            obj.put("url", urls[i]);
            inlineKeyboardButton.put(obj);
        }
        inlineKeyboardButtons.put(inlineKeyboardButton);
        finalJson.put("inline_keyboard", inlineKeyboardButtons);
        return finalJson;
    }

    public static JSONObject montaMatrizTecladoVoador_callback(String[] texts, String[] callbacks) throws JSONException {
        JSONObject finalJson = new JSONObject();
        JSONObject obj;
        JSONArray inlineKeyboardButtons = new JSONArray();
        JSONArray inlineKeyboardButton = new JSONArray();
        for (int i = 0; i < texts.length; i++) {
            obj = new JSONObject();
            obj.put("text", texts[i]);
            obj.put("callback_data", callbacks[i]);
            inlineKeyboardButton.put(obj);
        }
        inlineKeyboardButtons.put(inlineKeyboardButton);
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
