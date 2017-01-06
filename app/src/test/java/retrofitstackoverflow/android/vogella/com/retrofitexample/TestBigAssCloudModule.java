package retrofitstackoverflow.android.vogella.com.retrofitexample;

import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudAPI;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BigAssCloudApiInterface;

/**
 * Created by zfreeman on 1/6/17.
 */

public class TestBigAssCloudModule extends BigAssCloudModule {

    @Provides
    @UserScope
    @Override
    public BigAssCloudApiInterface providesBigAssCloudInterface(Retrofit retrofit) {
        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit
                .Builder(retrofit)
                .networkBehavior(behavior)
                .build();
        BehaviorDelegate<BigAssCloudApiInterface> basCloudAPIBehaviorDelegate = mockRetrofit.create(BigAssCloudApiInterface.class);
        return new MockBigAssCloudApiInterface(basCloudAPIBehaviorDelegate);
    }
}
