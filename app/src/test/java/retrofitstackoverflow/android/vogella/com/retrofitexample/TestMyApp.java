package retrofitstackoverflow.android.vogella.com.retrofitexample;


import android.content.SharedPreferences;

import org.mockito.Mockito;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class TestMyApp extends MyApp {
    @Override
    protected DaggerBigAssCloudComponent.Builder prepareBigAssCloudComponent() {
        return super.prepareBigAssCloudComponent()
                .netComponent(new TestNetComponent())
                .bigAssCloudModule(new TestBigAssCloudModule());
    }


}
