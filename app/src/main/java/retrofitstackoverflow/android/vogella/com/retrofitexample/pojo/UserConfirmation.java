package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

public class UserConfirmation {

    private String id;
    private Boolean confirmed;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The confirmed
     */
    public Boolean getConfirmed() {
        return confirmed;
    }

    /**
     *
     * @param confirmed
     * The confirmed
     */
    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        result.append(" id: " + id + NEWLINE);
        result.append(" confirmed: " + confirmed + NEWLINE);
        result.append("}");

        return result.toString();
    }
}