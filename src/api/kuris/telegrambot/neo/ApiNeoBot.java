/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    
    Mais informações sobre a API do Telegram:
    https://core.telegram.org/bots/api



 */
package api.kuris.telegrambot.neo;

import static api.kuris.telegrambot.neo.TelegramBotConnection.connectApi;
import static api.kuris.telegrambot.neo.TelegramBotConnection.getTelegramjson;
import static api.kuris.telegrambot.neo.TelegramBotConnection.postTelegramMessage;
import static bot.brains.alice.connectAlice.toAi;
import br.zul.zwork2.http.ZHttpPost;
import br.zul.zwork2.log.ZLogFileWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import static neotelegrambot.Translater.botSay;
import static neotelegrambot.Translater.userSay;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author STI
 */
public class ApiNeoBot {

    public static void main1(String[] args) throws JSONException, UnsupportedEncodingException, IOException {
        String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
        int id_master = 52022517;
        TelegramResponseSend send = send(token, id_master, "oi");
        System.out.println(send.error_description);
    }

    public static void main2(String[] args) throws JSONException, UnsupportedEncodingException, IOException {
        String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
        int id_master = 52022517;

        while (true) {
            try {
                TelegramUpdate x = getInstance(token);
                String text = userSay(x.message.text).split("&")[0];
                String resposta = toAi(text);
                ApiNeoBot.sendReply(token, id_master, botSay(resposta), x.message.message_id);
                System.out.println(x.message.text + " --> " + text);
            } catch (Exception ex) {

            }

        }
    }

    public static TelegramResponseSticker sendSticker(String token, long chat_id, String sticker) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSticker telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendSticker");
            connection.putParameter("chat_id", chat_id + "");
            connection.putParameter("sticker", sticker);
            try {
                telegram = new TelegramResponseSticker(postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                return telegram;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            return telegram = null;
        }
    }

    public static String getUserInformations(TelegramUpdate x) {
        String resposta = "";
        resposta += "ID de Usuário: " + x.message.from.id_user + "\n";
        if (x.message.from.first_name != null) {
            resposta += "Nome: " + x.message.from.first_name + "\n";
        }
        if (x.message.from.last_name != null) {
            resposta += "Sobrenome: " + x.message.from.last_name + "\n";
        }
        if (x.message.from.username != null) {
            resposta += "Username: @" + x.message.from.username + "\n";
        }
        return resposta;
    }

    public static TelegramResponseDelete deleteMessage(String token, long chat_id_to_delete, int message_id) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseDelete telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "deleteMessage");
            connection.putParameter("chat_id", chat_id_to_delete + "");
            connection.putParameter("message_id", message_id + "");
            try {
                telegram = new TelegramResponseDelete(postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                return telegram;
            }
        } else {
            System.out.println("WTF is that token?");
            return telegram = null;
        }

    }

    public static TelegramResponseSend sendButton(String token, long chat_id_to_send, String text_to_send, String[] buttons) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
        JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTeclado(buttons);
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendMessage");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("reply_markup", matriz.toString());
            try {
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                return telegram;
            }
        } else {
            System.out.println("WTF is that token?");
            return telegram = null;
        }

    }

    public static void main(String[] args) {
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

    public static TelegramResponseSend sendButtonFly_url(String token, long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] urls) throws JSONException {
        if (button_texts.length != urls.length) {
            System.out.println("qt button texts <> qt urls");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_url(button_texts, urls);
            if (validationToken(token)) {
                ZHttpPost connection = connectApi(token, "sendMessage");
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(postTelegramMessage(connection));
                    return telegram;
                } catch (JSONException error) {
                    SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                    Date x = new Date();
                    System.out.println(dt.format(x));
                    System.out.println("Erro ao enviar");
                    System.out.println(error);
                    return telegram;
                }
            } else {
                System.out.println("WTF is that token?");
                return telegram = null;
            }
        }
    }

    public static TelegramResponseSend sendButtonFly_callback(String token, long chat_id_to_send, String text_to_send,
            String[] button_texts, String[] callbacks) throws JSONException {
        if (button_texts.length != callbacks.length) {
            System.out.println("qt button texts <> qt callbacks");
            return null;
        } else {
            ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
            TelegramResponseSend telegram = null;
//        JSONObject layoutedButtons = getButtons(buttons);
            JSONObject matriz = TelegramButtonsMatrizToSend.montaMatrizTecladoVoador_callback(button_texts, callbacks);
            if (validationToken(token)) {
                ZHttpPost connection = connectApi(token, "sendMessage");
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.putParameter("text", text_to_send);
                connection.putParameter("reply_markup", matriz.toString());
                try {
                    telegram = new TelegramResponseSend(postTelegramMessage(connection));
                    return telegram;
                } catch (JSONException error) {
                    SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                    Date x = new Date();
                    System.out.println(dt.format(x));
                    System.out.println("Erro ao enviar");
                    System.out.println(error);
                    return telegram;
                }
            } else {
                System.out.println("WTF is that token?");
                return telegram = null;
            }
        }
    }

    public static TelegramResponseSend hideButton(String token, long chat_id_to_send, String text_to_send) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        JSONObject objeto = new JSONObject();
        objeto.put("hide_keyboard", true);
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendMessage");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("reply_markup", objeto.toString());
            try {
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);

            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            telegram = null;
        }
        TelegramResponseDelete deleteMessage = ApiNeoBot.deleteMessage(token, chat_id_to_send, telegram.message_id);
        return telegram;
    }

    public static boolean ApiNeoBotOffset(int update_id, String token) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        if (validationToken(token)) {
            try {
                TelegramBotConnection.setOffset(update_id, token);
                return true;
            } catch (Exception trynot) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Error in >>OFFSET<< command");
                return false;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("Error in >>TOKEN<<");
            return false;
        }
    }

    public static TelegramUpdate getInstance(String token) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramUpdate t_update = null;
        if (validationToken(token)) {
            try {
                String conteudojson = getTelegramjson(token);
                t_update = new TelegramUpdate(conteudojson);
                t_update.token = token;
                TelegramBotConnection.setOffset(t_update.update_id, token);
                return t_update;
            } catch (Exception trynot) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("I don't have messages");
                t_update.message.text = "I don't have messages";
                return t_update;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            t_update = null;
            return t_update;
        }
    }

    public static TelegramUpdate ApiNeoBotNoOffset(String token) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramUpdate t_update = null;
        if (validationToken(token)) {
            try {
                String conteudojson = getTelegramjson(token);
                t_update = new TelegramUpdate(conteudojson);
                return t_update;
            } catch (Exception trynot) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("I don't have messages");
                t_update.message.text = "I don't have messages";
                return t_update;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
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
            ZHttpPost connection = connectApi(token, "sendMessage");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send + "");
            connection.putParameter("reply_to_message_id", message_id + "");
            try {
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar:\n{");
                System.out.println(connection.parameterMap() + "\n");
                System.out.println(error);
                System.out.println("\n}");
                return telegram;
            }
        } else {
            System.out.println("WTF is that token?");
            return telegram = null;
        }

    }

    public static TelegramResponseSend sendGif(String token, long chat_id_to_send, String gif_id) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendDocument");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("document", gif_id);
            try {
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                return telegram;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            return telegram = null;
        }
    }

    public static TelegramResponseSend sendGif(String token, long chat_id_to_send, String gif_id, String caption) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendDocument");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("document", gif_id);
            connection.putParameter("caption", caption);
            try {
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                return telegram;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            return telegram = null;
        }
    }

    public static boolean sendGifReply(String token, long chat_id_to_send, String gif_id, String caption, int message_to_reply) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        boolean resposta = false;
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendDocument");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("document", gif_id);
            connection.putParameter("caption", caption);
            connection.putParameter("reply_to_message_id", message_to_reply + "");
            try {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println(connection.toString());
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
                resposta = true;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                resposta = false;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
        }
        return resposta;
    }

    public static boolean sendFile(String token, long chat_id_to_send, String name_file, String pathFile) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        boolean resposta = false;
        if (validationToken(token)) {
            try {
                final File arq = new File(pathFile);
                ZHttpPost connection = connectApi(token, "sendDocument");
                connection.putParameter("chat_id", chat_id_to_send + "");
                connection.sendFile("document", name_file, (long) pathFile.getBytes().length, new ByteArrayInputStream(pathFile.getBytes()));
                resposta = true;
            } catch (Exception error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                resposta = false;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            resposta = false;
        }
        return resposta;
    }

    public static TelegramResponseSend sendMarkdown(String token, long chat_id_to_send, String text_to_send, String parse_mode) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendMessage");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            connection.putParameter("parse_mode", parse_mode);
            try {
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                return telegram;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            return telegram = null;
        }

    }

    public static TelegramResponseSend send(String token, long chat_id_to_send, String text_to_send) throws JSONException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendMessage");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            try {
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
                System.out.println("");
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                return telegram;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            return telegram = null;
        }

    }

    public static TelegramResponseSend send(String token, BigInteger chat_id_to_send, String text_to_send) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        TelegramResponseSend telegram = null;
        if (validationToken(token)) {
            ZHttpPost connection = connectApi(token, "sendMessage");
            connection.putParameter("chat_id", chat_id_to_send + "");
            connection.putParameter("text", text_to_send);
            try {
                telegram = new TelegramResponseSend(postTelegramMessage(connection));
                return telegram;
            } catch (JSONException error) {
                SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                Date x = new Date();
                System.out.println(dt.format(x));
                System.out.println("Erro ao enviar");
                System.out.println(error);
                return telegram;
            }
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
            Date x = new Date();
            System.out.println(dt.format(x));
            System.out.println("WTF is that token?");
            return telegram = null;
        }
    }

}
