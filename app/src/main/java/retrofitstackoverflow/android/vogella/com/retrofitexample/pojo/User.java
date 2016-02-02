package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class User {

    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private Integer pin;
    private Boolean confirmed;
    private String updated_at_utc;
    private String created_at_utc;

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
     * The first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     *
     * @param first_name
     * The first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     *
     * @return
     * The last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     *
     * @param last_name
     * The last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The pin
     */
    public Integer getPin() {
        return pin;
    }

    /**
     *
     * @param pin
     * The pin
     */
    public void setPin(Integer pin) {
        this.pin = pin;
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

    /**
     *
     * @return
     * The updated_at_utc
     */
    public String getUpdated_at_utc() {
        return updated_at_utc;
    }

    /**
     *
     * @param updated_at_utc
     * The updated_at_utc
     */
    public void setUpdated_at_utc(String updated_at_utc) {
        this.updated_at_utc = updated_at_utc;
    }

    /**
     *
     * @return
     * The created_at_utc
     */
    public String getCreated_at_utc() {
        return created_at_utc;
    }

    /**
     *
     * @param created_at_utc
     * The created_at_utc
     */
    public void setCreated_at_utc(String created_at_utc) {
        this.created_at_utc = created_at_utc;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        result.append(" id: " + id + NEWLINE);
        result.append(" first_name: " + first_name + NEWLINE);
        result.append(" last_name: " + last_name + NEWLINE);
        result.append(" email: " + email + NEWLINE);
        if (pin != null) result.append(" pin: " + pin.toString() + NEWLINE);
        result.append(" confirmed: " + confirmed + NEWLINE);
        result.append(" updated_at_utc: " + updated_at_utc + NEWLINE);
        result.append(" created_at_utc: " + created_at_utc + NEWLINE);
        result.append("}");

        return result.toString();
    }
}