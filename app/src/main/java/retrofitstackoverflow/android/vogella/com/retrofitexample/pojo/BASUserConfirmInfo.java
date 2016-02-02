package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

import com.google.gson.annotations.SerializedName;

public class BASUserConfirmInfo {

    private Boolean success;
    @SerializedName("user")
    private UserConfirmation userConfirmation;

    /**
     *
     * @return
     * The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The userConfirmation
     */
    public UserConfirmation getUserConfirmation() {
        return userConfirmation;
    }

    /**
     *
     * @param userConfirmation
     * The userConfirmation
     */
    public void setUserConfirmation(UserConfirmation userConfirmation) {
        this.userConfirmation = userConfirmation;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (success != null) result.append(" success: " + success.toString() + NEWLINE);
        if (userConfirmation != null) result.append(" userConfirmation: " + userConfirmation.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }
}