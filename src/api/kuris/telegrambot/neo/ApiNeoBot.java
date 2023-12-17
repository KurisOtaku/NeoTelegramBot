/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    
    Mais informações sobre a API do Telegram:
    https://core.telegram.org/bots/api
 */
package api.kuris.telegrambot.neo;

import br.zul.zwork2.http.*;
import br.zul.zwork2.log.ZLogFileWriter;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.json.*;

/**
 *
 * @author STI
 */
public class ApiNeoBot {

    final static LoggerApiNeoBot logger = new LoggerApiNeoBot();
    final public static int limitSizeText = 4096;

    public static void send_Location(String token, long id, Float valueOf, Float valueOf0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void validationTitlesAnswerInline(String[] titles, String[] text) throws Exception {
        if (titles.length != text.length) {
            throw new Exception("titles.length != text.length");
        } else if (titles.length > 50) {
            throw new Exception("titles.length > 50");
        } else if (titles.length < 1) {
            throw new Exception("titles.length < 0");
        } else {
            Stream.of(titles, text)
                    .flatMap(Stream::of)
                    .filter(""::equals)
                    .findFirst()
                    .ifPresent(s -> {
                        throw new RuntimeException("has item empty");
                    });
        }
    }

    private String token;
    private long id_master;

    public long getId_master() {
        return id_master;
    }

    public long getId_chat_main() {
        return id_chat_main;
    }
    private long id_chat_main;

    /**
     *
     * @param token Fornecido pela Telegram
     * @param id_master Seu ID no telegram (deve ser possitivo)
     * @param id_chat_main ID do chat principal (caso exista) (deve ser
     * negativo)
     * @throws Throwable
     */
    public ApiNeoBot(String token, long id_master, long id_chat_main) throws Throwable {
        try {
            if (id_master <= 0) {
                throw new Throwable("ID MESTRE DEVE SER MAIOR QUE ZERO");
            }
            if (id_chat_main >= 0) {
                throw new Throwable("ID MESTRE DEVE SER MENOR QUE ZERO");
            }
            if (token.equals("") || token.contains(" ")) {
                throw new Throwable("HÁ ERROS NO TOKEN");
            }
            if (token.contains("bot")) {
                token = token.replace("bot", "");
            }
            this.token = token;
            this.id_master = id_master;
            this.id_chat_main = id_chat_main;

            TelegramResponseSend send = send(id_master, "Bot Pronto");
            if (send.ok) {
                System.out.println("BOT Pronto");
            } else {
                System.out.println("BOT OFFLINE");
                throw new Throwable("Ocorreu um erro. Verifique os seguintes itens:\n"
                        + "- Conexão com internet;\n"
                        + "- Token;\n"
                        + "- ID mestre (usado na avaliação);\n"
                        + "- Chat com o bot deve estar no mínimo iniciado para que o mesmo possa enviar uma mensagem;\n"
                        + "- Acredita em Deus(es)?");
            }
        } catch (Throwable throwable) {
            throwable = new Throwable("Ocorreu um erro. Verifique os seguintes itens:\n"
                    + "- Conexão com internet;\n"
                    + "- Token;\n"
                    + "- ID mestre (usado na avaliação);\n"
                    + "- Chat com "
                    + "o bot deve estar no mínimo iniciado para que o mesmo possa enviar uma mensagem;\n"
                    + "- Acredita em Deus(es)?");
        }
    }

    /**
     *
     * @return Telegram GET ME (informações do bot)
     */
    public TelegramResponseGetMe getMe() {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseGetMe telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "getMe");
            connection.setAutoDownloadCertificates(true);
            try {
                telegram = new TelegramResponseGetMe(TelegramBotConnection.postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    /**
     * Busca o primeiro UPDATE da API da Telegram
     *
     * @return TelegramUpdate (objeto de Mensagem)
     * @throws JSONException
     */
    public TelegramUpdate getInstance() throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramUpdate t_update = null;
        if (validationToken(this.token)) {
            try {
                String conteudojson = TelegramBotConnection.getTelegramjson(this.token);
                t_update = new TelegramUpdate(conteudojson, this.token);
                t_update.Token(this.token);
                TelegramBotConnection.setOffset(t_update.Update_id(), this.token);
                return t_update;
            } catch (Exception trynot) {
                logger.error("Erro ao buscar mensagens:" + trynot);
                t_update = new TelegramUpdate(token, token);
                t_update.text("I don't have messages");
                return t_update;
            }
        } else {
            logger.errorToken(this.token);
            t_update = null;
            return t_update;
        }
    }

    /**
     * Busca o primeiro UPDATE da API da Telegram sem Offset
     *
     * @return TelegramUpdate (objeto de Mensagem)
     * @throws JSONException
     *
     * @throws JSONException
     */
    public TelegramUpdate getInstanceNoOffset() throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramUpdate t_update = null;
        if (validationToken(token)) {
            try {
                String conteudojson = TelegramBotConnection.getTelegramjson(token);
                t_update = new TelegramUpdate(conteudojson, token);
                t_update.Token(token);
                return t_update;
            } catch (Exception trynot) {
                logger.error("Erro ao buscar mensagens:" + trynot);
                t_update.text("I don't have messages");
                return t_update;
            }
        } else {
            logger.errorToken(token);
            t_update = null;
            return t_update;
        }
    }

    /**
     * Envia mensagem de texto normal com opção de notificação
     *
     * @param chat_id_to_send
     * @param text_to_send
     * @param notification
     * @return TelegramResponseSend(Object)
     * @throws JSONException
     *
     * @throws JSONException
     */
    public TelegramResponseSend send(long chat_id_to_send, String text_to_send) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    /**
     * Envia mensagem de texto normal com opção de notificação
     *
     * @param chat_id_to_send
     * @param text_to_send
     * @param notification
     * @return TelegramResponseSend(Object)
     * @throws JSONException
     *
     * @throws JSONException
     */
    public TelegramResponseSend send(long chat_id_to_send, String text_to_send, boolean notification) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        notification = !notification;
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("disable_notification", notification + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    /**
     * Envia um dado normal ou dardo.
     *
     * @param chat_id_to_send
     * @param dice_emoji
     * @return TelegramResponseSend(Object)
     * @throws JSONException
     *
     * @throws JSONException
     */
    public TelegramResponseSend sendDice(long chat_id_to_send, DiceEmoji dice_emoji) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendDice");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("emoji", dice_emoji.toString() + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    /**
     * Envia um dado normal ou dardo.
     *
     * @param chat_id_to_send
     * @param String dice_emoji
     * @return TelegramResponseSend(Object)
     * @throws JSONException
     *
     * @throws JSONException
     */
    public TelegramResponseSend sendDice(long chat_id_to_send, String dice_emoji) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendDice");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("emoji", dice_emoji);
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    /**
     *
     * Envia mensagem de texto normal e botões(sendo este, um Array)
     *
     * @param chat_id_to_send
     * @param text_to_send
     * @param buttons
     * @return TelegramResponseSend(Object)
     * @throws JSONException
     */
    public TelegramResponseSend sendButton(long chat_id_to_send, String text_to_send, String[] buttons) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
        JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTeclado(buttons);
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("reply_markup", matriz.toString());
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public TelegramResponseSend sendButtonFly_url(long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] urls) throws JSONException {
        if (button_texts.length != urls.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_url(button_texts, urls);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public TelegramResponseSend sendButtonFly_switch_inline_current_chat(long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] urls) throws JSONException {
        if (button_texts.length != urls.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_switch_inline_current_chat(button_texts, urls);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public TelegramResponseSend sendButtonFly_callback(long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] callbacks, String switch_pm_text) throws JSONException {
        if (button_texts.length != callbacks.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            // JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_multilines(button_texts, callbacks);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_equilibrado(button_texts, callbacks);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("switch_pm_text", switch_pm_text);
                connection.putParameter("switch_pm_parameter", switch_pm_text);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public TelegramResponseSend sendButtonFly_callback(long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] callbacks) throws JSONException {
        if (button_texts.length != callbacks.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            // JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_multilines(button_texts, callbacks);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_equilibrado(button_texts, callbacks);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public TelegramResponseSend sendVerticalButtonFly_callback(long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] callbacks) throws JSONException {
        if (button_texts.length != callbacks.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            // JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_multilines(button_texts, callbacks);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_multilines(button_texts, callbacks);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public TelegramResponseSend editMessage(
            long chat_id_to_send, int message_id, String newText) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "editMessageText");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("message_id", message_id + "");
            connection.putParameter("text", newText + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToReply(error, connection.parameterMap());
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public TelegramResponseSend editMessage(
            long chat_id_to_send, int message_id, String newText, String parse_mode) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "editMessageText");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("message_id", message_id + "");
            connection.putParameter("text", newText + "");
            connection.putParameter("parse_mode", parse_mode);
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToReply(error, connection.parameterMap());
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public TelegramResponseDelete deleteMessage(long chat_id_to_delete, int message_id) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseDelete telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "deleteMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_delete + "");
            connection.putParameter("message_id", message_id + "");
            try {
                telegram = new TelegramResponseDelete(TelegramBotConnection.postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public TelegramResponseSend sendMarkdown(long chat_id_to_send, String text_to_send, String parse_mode) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("parse_mode", parse_mode);
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public TelegramResponseSend sendAnswerInlineQuery(String inline_query_id,
            String titles[], String text[], String switch_pm_text) {
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "answerInlineQuery");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("inline_query_id", inline_query_id + "");
            connection.putParameter("switch_pm_text", switch_pm_text);
            JSONArray results
                    = TelegramInlineQueryResultArticle
                            .montaInlineQueryResultArticle("article", titles, text);
            connection.putParameter("results", results.toString());
            try {
                String postTelegramMessage = TelegramBotConnection.postTelegramMessage(connection);
                telegram = new TelegramResponseSend(postTelegramMessage, token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public TelegramResponseSend sendAnswerInlineQuery(String inline_query_id,
            String titles[], String text[]) {
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "answerInlineQuery");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("inline_query_id", inline_query_id + "");
            JSONArray results
                    = TelegramInlineQueryResultArticle
                            .montaInlineQueryResultArticle("article", titles, text);
            connection.putParameter("results", results.toString());
            try {
                String postTelegramMessage = TelegramBotConnection.postTelegramMessage(connection);
                telegram = new TelegramResponseSend(postTelegramMessage, token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public TelegramResponseSend sendButton(long chat_id_to_send, String text_to_send, String[] buttons,
            String switch_inline_query) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
        JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTeclado(buttons);
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("switch_inline_query", switch_inline_query);
            connection.putParameter("reply_markup", matriz.toString());
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public TelegramResponseSend sendReply(long chat_id_to_send, String text_to_send, int message_id) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(this.token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(this.token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send + "");
            connection.putParameter("reply_to_message_id", message_id + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToReply(error, connection.parameterMap());
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public boolean sendPhoto(long chat_id_to_send, File file, String caption) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        boolean resposta = false;
        if (validationToken(token)) {
            try {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendPhoto");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("caption", caption + "");
                connection.sendFile("photo", file);
                resposta = true;
            } catch (Exception error) {
                logger.errorToSend(error);
                resposta = false;
            }
        } else {
            logger.errorToken(token);
            resposta = false;
        }
        return resposta;
    }

    /*//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//
        A PARTIR DAQUI É STATIC TODOS OS METODOS ABAIXO SERÃO REFEITOS 
        PARA OS NON-STATIC
     *///=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//=//
//@todo: implementar "Encaminhar mensagens"
//https://core.telegram.org/bots/api#forwardmessage  
    public static TelegramResponseSend editMessage(String token,
            long chat_id_to_send, int message_id, String newText) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "editMessageText");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("message_id", message_id + "");
            connection.putParameter("text", newText + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToReply(error, connection.parameterMap());
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static TelegramResponseSend editMessage(String token,
            long chat_id_to_send, int message_id, String newText, String parse_mode) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "editMessageText");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("message_id", message_id + "");
            connection.putParameter("text", newText + "");
            connection.putParameter("parse_mode", parse_mode);
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToReply(error, connection.parameterMap());
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static TelegramResponseSend forwardMessage(String token, long chat_id_origin, long chat_id_to_send, int message_id) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "forwardMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("from_chat_id", chat_id_origin + "");
            connection.putParameter("reply_to_message_id", message_id + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToReply(error, connection.parameterMap());
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public static TelegramResponseSend sendButtonFly_switch_inline_current_chat(String token, long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] urls) throws JSONException {
        if (button_texts.length != urls.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_switch_inline_current_chat(button_texts, urls);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public static TelegramResponseSend editMessageButtonFly_callback(String token,
            long chat_id_to_send, int message_id,
            String[] button_texts, String[] callbacks) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (button_texts.length != callbacks.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
//        JSONObject layoutedButtons = getButtons(buttons);
            // JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_multilines(button_texts, callbacks);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_equilibrado(button_texts, callbacks);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "editMessageReplyMarkup");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("message_id", message_id + "");
                //connection.putParameter("text", newText);
                System.out.println("chat_id: " + chat_id_to_send);
                System.out.println("message_id: " + message_id);
                System.out.println("Matriz:\n" + matriz.toString());
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (Exception error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public static List<TelegramUpdate> getAllInstances(String token) {
        ArrayList<TelegramUpdate> a = new ArrayList<TelegramUpdate>();
        TelegramUpdate x = getInstance(token);
        if (x != null) {
            while (x.exist_messages) {
                a.add(x);
                x = getInstance(token);
            }
        } else {
            a = null;
        }
        return a;
    }

    public static TelegramResponseSticker sendStickerReply(String token, long chat_id, int message_id_to_reply, String sticker) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSticker telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendSticker");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id + "");
            connection.putParameter("reply_to_message_id", message_id_to_reply + "");
            connection.putParameter("sticker", sticker);
            try {
                telegram = new TelegramResponseSticker(TelegramBotConnection.postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }

            // LOG4J  - para logs
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static TelegramResponseSticker sendSticker(String token, long chat_id, String sticker) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSticker telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendSticker");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id + "");
            connection.putParameter("sticker", sticker);
            try {
                telegram = new TelegramResponseSticker(TelegramBotConnection.postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }

            // LOG4J  - para logs
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static String getUserInformations(TelegramUpdate x) {
        String resposta = "";
        resposta += "ID de Usuário: " + x.Message().from.id_user + "\n";
        if (x.Message().from.first_name != null) {
            resposta += "Nome: " + x.Message().from.first_name + "\n";
        }
        if (x.Message().from.last_name != null) {
            resposta += "Sobrenome: " + x.Message().from.last_name + "\n";
        }
        if (x.Message().from.username != null) {
            resposta += "Username: @" + x.Message().from.username + "\n";
        }
        //if (x.Message().getChat().getId_chat() != x.Message().getFrom().getId_user()) {
        resposta += "Chat: " + x.Message().getChat().getId_chat() + "\n";
        // }
        return resposta;
    }

    public static String getUserInformationsHTML(TelegramUpdate x) {
        String resposta = "";
        resposta += "ID de Usuário: <code>" + x.Message().from.id_user + "</code>\n";
        if (x.Message().from.first_name != null) {
            resposta += "Nome: <code>" + x.Message().from.first_name + "</code>\n";
        }
        if (x.Message().from.last_name != null) {
            resposta += "Sobrenome: <code>" + x.Message().from.last_name + "</code>\n";
        }
        if (x.Message().from.username != null) {
            resposta += "Username: @" + x.Message().from.username + "\n";
        }
        if (x.Message().getChat().getId_chat() != x.Message().getFrom().getId_user()) {
            resposta += "Chat: " + x.Message().getChat().getId_chat() + "\n";
        }
        return resposta;
    }

    public static TelegramResponseDelete deleteMessage(String token, long chat_id_to_delete, int message_id) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseDelete telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "deleteMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_delete + "");
            connection.putParameter("message_id", message_id + "");
            try {
                telegram = new TelegramResponseDelete(TelegramBotConnection.postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public static String getPathFile(String token, String file_id) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseGetFile telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "getFile");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("file_id", file_id + "");
            try {
                telegram = new TelegramResponseGetFile(TelegramBotConnection.postTelegramMessage(connection));
                return telegram.File_path();
            } catch (JSONException error) {
                logger.errorToSend(error);
                return null;
            }
        } else {
            logger.errorToken(token);
            return null;
        }
    }

    public static boolean downloadFile(TelegramUpdate instance, String local_download) {
        if (!local_download.split("")[local_download.length() - 1].equals("\\")) {
            System.out.println("Caminho \"" + local_download + "\" não é pasta");
            return false;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            if (validationToken(instance.Token())) {
                local_download = local_download + instance.Message().document.file_name;
                String pathFile = getPathFile(instance.Token(), instance.Message().document.file_id);
                String url = TelegramBotConnection.urlApiFiles(instance.Token(), pathFile);
                try {
                    new ZHttp().requestGet(url).send().saveAs(new File(local_download));
                    return true;
                } catch (JSONException error) {
                    logger.errorOnDownload(error);
                    return false;
                }
            } else {
                logger.errorToken(instance.Token());
                return false;
            }
        }
    }

    private static boolean validarCaminhoArquivo(String caminho) {
        String[] split = caminho.split("");
        return split[split.length - 1].equals("\\");
    }

    public static boolean downloadFile(String token, String local_download,
            String file_name, String id_file) {
        if (validarCaminhoArquivo(local_download)) {
            System.out.println("Caminho \"" + local_download + "\" não é válido");
            return false;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            if (validationToken(token)) {
                String pathFile = getPathFile(token, id_file);
                String url = TelegramBotConnection.urlApiFiles(token, pathFile);
                try {
                    new File(local_download).mkdirs();
                    new ZHttp().requestGet(url).send().saveAs(new File(local_download + "\\" + file_name));
                    return true;
                } catch (JSONException error) {
                    logger.errorOnDownload(error);
                    return false;
                }
            } else {
                logger.errorToken(token);
                return false;
            }
        }
    }

    public static TelegramResponseSend sendButton(String token, long chat_id_to_send, String text_to_send, String[] buttons) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
        JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTeclado(buttons);
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("reply_markup", matriz.toString());
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public static TelegramResponseSend sendButtonFly_url(String token, long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] urls) throws JSONException {
        if (button_texts.length != urls.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_url(button_texts, urls);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public static TelegramResponseSend sendButtonFly_callback(String token, long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] callbacks, boolean notification, boolean asList) throws JSONException {
        if (button_texts.length != callbacks.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            // JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_multilines(button_texts, callbacks);
            JSONObject matriz = null;
            if (asList) {
                matriz = TelegramButtonsMatrizToSend.montaListTecladoVoador_callback_equilibrado(button_texts, callbacks);
            } else {
                matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_equilibrado(button_texts, callbacks);
            }
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                connection.putParameter("disable_notification", !notification + "");
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public static TelegramResponseSend sendButtonFly_callback(String token, long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] callbacks, boolean notification) throws JSONException {
        if (button_texts.length != callbacks.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_equilibrado(button_texts, callbacks);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                connection.putParameter("disable_notification", !notification + "");
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public static TelegramResponseSend sendButtonFly_callbackMarkDown(
            String token, long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] callbacks, String parse_mode,
            boolean notification) throws JSONException {
        if (button_texts.length != callbacks.length) {
            logger.errorButtonFlyLayout("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback_equilibrado(button_texts, callbacks);
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("parse_mode", parse_mode);
                connection.putParameter("reply_markup", matriz.toString());
                connection.putParameter("disable_notification", !notification + "");
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        }
    }

    public static TelegramResponseSend hideButtonWithMessage(String token, long chat_id_to_send, String text_to_send) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        JSONObject objeto = new JSONObject();
        objeto.put("hide_keyboard", true);
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("reply_markup", objeto.toString());
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
            } catch (JSONException error) {
                logger.errorToSend(error);
            }
        } else {
            logger.errorToken(token);
            telegram = null;
        }
        return telegram;
    }

    public static TelegramResponseSend hideButtonWithoutMessage(String token, long chat_id_to_send) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        JSONObject objeto = new JSONObject();
        objeto.put("hide_keyboard", true);
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", "-");
            connection.putParameter("reply_markup", objeto.toString());
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
            } catch (JSONException error) {
                logger.errorToSend(error);
            }
            TelegramResponseDelete deleteMessage = ApiNeoBot.deleteMessage(token, chat_id_to_send, telegram.Message_id());
        } else {
            logger.errorToken(token);
            telegram = null;
        }
        return telegram;
    }

    public static boolean ApiNeoBotOffset(int update_id, String token) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        if (validationToken(token)) {
            try {
                TelegramBotConnection.setOffset(update_id, token);
                return true;
            } catch (Exception trynot) {
                logger.errorOffset(trynot);
                return false;
            }
        } else {
            logger.errorToken(token);
            return false;
        }
    }

    /**
     *
     * Método usado como solução para aplicações estruturadas.<br>
     *
     * <i> Não irá mais receber atualizações.</i>
     *
     * @param token
     * @return TelegramUpdate (objeto de Mensagem)
     * @throws JSONException
     */
    public static TelegramUpdate getInstance(String token) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramUpdate t_update = null;
        if (validationToken(token)) {
            try {
                String conteudojson = TelegramBotConnection.getTelegramjson(token);
                t_update = new TelegramUpdate(conteudojson, token);
                t_update.Token(token);
                TelegramBotConnection.setOffset(t_update.Update_id(), token);
                return t_update;
            } catch (Exception trynot) {
                logger.error("Erro ao buscar mensagens:" + trynot);
                t_update.text("I don't have messages");
                return t_update;
            }
        } else {
            logger.errorToken(token);
            t_update = null;
            return t_update;
        }
    }

    /**
     * Este metodo deve ser usado com cuidado! public static
     * ArrayList<TelegramUpdate> getAllInstance(String token) throws
     * JSONException { ZLogFileWriter.setDefaultLogFileWriter(new
     * ZLogFileWriter("Log")); ArrayList<TelegramUpdate> t_update; t_update =
     * new ArrayList<TelegramUpdate>(); if (validationToken(token)) { try {
     * boolean messages = true; while (messages) { try { TelegramUpdate instance
     * = getInstance(token); if (instance.exist_messages != false) {
     * t_update.add(instance); } else { messages = false; } } catch (Exception
     * notMessages) { messages = false; } } } catch (Exception trynot) {
     * t_update = null; } } else { logger.errorToken(token); t_update = null;
     * return t_update; } return t_update; }
     *
     */
    public static TelegramUpdate getInstanceNoOffset(String token) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramUpdate t_update = null;
        if (validationToken(token)) {
            try {
                String conteudojson = TelegramBotConnection.getTelegramjson(token);
                t_update = new TelegramUpdate(conteudojson, token);
                t_update.Token(token);
                return t_update;
            } catch (Exception trynot) {
                logger.error("Erro ao buscar mensagens:" + trynot);
                t_update.text("I don't have messages");
                return t_update;
            }
        } else {
            logger.errorToken(token);
            t_update = null;
            return t_update;
        }
    }

    public static boolean validationToken(String token) {
        boolean resposta = false;
        if (token.length() > 40 && !token.contains(" ")) {
            resposta = true;
        }
        return resposta;
    }

    public static TelegramResponseSend sendReply(String token, long chat_id_to_send, String text_to_send, int message_id) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send + "");
            connection.putParameter("reply_to_message_id", message_id + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToReply(error, connection.parameterMap());
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public static TelegramResponseSendGif sendGifReply(String token, long chat_id_to_send, int message_id_to_reply, String gif_id) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSendGif telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendAnimation");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("reply_to_message_id", message_id_to_reply + "");
            connection.putParameter("animation", gif_id);
            try {
                String postTelegramMessage = TelegramBotConnection.postTelegramMessage(connection);
                telegram = new TelegramResponseSendGif(postTelegramMessage, token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static TelegramResponseSendGif sendGif(String token, long chat_id_to_send, String gif_id) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSendGif telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendAnimation");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("animation", gif_id);
            try {
                String postTelegramMessage = TelegramBotConnection.postTelegramMessage(connection);
                telegram = new TelegramResponseSendGif(postTelegramMessage, token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static TelegramResponseSend sendAnswerCallbackQuery(String token,
            String callback_query_id, String text, boolean show_alert) {
        boolean retorno = false;
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "answerCallbackQuery");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("callback_query_id", callback_query_id + "");
            connection.putParameter("text", text);
            connection.putParameter("show_alert", show_alert + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static TelegramResponseSend sendAnswerInlineQuery(String token, String inline_query_id,
            String titles[], String text[]) throws Exception {
        TelegramResponseSend telegram = null;
        try {
            if (validationToken(token)) {
                validationTitlesAnswerInline(titles, text);
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "answerInlineQuery");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("inline_query_id", inline_query_id + "");
                JSONArray results
                        = TelegramInlineQueryResultArticle
                                .montaInlineQueryResultArticle("article", titles, text);
                connection.putParameter("results", results.toString());
                try {
                    String json = TelegramBotConnection.postTelegramMessage(connection);
                    telegram = new TelegramResponseSend(json, token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                } catch (Exception error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        } catch (Exception d) {
            throw new Exception("Validação de campos", d);
        }
    }

    public static TelegramResponseSend sendAnswerInlineQuery(String token, String inline_query_id,
            String title, String text) throws Exception {
        boolean retorno = false;
        TelegramResponseSend telegram = null;
        try {
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "answerInlineQuery");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("inline_query_id", inline_query_id + "");
                JSONArray results
                        = TelegramInlineQueryResultArticle
                                .montaInlineQueryResultArticle("article", title, text);
                connection.putParameter("results", results.toString());
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        } catch (Exception e) {
            throw new Exception("Deu ruim" + e.getMessage());
        }
    }

    public static TelegramResponseSend sendGif(String token, long chat_id_to_send, String gif_id, String caption) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendDocument");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("document", gif_id);
            connection.putParameter("caption", caption);
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static boolean sendGifReply(String token, long chat_id_to_send, String gif_id, String caption, int message_to_reply) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        boolean resposta = false;
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendDocument");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("document", gif_id);
            connection.putParameter("caption", caption);
            connection.putParameter("reply_to_message_id", message_to_reply + "");
            try {
//                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
//                Date x = new Date();
//                System.out.println(dt.format(x));
//                System.out.println(connection.toString());
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                resposta = true;
            } catch (JSONException error) {
                logger.errorToSend(error);
                resposta = false;
            }
        } else {
            logger.errorToken(token);
        }
        return resposta;
    }

    public static boolean sendPhoto(String token, long chat_id_to_send, File file, String caption) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        boolean resposta = false;
        if (validationToken(token)) {
            try {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendPhoto");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("caption", caption + "");
                connection.sendFile("photo", file);
                resposta = true;
            } catch (Exception error) {
                logger.errorToSend(error);
                resposta = false;
            }
        } else {
            logger.errorToken(token);
            resposta = false;
        }
        return resposta;
    }

    public static boolean sendPhoto(String token, long chat_id_to_send, File file) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        boolean resposta = false;
        if (validationToken(token)) {
            try {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendPhoto");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.sendFile("photo", file);
                resposta = true;
            } catch (Exception error) {
                logger.errorToSend(error);
                resposta = false;
            }
        } else {
            logger.errorToken(token);
            resposta = false;
        }
        return resposta;
    }

    public static TelegramResponseSend sendFile(String token, long chat_id_to_send, String pathFile) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        //boolean resposta = false;
        TelegramResponseSend resposta = new TelegramResponseSend(null, token);
        if (validationToken(token)) {
            try {
                final File arq = new File(pathFile);
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendDocument");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                ZHttpResponse response = connection.sendFile("document", arq);
                resposta = new TelegramResponseSend(response.getResponseText(), token);
                return resposta;
            } catch (Exception error) {
                logger.errorToSend(error);
                //resposta = false;
            }
        } else {
            logger.errorToken(token);
            //resposta = false;
        }
        return resposta;
    }

    public static boolean sendFile(String token, long chat_id_to_send, String name_file, String pathFile) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        boolean resposta = false;
        if (validationToken(token)) {
            try {
                final File arq = new File(pathFile);
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendDocument");
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.sendFile("document", name_file, (long) pathFile.getBytes().length, new ByteArrayInputStream(pathFile.getBytes()));
                resposta = true;
            } catch (Exception error) {
                logger.errorToSend(error);
                resposta = false;
            }
        } else {
            logger.errorToken(token);
            resposta = false;
        }
        return resposta;
    }

    public static TelegramResponseSend sendMarkdownReply(String token, long chat_id_to_send, String text_to_send, String parse_mode, int message_id) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("parse_mode", parse_mode);
            connection.putParameter("reply_to_message_id", message_id + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public static TelegramResponseSend sendMarkdown(String token, long chat_id_to_send, String text_to_send, String parse_mode) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("parse_mode", parse_mode);
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public static TelegramResponseSend send(String token, long chat_id_to_send, String text_to_send, boolean notification) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        notification = !notification;
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("disable_notification", notification + "");
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }

    }

    public static TelegramResponseSend send(String token, long chat_id_to_send, String text_to_send) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (text_to_send.length() < limitSizeText) {
            if (validationToken(token)) {
                ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
                connection.setAutoDownloadCertificates(true);
                connection.setAutoDownloadCertificates(true);
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                try {
                    telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                    return telegram;
                } catch (JSONException error) {
                    logger.errorToSend(error);
                    return telegram;
                }
            } else {
                logger.errorToken(token);
                return telegram = null;
            }
        } else {
            logger.errorToken("Utrapassa Limite de caracteres(" + text_to_send.length() + "/" + limitSizeText + ")");
            return telegram = null;
        }

    }

    public static TelegramResponseSend send(String token, BigInteger chat_id_to_send, String text_to_send) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendMessage");
            connection.setAutoDownloadCertificates(true);
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

    public static TelegramResponseSend sendLocation(String token, long chat_id_to_send, String latitude, String longitude) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = TelegramBotConnection.connectApi(token, "sendLocation");
            connection.setAutoDownloadCertificates(true);
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("latitude", String.valueOf(latitude));
            connection.putParameter("longitude", String.valueOf(longitude));
            try {
                telegram = new TelegramResponseSend(TelegramBotConnection.postTelegramMessage(connection), token);
                return telegram;
            } catch (JSONException error) {
                logger.errorToSend(error);
                return telegram;
            }
        } else {
            logger.errorToken(token);
            return telegram = null;
        }
    }

}
