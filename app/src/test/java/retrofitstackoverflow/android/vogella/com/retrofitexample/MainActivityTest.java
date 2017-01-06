package retrofitstackoverflow.android.vogella.com.retrofitexample;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java_cup.Main;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;

@RunWith(MyAppRobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity subject;
    ActivityController<MainActivity> activityController;
    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(MainActivity.class);
        subject = activityController.get();
        activityController.create();
    }

    @After
    public void teardown() {

    }

    @Test
    public void testDoLogin() {
        BASAccessToken basAccessToken = new BASAccessToken();
        basAccessToken.setAccess_token("test");
        subject.doLogin();
        assertThat(subject.myToken, is(equalTo(basAccessToken)));
    }
}
