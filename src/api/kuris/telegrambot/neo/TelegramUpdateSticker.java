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
public class TelegramUpdateSticker {

    protected String emoji;               //Emoji do Sticker
    protected String set_name;            // Nome do pacote
    protected String file_id;             // ID do sticker (enviar este)

    public TelegramUpdateSticker(JSONObject message)  throws JSONException{
        JSONObject sticker = message.getJSONObject("sticker");
        try {
            this.emoji = sticker.getString("emoji");
        } catch (JSONException tryemoji) {
            this.emoji = "#noEmoji";
        }
        this.set_name = sticker.getString("set_name");
        this.file_id = sticker.getString("file_id");
    }

    public String getEmoji() {
        return emoji;
    }

    public String getSet_name() {
        return set_name;
    }

    public String getFile_id() {
        return file_id;
    }

}
