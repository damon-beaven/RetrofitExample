package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

public class Thermostat {

    private String id;
    private String thermostat_token_id; //unique id for YOUR thermostat
    private String thermostat_type_id;  //unique id for the TYPE of thermostat
    private String thermostat_xid;      //the serial number of the thermostat
    private String name;
    private String locale;
    private String temperature_scale;
    private String hvac_mode;
    private Float target_temperature_high_c;
    private Float target_temperature_high_f;
    private Float target_temperature_low_c;
    private Float target_temperature_low_f;
    private Float ambient_temperature_c;
    private Float ambient_temperature_f;
    private String humidity;
    private Boolean is_online;
    private String updated_at_utc; //not in getUserThermostats
    private Object linked_device_id;
    private String structure_name;
    // from create thermostat response
    private Boolean away;   //getUserThermostats returns also now
    // showed up...is this new?
    private Boolean has_leaf; //getUserThermostats

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
     * The thermostat_token_id
     */
    public String getThermostat_token_id() {
        return thermostat_token_id;
    }

    /**
     *
     * @param thermostat_token_id
     * The thermostat_token_id
     */
    public void setThermostat_token_id(String thermostat_token_id) {
        this.thermostat_token_id = thermostat_token_id;
    }

    /**
     *
     * @return
     * The thermostat_type_id
     */
    public String getThermostat_type_id() {
        return thermostat_type_id;
    }

    /**
     *
     * @param thermostat_type_id
     * The thermostat_type_id
     */
    public void setThermostat_type_id(String thermostat_type_id) {
        this.thermostat_type_id = thermostat_type_id;
    }

    /**
     *
     * @return
     * The thermostat_xid
     */
    public String getThermostat_xid() {
        return thermostat_xid;
    }

    /**
     *
     * @param thermostat_xid
     * The thermostat_xid
     */
    public void setThermostat_xid(String thermostat_xid) {
        this.thermostat_xid = thermostat_xid;
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
     * The locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     *
     * @param locale
     * The locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     *
     * @return
     * The temperature_scale
     */
    public String getTemperature_scale() {
        return temperature_scale;
    }

    /**
     *
     * @param temperature_scale
     * The temperature_scale
     */
    public void setTemperature_scale(String temperature_scale) {
        this.temperature_scale = temperature_scale;
    }

    /**
     *
     * @return
     * The hvac_mode
     */
    public String getHvac_mode() {
        return hvac_mode;
    }

    /**
     *
     * @param hvac_mode
     * The hvac_mode
     */
    public void setHvac_mode(String hvac_mode) {
        this.hvac_mode = hvac_mode;
    }

    /**
     *
     * @return
     * The target_temperature_high_c
     */
    public Float getTarget_temperature_high_c() {
        return target_temperature_high_c;
    }

    /**
     *
     * @param target_temperature_high_c
     * The target_temperature_high_c
     */
    public void setTarget_temperature_high_c(Float target_temperature_high_c) {
        this.target_temperature_high_c = target_temperature_high_c;
    }

    /**
     *
     * @return
     * The target_temperature_high_f
     */
    public Float getTarget_temperature_high_f() {
        return target_temperature_high_f;
    }

    /**
     *
     * @param target_temperature_high_f
     * The target_temperature_high_f
     */
    public void setTarget_temperature_high_f(Float target_temperature_high_f) {
        this.target_temperature_high_f = target_temperature_high_f;
    }

    /**
     *
     * @return
     * The target_temperature_low_c
     */
    public Float getTarget_temperature_low_c() {
        return target_temperature_low_c;
    }

    /**
     *
     * @param target_temperature_low_c
     * The target_temperature_low_c
     */
    public void setTarget_temperature_low_c(Float target_temperature_low_c) {
        this.target_temperature_low_c = target_temperature_low_c;
    }

    /**
     *
     * @return
     * The target_temperature_low_f
     */
    public Float getTarget_temperature_low_f() {
        return target_temperature_low_f;
    }

    /**
     *
     * @param target_temperature_low_f
     * The target_temperature_low_f
     */
    public void setTarget_temperature_low_f(Float target_temperature_low_f) {
        this.target_temperature_low_f = target_temperature_low_f;
    }

    /**
     *
     * @return
     * The ambient_temperature_c
     */
    public Float getAmbient_temperature_c() {
        return ambient_temperature_c;
    }

    /**
     *
     * @param ambient_temperature_c
     * The ambient_temperature_c
     */
    public void setAmbient_temperature_c(Float ambient_temperature_c) {
        this.ambient_temperature_c = ambient_temperature_c;
    }

    /**
     *
     * @return
     * The ambient_temperature_f
     */
    public Float getAmbient_temperature_f() {
        return ambient_temperature_f;
    }

    /**
     *
     * @param ambient_temperature_f
     * The ambient_temperature_f
     */
    public void setAmbient_temperature_f(Float ambient_temperature_f) {
        this.ambient_temperature_f = ambient_temperature_f;
    }

    /**
     *
     * @return
     * The humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * @return
     * The is_online
     */
    public Boolean getIs_online() {
        return is_online;
    }

    /**
     *
     * @param is_online
     * The is_online
     */
    public void setIs_online(Boolean is_online) {
        this.is_online = is_online;
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

    /**
     *
     * @return
     * The structure_name
     */
    public String getStructure_name() {
        return structure_name;
    }

    /**
     *
     * @param structure_name
     * The structure_name
     */
    public void setStructure_name(String structure_name) {
        this.structure_name = structure_name;
    }

    /**
     *
     * @return
     * The away
     */
    public Boolean getAway() {
        return away;
    }

    /**
     *
     * @param away
     * The away
     */
    public void setAway(Boolean away) {
        this.away = away;
    }

    /**
     *
     * @return
     * The has_leaf
     */
    public Boolean getHas_leaf() {
        return has_leaf;
    }

    /**
     *
     * @param has_leaf
     * The has_leaf
     */
    public void setHas_leaf(Boolean has_leaf) {
        this.has_leaf = has_leaf;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (id != null) result.append(" id: " + id.toString() + NEWLINE);
        if (thermostat_token_id != null) result.append(" thermostat_token_id: " + thermostat_token_id.toString() + NEWLINE);
        if (thermostat_type_id != null) result.append(" thermostat_type_id: " + thermostat_type_id.toString() + NEWLINE);
        if (thermostat_xid != null) result.append(" thermostat_xid: " + thermostat_xid.toString() + NEWLINE);
        if (name != null) result.append(" name: " + name.toString() + NEWLINE);
        if (locale != null) result.append(" locale: " + locale.toString() + NEWLINE);
        if (temperature_scale != null) result.append(" temperature_scale: " + temperature_scale.toString() + NEWLINE);
        if (hvac_mode != null) result.append(" hvac_mode: " + hvac_mode.toString() + NEWLINE);
        if (target_temperature_high_c != null) result.append(" target_temperature_high_c: " + target_temperature_high_c.toString() + NEWLINE);
        if (target_temperature_high_f != null) result.append(" target_temperature_high_f: " + target_temperature_high_f.toString() + NEWLINE);
        if (target_temperature_low_c != null) result.append(" target_temperature_low_c: " + target_temperature_low_c.toString() + NEWLINE);
        if (target_temperature_low_f != null) result.append(" target_temperature_low_f: " + target_temperature_low_f.toString() + NEWLINE);
        if (ambient_temperature_c != null) result.append(" ambient_temperature_c: " + ambient_temperature_c.toString() + NEWLINE);
        if (ambient_temperature_f != null) result.append(" ambient_temperature_f: " + ambient_temperature_f.toString() + NEWLINE);
        if (humidity != null) result.append(" humidity: " + humidity.toString() + NEWLINE);
        if (is_online != null) result.append(" is_online: " + is_online.toString() + NEWLINE);
        if (updated_at_utc != null) result.append(" updated_at_utc: " + updated_at_utc.toString() + NEWLINE);
        if (linked_device_id != null) result.append(" linked_device_id: " + linked_device_id.toString() + NEWLINE);
        if (structure_name != null) result.append(" structure_name: " + structure_name.toString() + NEWLINE);
        if (away != null) result.append(" away: " + away.toString() + NEWLINE);
        if (has_leaf != null) result.append(" has_leaf: " + has_leaf.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }
}