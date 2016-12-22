package retrofitstackoverflow.android.vogella.com.retrofitexample;

import dagger.Component;

/**
 * Created by zfreeman on 12/22/16.
 */
@UserScope
@Component(dependencies = NetComponent.class, modules = BigAssCloudModule.class)
public interface BigAssCloudComponent {
    void inject(MainActivity activity);
}
