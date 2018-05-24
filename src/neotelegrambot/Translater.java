/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neotelegrambot;

import br.zul.zwork2.encryptor.ZEncryptor;
import br.zul.zwork2.http.ZHttp;
import br.zul.zwork2.http.ZHttpGet;
import br.zul.zwork2.log.ZLogFileWriter;
import br.zul.zwork2.log.ZLogger;
import br.zul.zwork2.log.ZVoidLogFileWriter;
import br.zul.zwork2.url.ZUrl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * @author cristiano.rosa
 */
public class Translater {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //PREPARA O LOGGER
        ZLogFileWriter.setDefaultLogFileWriter(new ZVoidLogFileWriter());
        ZLogger.DEFAULT_PRINT_CLASS_METHOD_NAME_ON_CONSOLE = false;
        //TESTE
        // Instantiates a client

        final String text = "oi amigo";
        final String languageFrom = "pt";
        final String languageTo = "en";
        String result = translate(languageFrom, languageTo, text);
        System.out.printf("Texto original (%s): %s\r\nTexto traduzido (%s): %s\r\n", languageFrom, text, languageTo, result);
    }

    public static String tratarTexto(String text){
        text = text.replace("&quot;","\"");
        return text;
    }
    
    public static String botSay(String textToTranslate) throws UnsupportedEncodingException{
        return tratarTexto(translate("en","pt",textToTranslate));
    }
    
    public static String userSay(String textToTranslateToBot) throws UnsupportedEncodingException{
        return tratarTexto(translate("pt","en",textToTranslateToBot));
    }
    
    public static String translate(String languageFrom, String languageTo, String text) throws UnsupportedEncodingException {

        ZUrl url = new ZUrl("https://www.googleapis.com/language/translate/v2?key=AIzaSyAAmH1dq75lia-JMzuiAviiBKQdk5s1fhc&callback=gtt");
        url = url.addParameter("source", languageFrom);
        url = url.addParameter("target", languageTo);
        url = url.addParameter("q", URLEncoder.encode(text, "UTF-8"));

        ZHttp http = new ZHttp();
        ZHttpGet request = http.requestGet(url);
        request.setRequestProperty("referer", new ZEncryptor("0").decript("3eeady//iiix7Zh4W007xW08/"));
        String x = request.send().getResponseText(true).fromLeft("(").toRight(")").asJson().get("data", "translations", "0", "translatedText").asString();
        return x;

    }

}
