
import api.kuris.telegrambot.neo.ApiNeoBot;
import static api.kuris.telegrambot.neo.ApiNeoBot.*;
import api.kuris.telegrambot.neo.*;
import br.zul.zwork2.log.ZLogFileWriter;
import br.zul.zwork2.log.ZVoidLogFileWriter;
import br.zul.zwork2.thread.ZThread;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Newaii {

    public static String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
    static long masterid = 52022517l;

    public static void main4(String[] args) {

        //COMO EDITAR BOTÕES FLUTUANTES COM CALLBACK?
        ZLogFileWriter.setDefaultLogFileWriter(new ZVoidLogFileWriter());
        ZLogFileWriter.getDefaultLogFileWriter().setInfoLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setWarningLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setErrorLogFile(null);
        String[] array = {"array"};
        String[] array2 = {masterid + "_array"};
        ApiNeoBot.sendButtonFly_callback(token, masterid, "Teste", array, array2);
        while (true) {
            ZThread.sleep(1000);
            TelegramUpdate x = null;
            try {
                x = ApiNeoBot.getInstance(token);
                if (x.isExist_messages()) {
                    if (x.Callback_query().Data().contains("_array")) {
                        ApiNeoBot.editMessage(token, masterid, x.messageId(), "Atualizado!");
                        System.out.println("");
                    }
                }
            } catch (Exception try1) {
                //sem mensagem
                try1.printStackTrace();
                System.out.println("erro");
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
        long masterid = 52022517l;
        try {
            ApiNeoBot a = new ApiNeoBot(token, masterid, -masterid);
            a.sendVerticalButtonFly_callback(
                    masterid,
                    "Teste",
                    "a;aserasrasrb;c;ddafasdfaesfa;e;f;aserfafaseawsg;h;i".split(";"),
                    "a;b;c;d;e;f;g;h;i".split(";"));
        } catch (Exception d) {
            d.printStackTrace();
        }

    }

    public static void main1(String[] args) {
        String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
        long masterid = 52022517l;
        TelegramResponseSendGif sendGif = sendGifReply(token, masterid, 3279, "CgADBAADj4sAAugaZAfrx4Ee7eHbvgI");
        //TelegramResponseSticker sendSticker = sendStickerReply(token, masterid, 3279, "CAADAgADFgMAAs7Y6As05HgrRUxSGAI");
        System.out.println("");
    }

    public static void main2(String[] args) throws IOException {
        ZLogFileWriter.setDefaultLogFileWriter(new ZVoidLogFileWriter());
        ZLogFileWriter.getDefaultLogFileWriter().setInfoLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setWarningLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setErrorLogFile(null);
        TelegramUpdate x;
        long masterid = 52022517l;
        ApiNeoBot.send(token, masterid, "Estou pronta para os testes");
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = dateFormat.format(now);
        while (true) {
            //ZThread.sleep(1000);
            x = null;
            new Thread(new Runnable() {
                Date now = new Date();
                String date = dateFormat.format(now);

                @Override
                public void run() {
                    now = new Date();
                    date = dateFormat.format(now);
                    //   ApiNeoBot.send(token, masterid, date);
                }
            }).start();
            try {

            } catch (Exception try1) {
                //sem mensagem
            }
        }
    }

    public static void main3(String[] args) {
        ZLogFileWriter.setDefaultLogFileWriter(new ZVoidLogFileWriter());
        ZLogFileWriter.getDefaultLogFileWriter().setInfoLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setWarningLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setErrorLogFile(null);
        TelegramUpdate x;
        long masterid = 52022517l;
        ApiNeoBot.send(token, masterid, "Estou pronta para os testes");
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = dateFormat.format(now);
        TelegramResponseSend datemessage = ApiNeoBot.send(token, masterid, date);
        while (true) {
            ZThread.sleep(1000);
            x = null;
            try {
                now = new Date();
                date = dateFormat.format(now);
                ApiNeoBot.editMessage(token, masterid, datemessage.Message_id(), date);
            } catch (Exception try1) {
                //sem mensagem
            }
        }
    }
}
