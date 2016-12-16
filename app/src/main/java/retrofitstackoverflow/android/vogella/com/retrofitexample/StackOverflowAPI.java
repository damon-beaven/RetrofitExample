package retrofitstackoverflow.android.vogella.com.retrofitexample;

/**
 * Created by dbeaven on 1/25/2016.
 */
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.StackOverflowQuestions;

public interface StackOverflowAPI {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);
}