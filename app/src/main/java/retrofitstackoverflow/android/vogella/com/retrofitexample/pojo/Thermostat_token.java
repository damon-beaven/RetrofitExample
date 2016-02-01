package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

public class Thermostat_token {

    private String id;
    private Thermostat_type thermostat_type;
    private String access_token;
    private String expires_at;

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
     * The thermostat_type
     */
    public Thermostat_type getThermostat_type() {
        return thermostat_type;
    }

    /**
     *
     * @param thermostat_type
     * The thermostat_type
     */
    public void setThermostat_type(Thermostat_type thermostat_type) {
        this.thermostat_type = thermostat_type;
    }

    /**
     *
     * @return
     * The access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     *
     * @param access_token
     * The access_token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     *
     * @return
     * The expires_at
     */
    public String getExpires_at() {
        return expires_at;
    }

    /**
     *
     * @param expires_at
     * The expires_at
     */
    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (id != null) result.append(" id: " + id.toString() + NEWLINE);
        if (thermostat_type != null) result.append(" thermostat_type: " + thermostat_type.toString() + NEWLINE);
        if (access_token != null) result.append(" access_token: " + access_token.toString() + NEWLINE);
        if (expires_at != null) result.append(" expires_at: " + expires_at.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }

}