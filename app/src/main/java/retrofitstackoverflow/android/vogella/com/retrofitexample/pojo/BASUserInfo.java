package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class BASUserInfo {

    private Boolean success;
    private User user;
    private List<Object> devices = new ArrayList<Object>();
    private AppsDeprecated appsDeprecated;

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
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The devices
     */
    public List<Object> getDevices() {
        return devices;
    }

    /**
     *
     * @param devices
     * The devices
     */
    public void setDevices(List<Object> devices) {
        this.devices = devices;
    }

    /**
     *
     * @return
     * The appsDeprecated
     */
    public AppsDeprecated getAppsDeprecated() {
        return appsDeprecated;
    }

    /**
     *
     * @param appsDeprecated
     * The appsDeprecated
     */
    public void setAppsDeprecated(AppsDeprecated appsDeprecated) {
        this.appsDeprecated = appsDeprecated;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        result.append(" success: " + success + NEWLINE);
        if (user != null) result.append(" user: " + user.toString() + NEWLINE);
        if (devices != null) result.append(" devices: " + devices.toString() + NEWLINE);
        if (appsDeprecated != null) result.append(" appsDeprecated: " + appsDeprecated.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }
}