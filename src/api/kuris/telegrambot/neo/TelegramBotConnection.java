/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import br.zul.zwork2.http.ZHttp;
import br.zul.zwork2.http.ZHttpPost;
import br.zul.zwork2.log.ZLogFileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramBotConnection {

    public static String getTelegramjson(String token) {
        String url = "https://api.telegram.org/bot";
        String command_api = "getUpdates";
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        String conteudojson = "{\"ok\":false,\"error_code\":000,\"description\":\"You don't have a telegrambot token\"}";
        try {
            conteudojson = new ZHttp()
                    .requestGet(url + token + "/" + command_api)
                    .send()
                    .getResponseText();
        } catch (Exception erro) {
            SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("Error in token?\n===================");
        }
        return conteudojson;
    }

    public static String postTelegramMessage(ZHttpPost request) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        String resposta = "";
        try {
            resposta = request.send().getResponseText().toString();
        } catch (Exception errorconnect) {
            resposta = "{\"ok\":false,\"error_code\":000,\"description\":\"By ApiNeoBot: Error don't find.\"}";
            System.out.println("erro ao conectar: ");
            System.out.println(errorconnect);
        }
        return resposta;
    }

    public static String setOffset(int lastupdate_id, String token) {
        String url = "https://api.telegram.org/bot";
        lastupdate_id++;
        String respostaservidor = null;
        respostaservidor = new ZHttp()
                .requestPost(url
                        + token
                        + "/getUpdates?offset="
                        + lastupdate_id)
                .send()
                .toString();
        return respostaservidor;
    }

    public static ZHttpPost connectApi(String token, String command) {
        ZHttpPost x = new ZHttp()
                .requestPost("https://api.telegram.org/bot"
                        + token
                        + "/"
                        + command);
        return x;
    }
}
