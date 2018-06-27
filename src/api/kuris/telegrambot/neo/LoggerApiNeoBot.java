package api.kuris.telegrambot.neo;

import br.zul.zwork2.log.ZLogger;
import java.util.Map;
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
public class LoggerApiNeoBot {

    final static ZLogger logger = new ZLogger(LoggerApiNeoBot.class, "LogDoKuris");

    void error(String parameter) {
        logger.error.println("Error : " + parameter);
    }

    void info(String parameter) {
        logger.info.println("Info : " + parameter);
    }

//    void debug(String parameter) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("A wild debug appears : " + parameter);
//        }
//    }
    void warn(String parameter) {
        logger.warning.println("This is warn : " + parameter);
    }
    //    void fatal(String parameter) {
    //        if (logger.isDebugEnabled()) {
    //            logger.fatal("This is fatal : " + parameter);
    //        }
    //    }

    void errorToken(String parameter) {
        logger.error.println("Erro de Token : " + parameter);

    }

    void errorToSend(JSONException error) {
        logger.error.println("Erro ao enviar : " + error);
    }

    void errorToSend(Exception error) {
        logger.error.println("Erro ao enviar : " + error);

    }

    void errorOnDownload(JSONException error) {
        logger.error.println("Erro ao realizar download : " + error);

    }

    void errorButtonFlyLayout(String error) {
        logger.error.println("Erro nos botões Flutuantes: " + error);

    }

    void errorOffset(Exception trynot) {
        logger.error.println("Error no comando OffSet: " + trynot);

    }

    void errorToReply(JSONException error, Map<String, String> parameterMap) {
        logger.error.println("Erro ao responder:");
        logger.error.println(error.toString());
        logger.error.println(parameterMap.toString());
    }
}
