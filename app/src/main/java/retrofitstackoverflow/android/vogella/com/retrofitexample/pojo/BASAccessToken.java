package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;


public class BASAccessToken extends BaseResponse {

    private String access_token;
    private String token_type;
    private int expires_in;

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

    /**
     *
     * @return
     * The token_type
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     *
     * @param token_type
     * The token_type
     */
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    /**
     *
     * @return
     * The expires_in
     */
    public int getExpires_in() {
        return expires_in;
    }

    /**
     *
     * @param expires_in
     * The expires_in
     */
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//        return StringUtil.toDetailString(this);
//        return ModelUtil.toStringFor(this);
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        result.append(" access_token: " + access_token + NEWLINE);
        result.append(" token_type: " + token_type + NEWLINE);
        result.append(" expires_in: " + expires_in + NEWLINE);
        result.append("}");

        return result.toString();
    }

}