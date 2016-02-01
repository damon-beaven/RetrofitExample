package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

        import java.util.ArrayList;
        import java.util.List;

public class BASThermostats {

    private Boolean success;
    private List<Thermostat_token> thermostat_token = new ArrayList<Thermostat_token>();
    private List<Thermostat> thermostats = new ArrayList<Thermostat>();

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
     * The thermostat_token
     */
    public List<Thermostat_token> getThermostat_token() {
        return thermostat_token;
    }

    /**
     *
     * @param thermostat_token
     * The thermostat_token
     */
    public void setThermostat_token(List<Thermostat_token> thermostat_token) {
        this.thermostat_token = thermostat_token;
    }

    /**
     *
     * @return
     * The thermostats
     */
    public List<Thermostat> getThermostats() {
        return thermostats;
    }

    /**
     *
     * @param thermostats
     * The thermostats
     */
    public void setThermostats(List<Thermostat> thermostats) {
        this.thermostats = thermostats;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (success != null) result.append(" success: " + success.toString() + NEWLINE);
        if (thermostat_token != null) result.append(" thermostat_token: " + thermostat_token.toString() + NEWLINE);
        if (thermostats != null) result.append(" thermostats: " + thermostats.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }
}