/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramResponseSendGif {

    protected String token;
    protected String file_id;
    protected int file_size;
    protected int message_id;
    protected String error_description;
    protected int error_code;
    protected boolean ok;

    protected TelegramUpdateDocument document;
    protected TelegramUpdateChat chat;

    public TelegramResponseSendGif(String json, String token) {
        JSONObject jsonObj = new JSONObject(json);
        try {
            JSONObject result = jsonObj.getJSONObject("result");
            this.token = token;
            this.document = new TelegramUpdateDocument(result);
            this.file_id = document.getFile_id();
            this.file_size = document.getFile_size();
            this.chat = new TelegramUpdateChat(result);
            this.message_id = result.getInt("message_id");
            this.error_code = 0;
            this.error_description = "#null";
            this.ok = true;
        } catch (Exception pegaErro1) {
            JSONObject result = jsonObj.getJSONObject("result");
            this.token = token;
            this.document = null;
            this.file_id = null;
            this.file_size = 0;
            this.chat = null;
            this.message_id = 0;
            this.ok = false;
            this.error_code = new JSONObject(json).getInt("error_code");
            this.error_description = new JSONObject(json).getString("description");
        }
    }

    public String Token() {
        return token;
    }

    public void Token(String token) {
        this.token = token;
    }

}
