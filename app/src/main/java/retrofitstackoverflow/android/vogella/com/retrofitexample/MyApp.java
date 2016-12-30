package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.app.Application;

import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;

public class MyApp extends Application {

    private NetComponent mNetComponent;
    private BigAssCloudComponent mBigAssCloudComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASCloudTask.API_BASE_URL))
                .build();
        mBigAssCloudComponent = DaggerBigAssCloudComponent.builder()
                .netComponent(mNetComponent)
                .bigAssCloudModule(new BigAssCloudModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
    public BigAssCloudComponent getBigAssCloudComponent() { return mBigAssCloudComponent; }
}
