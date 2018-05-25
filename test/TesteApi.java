/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import api.kuris.telegrambot.neo.ApiNeoBot;
import static api.kuris.telegrambot.neo.ApiNeoBot.validationToken;
import static api.kuris.telegrambot.neo.TelegramBotConnection.connectApi;
import static api.kuris.telegrambot.neo.TelegramBotConnection.postTelegramMessage;
import api.kuris.telegrambot.neo.TelegramResponseSend;
import br.zul.zwork2.http.ZHttpPost;
import br.zul.zwork2.log.ZLogFileWriter;
import org.json.JSONException;

/**
 *
 * @author cristiano.rosa
 */
public class TesteApi {

    public static void main(String[] args) throws JSONException {
        String token = "242688611:AAH1pzyJl1_7TifX6RzBWxuIjOj904ZL-G4";
        long id_master = 52022517;
        String[] botoes = String.valueOf(id_master).split("");
//        TelegramResponseSend send = ApiNeoBot.sendButton(token, id_master, token, botoes);
//        TelegramResponseSend send = ApiNeoBot.hideButton(token, id_master, token);
        System.out.println("");
    }


}
