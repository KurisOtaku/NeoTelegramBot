
import api.kuris.telegrambot.neo.ApiNeoBot;
import static api.kuris.telegrambot.neo.ApiNeoBot.getInstance;
import static api.kuris.telegrambot.neo.ApiNeoBot.getInstanceNoOffset;
import static api.kuris.telegrambot.neo.ApiNeoBot.send;
import static api.kuris.telegrambot.neo.ApiNeoBot.sendButtonFly_callback;
import static api.kuris.telegrambot.neo.ApiNeoBot.sendButtonFly_url;
import api.kuris.telegrambot.neo.TelegramResponseSend;
import api.kuris.telegrambot.neo.TelegramUpdate;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cristiano.rosa
 */
public class testes {

    public static void main1(String[] args) throws JSONException, UnsupportedEncodingException, IOException {
        String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
        int id_master = 52022517;
        TelegramResponseSend send = send(token, id_master, "oi");
        System.out.println(send.error_description);
    }



    public static void main3(String[] args) {
        String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
        int id_master = 52022517;
        int var = 1;
        String[] botoes = ("a" + var + ";a" + var).split(";");
        String[] urls = "google.com;google.com".split(";");
        sendButtonFly_url(token, id_master, "Teste1", botoes, urls);
        var++;
        botoes = ("a" + var + ";a" + var).split(";");
        sendButtonFly_callback(token, id_master, "Teste2", botoes, urls);
    }

    public static void main(String[] args) {
        String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
        int id_master = 52022517;
        int var = 1;
        String[] botoes = ("a" + var + ";a" + var).split(";");
        String[] urls = "google.com;google.com".split(";");
        sendButtonFly_callback(token, id_master, "Teste2", botoes, urls);
        TelegramUpdate update = getInstanceNoOffset(token);
        System.out.println(update);
    }

}
