/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot.brains.alice;

import br.zul.zwork2.http.ZHttp;
import br.zul.zwork2.http.ZHttpPost;
import br.zul.zwork2.log.ZLogFileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.zul.zwork2.http.ZHttp;
import br.zul.zwork2.http.ZHttpPost;
import br.zul.zwork2.log.ZLogFileWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import neotelegrambot.Translater;
import static neotelegrambot.Translater.userSay;

/**
 *
 * @author cristiano.rosa
 */
/**
 *
 * @author cristiano.rosa
 */
public class connectAlice {

    public static void main(String[] args) throws UnsupportedEncodingException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        String user = "Por que?";
        String text = userSay(user);
    }

    public static String toAi(String text) {
        ZHttpPost connectApi = connectApi();
        connectApi.putParameter("say", text);
        String postTelegramMessage = postTelegramMessage(connectApi);
        return postTelegramMessage;
    }

    public static String postTelegramMessage(ZHttpPost request) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        String resposta = "";
        try {
            resposta = request.send().getResponseText().toString();
        } catch (Exception errorconnect) {
            System.out.println("erro ao conectar: ");
            System.out.println(errorconnect);
        }
        return resposta;
    }

    public static ZHttpPost connectApi() {
        ZHttpPost x = new ZHttp()
                .requestPost("http://localhost:2001/");
        return x;
    }
}
