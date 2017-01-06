package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.content.SharedPreferences;

import org.mockito.Mockito;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zfreeman on 1/6/17.
 */

public class TestNetComponent implements NetComponent {
    @Override
    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.com")
                .build();
        return retrofit;
    }
    @Override
    public OkHttpClient okHttpClient() {
        return Mockito.mock(OkHttpClient.class);
    }
    @Override
    public SharedPreferences sharedPreferences() {
        return Mockito.mock(SharedPreferences.class);
    }
}
