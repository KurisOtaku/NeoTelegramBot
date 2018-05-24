/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.kuris.telegrambot.neo;

/**
 *
 * @author cristiano.rosa
 */
public class TelegramErrorHandling {

    public static String errorDescription(String code_error) {
        String resposta = "Unknown error";
        if (code_error.equals("400")) {
            resposta = "BAD_REQUEST\nThe query contains errors. In the event that a request was created using a form and contains user generated data, the user should be notified that the data must be corrected before the query is repeated.";
        }
        if (code_error.equals("401")) {
            resposta = "UNAUTHORIZED\nThere was an unauthorized attempt to use functionality available only to authorized users.";
        }
        if (code_error.equals("403")) {
            resposta = "FORBIDDEN\nPrivacy violation. For example, an attempt to write a message to someone who has blacklisted the current user.";
        }
        if (code_error.equals("404")) {
            resposta = "NOT_FOUND\nAn attempt to invoke a non-existent object, such as a method.";
        }
        if (code_error.equals("420")) {
            resposta = "FLOOD\nThe maximum allowed number of attempts to invoke the given method with the given input parameters has been exceeded. For example, in an attempt to request a large number of text messages (SMS) for the same phone number.";
        }
        if (code_error.equals("500")) {
            resposta = "INTERNAL\nAn internal server error occurred while a request was being processed; for example, there was a disruption while accessing a database or file storage.\n"
                    + "If a client receives a 500 error, or you believe this error should not have occurred, please collect as much information as possible about the query and error and send it to the developers.";
        }
        return resposta;
    }

    public static String[] errorsExemples(String code_error) {
        String[] resposta = null;
        if (code_error.equals("400")) {
            resposta = ("    FIRSTNAME_INVALID: The first name is invalid\n"
                    + "    LASTNAME_INVALID: The last name is invalid\n"
                    + "    PHONE_NUMBER_INVALID: The phone number is invalid\n"
                    + "    PHONE_CODE_HASH_EMPTY: phone_code_hash is missing\n"
                    + "    PHONE_CODE_EMPTY: phone_code is missing\n"
                    + "    PHONE_CODE_EXPIRED: The confirmation code has expired\n"
                    + "    API_ID_INVALID: The api_id/api_hash combination is invalid\n"
                    + "    PHONE_NUMBER_OCCUPIED: The phone number is already in use\n"
                    + "    PHONE_NUMBER_UNOCCUPIED: The phone number is not yet being used\n"
                    + "    USERS_TOO_FEW: Not enough users (to create a chat, for example)\n"
                    + "    USERS_TOO_MUCH: The maximum number of users has been exceeded (to create a chat, for example)\n"
                    + "    TYPE_CONSTRUCTOR_INVALID: The type constructor is invalid\n"
                    + "    FILE_PART_INVALID: The file part number is invalid\n"
                    + "    FILE_PARTS_INVALID: The number of file parts is invalid\n"
                    + "    FILE_PART_Х_MISSING: Part X (where X is a number) of the file is missing from storage\n"
                    + "    MD5_CHECKSUM_INVALID: The MD5 checksums do not match\n"
                    + "    PHOTO_INVALID_DIMENSIONS: The photo dimensions are invalid\n"
                    + "    FIELD_NAME_INVALID: The field with the name FIELD_NAME is invalid\n"
                    + "    FIELD_NAME_EMPTY: The field with the name FIELD_NAME is missing\n"
                    + "    MSG_WAIT_FAILED: A waiting call returned an error")
                    .split("\n");
        }
        if (code_error.equals("401")) {
            resposta = ("    AUTH_KEY_UNREGISTERED: The key is not registered in the system\n"
                    + "    AUTH_KEY_INVALID: The key is invalid\n"
                    + "    USER_DEACTIVATED: The user has been deleted/deactivated\n"
                    + "    SESSION_REVOKED: The authorization has been invalidated, because of the user terminating all sessions\n"
                    + "    SESSION_EXPIRED: The authorization has expired\n"
                    + "    ACTIVE_USER_REQUIRED: The method is only available to already activated users\n"
                    + "    AUTH_KEY_PERM_EMPTY: The method is unavailble for temporary authorization key, not bound to permanent")
                    .split("\n");
        }
        if (code_error.equals("403")) {
            resposta = null;
        }
        if (code_error.equals("420")) {
            resposta[0] = ("FLOOD_WAIT_X: A wait of X seconds is required (where X is a number)");
        }
        if (code_error.equals("500")) {
            resposta = null;
        }
        return resposta;
    }
}
