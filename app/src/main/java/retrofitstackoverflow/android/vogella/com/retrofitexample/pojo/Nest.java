package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class Nest {

    private List<NestUser> users = new ArrayList<NestUser>();
    private List<Thermostat> thermostats = new ArrayList<Thermostat>();

    /**
     *
     * @return
     * The users
     */
    public List<NestUser> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     * The users
     */
    public void setUsers(List<NestUser> users) {
        this.users = users;
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
        if (users != null) result.append(" users: " + users.toString() + NEWLINE);
        if (thermostats != null) result.append(" thermostats: " + thermostats.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }

}
