package api.kuris.telegrambot.neo;

import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;
import org.json.JSONException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 *
 *
 * @author cristiano.rosa
 *
 */
public class LoggerApiNeoBot {

    final static Logger logger = Logger.getLogger(LoggerApiNeoBot.class);

    void error(String parameter) {
        logger.getRootLogger().removeAllAppenders();
        logger.getRootLogger().addAppender(new NullAppender());
        logger.error("Error : " + parameter);
    }

    void info(String parameter) {
        logger.info("Info : " + parameter);
    }

    void debug(String parameter) {
        if (logger.isDebugEnabled()) {
            logger.debug("This is debug : " + parameter);
        }
    }

    void warn(String parameter) {
        logger.warn("This is warn : " + parameter);
    }

    void fatal(String parameter) {
        logger.fatal("This is fatal : " + parameter);
    }

    void errorToken(String parameter) {
        logger.error("Erro de Token : " + parameter);
    }

    void errorMessageSize(String parameter) {
        logger.error("Erro de MessageSize : " + parameter);
    }

    void errorToSend(JSONException error) {
        logger.error("Erro ao enviar : " + error);
    }

    void errorToSend(Exception error) {
        logger.error("Erro ao enviar : " + error);
    }

    void errorOnDownload(JSONException error) {
        logger.error("Erro ao realizar download : " + error);
    }

    void errorButtonFlyLayout(String error) {
        logger.error("Erro nos botões Flutuantes: " + error);
    }

    void errorOffset(Exception trynot) {
        logger.error("Error no comando OffSet: " + trynot);
    }

    void errorToReply(JSONException error, Map<String, String> parameterMap) {
        logger.error("Erro ao responder:");
        logger.error(error);
        logger.error(parameterMap);
    }
}
