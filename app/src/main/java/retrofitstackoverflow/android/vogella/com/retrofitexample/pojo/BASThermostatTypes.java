package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/29/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class BASThermostatTypes {

    private Boolean success;
    private List<Thermostat_type> thermostat_types = new ArrayList<Thermostat_type>();

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
     * The thermostat_types
     */
    public List<Thermostat_type> getThermostat_types() {
        return thermostat_types;
    }

    /**
     *
     * @param thermostat_types
     * The thermostat_types
     */
    public void setThermostat_types(List<Thermostat_type> thermostat_types) {
        this.thermostat_types = thermostat_types;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (success != null) result.append(" success: " + success.toString() + NEWLINE);
        if (thermostat_types != null) result.append(" thermostats: " + thermostat_types.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }

}
