/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramUpdateLocation {

    public double latitude_d;            // latitude double
    public double longitude_d;            // longitude double
    public String latitude_S;      // latitude String
    public String longitude_S;       // longitude String
    public String full_location;        // latitude e longitude

    public TelegramUpdateLocation(JSONObject message) throws JSONException {
//        try {
        JSONObject location = message.getJSONObject("location");
        this.latitude_d = location.getDouble("latitude");
        this.longitude_d = location.getDouble("longitude");
        this.latitude_S = String.valueOf(latitude_d);
        this.longitude_S = String.valueOf(longitude_d);
        this.full_location = latitude_S + " , " + longitude_S;
//        } catch (Exception ex) {
//            this.latitude_d = 0;
//            this.longitude_d = 0;
//            this.latitude_S = null;
//            this.longitude_S = null;
//            this.full_location = null;
//        }
    }
}
