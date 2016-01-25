package retrofitstackoverflow.android.vogella.com.retrofitexample;

/**
 * Created by dbeaven on 1/25/2016.
 */
// This is used to map the JSON keys to the object by GSON
public class Question {

    String title;
    String link;

    @Override
    public String toString() {
        return(title);
    }
}