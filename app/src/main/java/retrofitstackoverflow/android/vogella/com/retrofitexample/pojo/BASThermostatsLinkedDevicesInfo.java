package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

import java.util.ArrayList;
import java.util.List;

public class BASThermostatsLinkedDevicesInfo {

    private Boolean success;
    private List<Object> linked_devices = new ArrayList<Object>(); //todo: need real object for this

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
     * The linked_devices
     */
    public List<Object> getLinked_devices() {
        return linked_devices;
    }

    /**
     *
     * @param linked_devices
     * The linked_devices
     */
    public void setLinked_devices(List<Object> linked_devices) {
        this.linked_devices = linked_devices;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (success != null) result.append(" success: " + success.toString() + NEWLINE);
        if (linked_devices != null) result.append(" linked_devices: " + linked_devices+ NEWLINE);
        result.append("}");

        return result.toString();
    }
}