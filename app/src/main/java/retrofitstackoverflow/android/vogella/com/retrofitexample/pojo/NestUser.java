package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class NestUser {

    private String id;
    private String access_token;

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
     * The access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     *
     * @param access_token
     * The access_token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        result.append(" id: " + id + NEWLINE);
        result.append(" access_token: " + access_token + NEWLINE);
        result.append("}");

        return result.toString();
    }

}
