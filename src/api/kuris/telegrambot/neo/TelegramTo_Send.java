/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramTo_Send {

    public int chat_id;
    public String text;
    public String token;

    public int getChat_id() {
        return chat_id;
    }

    public String getText() {
        return text;
    }

    public String getToken() {
        return token;
    }
}
