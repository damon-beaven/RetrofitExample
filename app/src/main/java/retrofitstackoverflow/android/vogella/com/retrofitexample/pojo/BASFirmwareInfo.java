package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

public class BASFirmwareInfo {

    private Boolean success;
    private String firmware_id;
    private String firmware_url;
    private String firmware_key;
    private String version;
    private String release_notes;
    private String device_type;
    private Integer device_type_id;
    private String channel;
    private String commit;
    private String submitter;
    private String published_at;
    private String updated_at_utc;
    private String created_at_utc;

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
     * The firmware_id
     */
    public String getFirmware_id() {
        return firmware_id;
    }

    /**
     *
     * @param firmware_id
     * The firmware_id
     */
    public void setFirmware_id(String firmware_id) {
        this.firmware_id = firmware_id;
    }

    /**
     *
     * @return
     * The firmware_url
     */
    public String getFirmware_url() {
        return firmware_url;
    }

    /**
     *
     * @param firmware_url
     * The firmware_url
     */
    public void setFirmware_url(String firmware_url) {
        this.firmware_url = firmware_url;
    }

    /**
     *
     * @return
     * The firmware_key
     */
    public String getFirmware_key() {
        return firmware_key;
    }

    /**
     *
     * @param firmware_key
     * The firmware_key
     */
    public void setFirmware_key(String firmware_key) {
        this.firmware_key = firmware_key;
    }

    /**
     *
     * @return
     * The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     * The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     * The release_notes
     */
    public String getRelease_notes() {
        return release_notes;
    }

    /**
     *
     * @param release_notes
     * The release_notes
     */
    public void setRelease_notes(String release_notes) {
        this.release_notes = release_notes;
    }

    /**
     *
     * @return
     * The device_type
     */
    public String getDevice_type() {
        return device_type;
    }

    /**
     *
     * @param device_type
     * The device_type
     */
    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    /**
     *
     * @return
     * The device_type_id
     */
    public Integer getDevice_type_id() {
        return device_type_id;
    }

    /**
     *
     * @param device_type_id
     * The device_type_id
     */
    public void setDevice_type_id(Integer device_type_id) {
        this.device_type_id = device_type_id;
    }

    /**
     *
     * @return
     * The channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     *
     * @param channel
     * The channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     *
     * @return
     * The commit
     */
    public String getCommit() {
        return commit;
    }

    /**
     *
     * @param commit
     * The commit
     */
    public void setCommit(String commit) {
        this.commit = commit;
    }

    /**
     *
     * @return
     * The submitter
     */
    public String getSubmitter() {
        return submitter;
    }

    /**
     *
     * @param submitter
     * The submitter
     */
    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    /**
     *
     * @return
     * The published_at
     */
    public String getPublished_at() {
        return published_at;
    }

    /**
     *
     * @param published_at
     * The published_at
     */
    public void setPublished_at(String published_at) {
        this.published_at = published_at;
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (success != null) result.append(" success: " + success.toString() + NEWLINE);
        if (firmware_id != null) result.append(" firmware_id: " + firmware_id + NEWLINE);
        if (firmware_url != null) result.append(" firmware_url: " + firmware_url + NEWLINE);
        if (firmware_key != null) result.append(" firmware_key: " + firmware_key + NEWLINE);
        if (version != null) result.append(" version: " + firmware_url + NEWLINE);
        if (release_notes != null) result.append(" release_notes: " + firmware_url + NEWLINE);
        if (device_type != null) result.append(" device_type: " + device_type + NEWLINE);
        if (device_type_id != null) result.append(" device_type_id: " + device_type_id.toString() + NEWLINE);
        if (channel != null) result.append(" channel: " + channel + NEWLINE);
        if (commit != null) result.append(" commit: " + commit + NEWLINE);
        if (submitter != null) result.append(" submitter: " + submitter + NEWLINE);
        if (published_at != null) result.append(" published_at: " + published_at + NEWLINE);
        if (updated_at_utc != null) result.append(" updated_at_utc: " + updated_at_utc + NEWLINE);
        if (created_at_utc != null) result.append(" created_at_utc: " + created_at_utc + NEWLINE);
        result.append("}");

        return result.toString();
    }
}