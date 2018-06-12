
import api.kuris.telegrambot.neo.ApiNeoBot;
import api.kuris.telegrambot.neo.TelegramResponseSend;
import api.kuris.telegrambot.neo.TelegramUpdate;
import br.zul.zwork2.log.ZLogFileWriter;
import br.zul.zwork2.log.ZVoidLogFileWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cristiano.rosa
 */
public class Newaii {

    public static String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";

    public static void main(String[] args) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZVoidLogFileWriter());
        ZLogFileWriter.getDefaultLogFileWriter().setInfoLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setWarningLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setErrorLogFile(null);
        System.out.println("Iniciando vida");
        TelegramUpdate x;
        String[] split = "critica;pedidos;faltantes;boni;Trocas;desc".split(";");
        long masterid = 52022517l;
        ApiNeoBot.sendButtonFly_callback(token, masterid, "A. Botoes", split, split);

        while (true) {
            x = null;
            try {
                x = ApiNeoBot.getInstance(token);
                if (x.callback_query.id_callback_query != null) {
                    ApiNeoBot.sendAnswerCallbackQuery(token,
                            x.callback_query.id_callback_query,
                            x.callback_query.data, true);
                    ApiNeoBot.deleteMessage(token,
                            x.callback_query.messageOfBot.chat.id_chat,
                            x.callback_query.messageOfBot.message_id);
                    ApiNeoBot.send(token,
                            x.callback_query.from.id_user,
                             x.callback_query.data);
                }
            } catch (Exception try1) {
                System.out.println("");
                //sem mensagem
            }
        }
    }

}
