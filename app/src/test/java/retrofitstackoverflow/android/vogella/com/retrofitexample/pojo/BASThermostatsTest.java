package retrofitstackoverflow.android.vogella.com.retrofitexample.pojo;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import retrofitstackoverflow.android.vogella.com.retrofitexample.ApiTestHelper;
import retrofitstackoverflow.android.vogella.com.retrofitexample.BuildConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class BASThermostatsTest {

    BASThermostats subject;

    @Before
    public void setup() {

    }

    @After
    public void teardown() {

    }

    @Test
    public void testDeserializeFromJson() throws Exception {
        String userDevicesJsonString = ApiTestHelper.getStringFromFile(this, "user-thermostats.json");
        Gson gson = new Gson();
        subject = gson.fromJson(userDevicesJsonString, BASThermostats.class);
        assertThat(subject, is(notNullValue(BASThermostats.class)));
    }
}
