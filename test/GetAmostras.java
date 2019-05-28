
//import static Newaii.masterid;
import api.kuris.telegrambot.neo.ApiNeoBot;
import api.kuris.telegrambot.neo.TelegramUpdate;
import br.zul.zwork2.log.ZLogFileWriter;
import br.zul.zwork2.log.ZVoidLogFileWriter;
import br.zul.zwork2.thread.ZThread;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cristiano.rosa
 */
public class GetAmostras {

    public static String token = "304076906:AAFjEZWRm2CkOVDuEvIfOnfz0LlNRY87P4A";
    static long masterid = 52022517l;
    
    public static void main(String[] args) throws Throwable {
         //COMO EDITAR BOTÕES FLUTUANTES COM CALLBACK?
        ZLogFileWriter.setDefaultLogFileWriter(new ZVoidLogFileWriter());
        ZLogFileWriter.getDefaultLogFileWriter().setInfoLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setWarningLogFile(null);
        ZLogFileWriter.getDefaultLogFileWriter().setErrorLogFile(null);
        ApiNeoBot apiNeoBot = new ApiNeoBot(token, masterid, -masterid);
        while (true) {
            ZThread.sleep(1000);
            TelegramUpdate x = null;
            try {
                x = apiNeoBot.getInstance();
                if (x.isExist_messages()) {
                    
                }
            } catch (Exception try1) {
                //sem mensagem
                try1.printStackTrace();
                System.out.println("erro");
            }
        }
    }
}
