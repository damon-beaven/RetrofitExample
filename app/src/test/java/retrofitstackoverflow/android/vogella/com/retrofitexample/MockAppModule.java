package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.app.Application;

public class MockAppModule extends AppModule {

    public MockAppModule(Application application) {
        super(application);
    }

    @Override
    Application providesApplication() {
        return mApplication;
    }
}
