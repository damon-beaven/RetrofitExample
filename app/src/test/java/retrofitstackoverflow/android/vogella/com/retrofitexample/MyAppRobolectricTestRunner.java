package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.support.annotation.NonNull;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

/**
 * Created by zfreeman on 1/6/17.
 */

public class MyAppRobolectricTestRunner extends RobolectricTestRunner {

    // This value should be changed as soon as Robolectric will support newer api.
    private static final int SDK_EMULATE_LEVEL = 23;

    public MyAppRobolectricTestRunner(@NonNull Class<?> klass) throws Exception {
        super(klass);
    }

    @Override
    public Config getConfig(@NonNull Method method) {
        final Config defaultConfig = super.getConfig(method);
        return new Config.Implementation(
                new int[]{SDK_EMULATE_LEVEL},
                defaultConfig.manifest(),
                defaultConfig.qualifiers(),
                defaultConfig.packageName(),
                defaultConfig.abiSplit(),
                defaultConfig.resourceDir(),
                defaultConfig.assetDir(),
                defaultConfig.buildDir(),
                defaultConfig.shadows(),
                defaultConfig.instrumentedPackages(),
                TestMyApp.class, // Notice that we override real application class for tests.
                defaultConfig.libraries(),
                defaultConfig.constants() == Void.class ? BuildConfig.class : defaultConfig.constants()
        );
    }

    @NonNull
    public static MyApp myApp() {
        return (MyApp) RuntimeEnvironment.application;
    }
}
