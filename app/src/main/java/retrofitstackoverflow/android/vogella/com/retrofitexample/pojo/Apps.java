package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

/**
 * Created by dbeaven on 1/26/2016.
 */
public class Apps {

    private Jawbone jawbone;
    private Nest nest;

    /**
     *
     * @return
     * The jawbone
     */
    public Jawbone getJawbone() {
        return jawbone;
    }

    /**
     *
     * @param jawbone
     * The jawbone
     */
    public void setJawbone(Jawbone jawbone) {
        this.jawbone = jawbone;
    }

    /**
     *
     * @return
     * The nest
     */
    public Nest getNest() {
        return nest;
    }

    /**
     *
     * @param nest
     * The nest
     */
    public void setNest(Nest nest) {
        this.nest = nest;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");

        result.append(this.getClass().getSimpleName() + " Object {" + NEWLINE);
        if (jawbone != null) result.append(" jawbone: " + jawbone.toString() + NEWLINE);
        if (nest != null) result.append(" nest: " + nest.toString() + NEWLINE);
        result.append("}");

        return result.toString();
    }
}
