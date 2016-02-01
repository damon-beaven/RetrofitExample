package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class AppsDeprecated {

    private JawboneDeprecated jawboneDeprecated;
    private NestDeprecated nestDeprecated;

    /**
     *
     * @return
     * The jawboneDeprecated
     */
    public JawboneDeprecated getJawboneDeprecated() {
        return jawboneDeprecated;
    }

    /**
     *
     * @param jawboneDeprecated
     * The jawboneDeprecated
     */
    public void setJawboneDeprecated(JawboneDeprecated jawboneDeprecated) {
        this.jawboneDeprecated = jawboneDeprecated;
    }

    /**
     *
     * @return
     * The nestDeprecated
     */
    public NestDeprecated getNestDeprecated() {
        return nestDeprecated;
    }

    /**
     *
     * @param nestDeprecated
     * The nestDeprecated
     */
    public void setNestDeprecated(NestDeprecated nestDeprecated) {
        this.nestDeprecated = nestDeprecated;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (jawboneDeprecated != null) result.append(" jawboneDeprecated: " + jawboneDeprecated.toString() + NEWLINE);
        if (nestDeprecated != null) result.append(" nestDeprecated: " + nestDeprecated.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }
}
