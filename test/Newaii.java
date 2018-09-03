
import api.kuris.telegrambot.neo.ApiNeoBot;
import api.kuris.telegrambot.neo.TelegramResponseSend;
import api.kuris.telegrambot.neo.TelegramUpdate;
import br.zul.zwork2.log.ZLogFileWriter;
import br.zul.zwork2.log.ZVoidLogFileWriter;
import br.zul.zwork2.thread.ZThread;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class Newaii {

    public static String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";

    public static void main(String[] args) throws IOException {
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

    public static void main1(String[] args) {
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
