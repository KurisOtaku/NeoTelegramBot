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
public class TelegramUpdateDice {

    /* MODELO
    "dice": {
            "emoji": "\ud83c\udfaf",
            "value": 6
        }
     */

    protected boolean exist;
    protected String emoji;
    protected int value;

    public TelegramUpdateDice(JSONObject message) throws JSONException {
        JSONObject chat = message.getJSONObject("dice");
        try {
            this.value = chat.getInt("value");
            this.emoji = chat.getString("emoji");
            this.exist = true;
        } catch (Exception e) {
            this.value = 0;
            this.emoji = "#null";
            this.exist = false;
        }
    }

    public boolean isExist() {
        return exist;
    }

    public String emoji() {
        return emoji;
    }

    public int value() {
        return value;
    }


}
