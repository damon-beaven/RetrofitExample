package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/29/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class BASDevices {

    private Boolean success;
    // we'll need to expand on this when we get a real object from the server
    private List<Object> devices = new ArrayList<Object>();
    private Integer total;
    private Integer per_page;
    private Integer current_page;
    private Integer count;

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
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The per_page
     */
    public Integer getPer_page() {
        return per_page;
    }

    /**
     *
     * @param per_page
     * The per_page
     */
    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    /**
     *
     * @return
     * The current_page
     */
    public Integer getCurrent_page() {
        return current_page;
    }

    /**
     *
     * @param current_page
     * The current_page
     */
    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    /**
     *
     * @return
     * The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        result.append(" success: " + success + NEWLINE);
        if (devices != null) result.append(" devices: " + devices + NEWLINE);
        result.append(" total: " + total + NEWLINE);
        result.append(" per_page: " + per_page + NEWLINE);
        result.append(" current_page: " + current_page + NEWLINE);
        result.append(" count: " + count + NEWLINE);
        result.append("}");

        return result.toString();
    }
}