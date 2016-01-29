package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/29/2016.
 */
public class Thermostat_type {

    private String id;
    private String name;
    private String description;
    private String site_url;
    private String created_at_utc;
    private String updated_at_utc;

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
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The site_url
     */
    public String getSite_url() {
        return site_url;
    }

    /**
     *
     * @param site_url
     * The site_url
     */
    public void setSite_url(String site_url) {
        this.site_url = site_url;
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        result.append(" id: " + id + NEWLINE);
        result.append(" name: " + name + NEWLINE);
        result.append(" description: " + description + NEWLINE);
        result.append(" site_url: " + site_url + NEWLINE);
        result.append(" created_at_utc: " + created_at_utc + NEWLINE);
        result.append(" updated_at_utc: " + updated_at_utc + NEWLINE);
        result.append("}");

        return result.toString();
    }
}