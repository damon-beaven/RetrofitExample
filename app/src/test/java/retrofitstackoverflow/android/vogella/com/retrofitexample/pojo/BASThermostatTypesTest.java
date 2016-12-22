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
public class BASThermostatTypesTest {

    BASThermostatTypes subject;

    @Before
    public void setup() {

    }

    @After
    public void teardown() {

    }

    @Test
    public void testDeserializeFromJson() throws Exception {
        String thermostatTypesJsonString = ApiTestHelper.getStringFromFile(this, "thermostat-types.json");
        Gson gson = new Gson();
        subject = gson.fromJson(thermostatTypesJsonString, BASThermostatTypes.class);
        assertThat(subject, is(notNullValue(BASThermostatTypes.class)));
        assertThat(subject.getSuccess(), is(true));
        assertThat(subject.getThermostat_types(), is(notNullValue()));
        assertThat(subject.getThermostat_types().size(), is(2));
        assertThat(subject.getThermostat_types().get(0), is(notNullValue(Thermostat_type.class)));

    }
}
