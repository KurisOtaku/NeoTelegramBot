/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neotelegrambot;

import api.kuris.telegrambot.neo.ApiNeoBot;
import api.kuris.telegrambot.neo.TelegramUpdate;
import org.json.JSONException;

/**
 *
 * @author cristiano.rosa
 */
public class NeoTelegramBot {

    /**
     * @param args the command line arguments
     * @throws org.json.JSONException
     */
    /*
    242688611:AAH1pzyJl1_7TifX6RzBWxuIjOj904ZL-G4
     */
    public static void main2(String[] args) throws JSONException {
        String token = "242688611:AAH1pzyJl1_7TifX6RzBWxuIjOj904ZL-G4";
        int id_master = 52022517;
        int i = 0;
        while (i < 500) {
            TelegramUpdate x = ApiNeoBot.getInstance(token);
            try {
                if (x.message.text.toLowerCase().contains("oi".toLowerCase()) || x.message.text.toLowerCase().contains("ola".toLowerCase()) || x.message.text.toLowerCase().contains("olá".toLowerCase())) {
                    System.out.println("");
                    System.out.println(x.message.chat.id_chat);
                    System.out.println(x.message.text);
                    ApiNeoBot.sendReply(token, x.message.chat.id_chat, "Olá " + x.message.chat.first_name + "!", x.message.message_id);
                    ApiNeoBot.send(token, x.message.chat.id_chat, "Só sei responder isso por enquanto😅");
                    i = 0;
                } else if (x.message.text.toLowerCase().contains("eae".toLowerCase()) || x.message.text.toLowerCase().contains("iae".toLowerCase()) || x.message.text.toLowerCase().contains("eai".toLowerCase())) {
                    System.out.println("");
                    System.out.println(x.message.chat.id_chat);
                    System.out.println(x.message.text);
                    ApiNeoBot.sendReply(token, x.message.chat.id_chat, "É nóis " + x.message.chat.first_name + "!😎", x.message.message_id);
                    i = 0;
                } else if (x.message.from.id_user == id_master && x.message.text.equalsIgnoreCase("Desligar LFbot")) {
                    i = 1000;
                } else if (i == 400) {
                    System.out.println("");
                    ApiNeoBot.send(token, id_master, "Vou desligar daquia pouco");
                } else {
                    if(x != null){
                    System.out.println("");
                    System.out.println(x.message.chat.id_chat);
                    System.out.println(x.message.text);
                    ApiNeoBot.sendReply(token, x.message.chat.id_chat, "Não sei o que ''" + x.message.text + "'' quer dizer", x.message.message_id);
                    }
                }
            } catch (Exception t) {
                System.out.print(".");
            }
            i++;
        }
        ApiNeoBot.send(token, id_master, "Desligando");
        System.out.println("Desligando");
    }
}
