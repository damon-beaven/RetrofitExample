package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class NestDeprecated {

    private List<NestUserDeprecated> users = new ArrayList<NestUserDeprecated>();
    private List<ThermostatDeprecated> thermostatDeprecateds = new ArrayList<ThermostatDeprecated>();

    /**
     *
     * @return
     * The users
     */
    public List<NestUserDeprecated> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     * The users
     */
    public void setUsers(List<NestUserDeprecated> users) {
        this.users = users;
    }

    /**
     *
     * @return
     * The thermostatDeprecateds
     */
    public List<ThermostatDeprecated> getThermostatDeprecateds() {
        return thermostatDeprecateds;
    }

    /**
     *
     * @param thermostatDeprecateds
     * The thermostatDeprecateds
     */
    public void setThermostatDeprecateds(List<ThermostatDeprecated> thermostatDeprecateds) {
        this.thermostatDeprecateds = thermostatDeprecateds;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (users != null) result.append(" users: " + users.toString() + NEWLINE);
        if (thermostatDeprecateds != null) result.append(" thermostatDeprecateds: " + thermostatDeprecateds.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }

}
