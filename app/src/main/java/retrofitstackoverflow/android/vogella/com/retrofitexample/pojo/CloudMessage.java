package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/28/2016.
 */
public class CloudMessage {
    public String success;
    public String message;
    public String status_code;
    public String code;
//    public String errors; //need to create object with all possible responses
//    "errors": {
//        "email": [
//        "The email field is required."
//        ],
//        "pin": [
//        "The pin field is required."
//        ],
//        "reset_token": [
//        "The reset token field is required."
//        ],
//        "password": [
//        "The password field is required."
//        ]
//    {"email":["The email format is invalid."]}

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (success != null) result.append(" success: " + success.toString() + NEWLINE);
        if (message != null) result.append(" message: " + message.toString() + NEWLINE);
        if (status_code != null) result.append(" status_code: " + status_code.toString() + NEWLINE);
        if (code != null) result.append(" code: " + code.toString() + NEWLINE);
//        if (errors != null) result.append(" errors: " + errors.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }

}
