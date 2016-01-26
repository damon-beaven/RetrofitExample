package retrofitstackoverflow.android.vogella.com.retrofitexample;

/**
 * Created by dbeaven on 1/25/2016.
 */
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.Call;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.StackOverflowQuestions;

public interface StackOverflowAPI {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);
}