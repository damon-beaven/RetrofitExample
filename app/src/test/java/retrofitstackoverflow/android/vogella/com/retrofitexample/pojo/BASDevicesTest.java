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
import static org.hamcrest.Matchers.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class BASDevicesTest {

    BASDevices subject;

    @Before
    public void setup() {

    }

    @After
    public void teardown() {

    }

    @Test
    public void testDeserializeFromJson() throws Exception {
        String userDevicesJsonString = ApiTestHelper.getStringFromFile(this, "user-devices.json");
        Gson gson = new Gson();
        subject = gson.fromJson(userDevicesJsonString, BASDevices.class);
        assertThat(subject, is(notNullValue(BASDevices.class)));
        assertThat(subject.getSuccess(), is(true));
        assertThat(subject.getCount(), is(7));
        assertThat(subject.getCurrent_page(), is(1));
        assertThat(subject.getPer_page(), is(128));
        assertThat(subject.getTotal(), is(7));
        assertThat(subject.getDevices(), is(notNullValue()));
    }
}
