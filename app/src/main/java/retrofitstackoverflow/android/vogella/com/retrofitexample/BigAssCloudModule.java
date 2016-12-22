package retrofitstackoverflow.android.vogella.com.retrofitexample;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BigAssCloudApiInterface;

/**
 * Created by zfreeman on 12/22/16.
 */
@Module
public class BigAssCloudModule {

    @Provides
    @UserScope
    public BigAssCloudApiInterface providesBigAssCloudInterface(Retrofit retrofit) {
        return retrofit.create(BigAssCloudApiInterface.class);
    }
}
