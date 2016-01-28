package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/28/2016.
 */
public class CloudMessage {
    private String success;
    private String message;
    private String status_code;
    private String code;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (success != null) result.append(" success: " + success.toString() + NEWLINE);
        if (message != null) result.append(" message: " + message.toString() + NEWLINE);
        if (status_code != null) result.append(" status_code: " + status_code.toString() + NEWLINE);
        if (code != null) result.append(" code: " + code.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }

}
