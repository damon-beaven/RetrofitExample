package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class Thermostat {

    private String id;
    private String nest_id;
    private String name;
    private String name_long;
    private String nest_structure_id;
    private Object linked_device_id;

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
     * The nest_id
     */
    public String getNest_id() {
        return nest_id;
    }

    /**
     *
     * @param nest_id
     * The nest_id
     */
    public void setNest_id(String nest_id) {
        this.nest_id = nest_id;
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
     * The name_long
     */
    public String getName_long() {
        return name_long;
    }

    /**
     *
     * @param name_long
     * The name_long
     */
    public void setName_long(String name_long) {
        this.name_long = name_long;
    }

    /**
     *
     * @return
     * The nest_structure_id
     */
    public String getNest_structure_id() {
        return nest_structure_id;
    }

    /**
     *
     * @param nest_structure_id
     * The nest_structure_id
     */
    public void setNest_structure_id(String nest_structure_id) {
        this.nest_structure_id = nest_structure_id;
    }

    /**
     *
     * @return
     * The linked_device_id
     */
    public Object getLinked_device_id() {
        return linked_device_id;
    }

    /**
     *
     * @param linked_device_id
     * The linked_device_id
     */
    public void setLinked_device_id(Object linked_device_id) {
        this.linked_device_id = linked_device_id;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        result.append(" id: " + id + NEWLINE);
        result.append(" nest_id: " + nest_id + NEWLINE);
        result.append(" name: " + name + NEWLINE);
        result.append(" name_long: " + name_long + NEWLINE);
        result.append(" nest_structure_id: " + nest_structure_id + NEWLINE);
        result.append(" linked_device_id: " + linked_device_id + NEWLINE);
        result.append("}");

        return result.toString();
    }
}