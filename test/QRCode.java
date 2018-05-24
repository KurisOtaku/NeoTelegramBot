
import br.zul.zwork2.http.ZHttp;
import br.zul.zwork2.io.ZTxtFile;
import br.zul.zwork2.log.ZLogFileWriter;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cristiano.rosa
 */
public class QRCode {

    public static void main(String[] args) {
        gerateAndGetQRCODE("Atualizações"
                + "/pedido 'SETOR' 'PDV'"
                + "Use para procurar o número do pedido do PDV");
    }

    public static String gerateAndGetQRCODE(String text) {
        String url = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";
        ZLogFileWriter.setDefaultLogFileWriter(new ZLogFileWriter("Log"));
        String site = "";
        String caminho = "C:\\Users\\jeferson.oliveira\\Desktop\\QRCodes\\";
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = dateFormat.format(new Date().getTime());
        try {
            new ZHttp()
                    .requestGet(url + text)
                    .send()
                    .saveAs(new File(fileName + ".PNG"));
        } catch (Exception erro) {
            System.out.println("Error in token?");
            System.out.println(erro);
        }
        return caminho + text + ".PNG";
    }
}
