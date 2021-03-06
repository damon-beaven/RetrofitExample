package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatTypes;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.EcobeeAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.NestAuthInfo;

/**
 * Created by dbeaven on 1/29/2016.
 */

public class BASThermostat extends BASCloudTask {
    private static final String TAG = BASThermostat.class.getSimpleName();

    private NestAuthInfo myNestAuthInfo = NestAuthInfo.getInstance();
    private EcobeeAuthInfo myEcobeeAuthInfo = EcobeeAuthInfo.getInstance();

    public enum ThermostatTypeId {NEST, ECOBEE};

    public String getThermostatTypeIdFromEnum (ThermostatTypeId myThermostatTypeId) {
        String typeIdRetVal;
        switch (myThermostatTypeId) {
            case NEST:
                //eventually I'd like to query for this...but type ids aren't supposed to change anyway
                typeIdRetVal = "329ec0b2-c598-4ce5-8cf1-41efb0631e1a";
                break;
            case ECOBEE:
                //eventually I'd like to query for this...but type ids aren't supposed to change anyway
                typeIdRetVal = "cdb84c3c-1b24-4872-99dd-ecefd58d8cb9";
                break;
            default:
                typeIdRetVal = "Type ID not recognized.";
                break;
        }
        return typeIdRetVal;
    }

    public String getThermostatAuthCodeFromEnum (ThermostatTypeId myThermostatTypeId) {
        String authCodeRetVal;
        switch (myThermostatTypeId) {
            case NEST:
                authCodeRetVal = myNestAuthInfo.authCode;
                break;
            case ECOBEE:
                authCodeRetVal = myEcobeeAuthInfo.authCode;
                break;
            default:
                authCodeRetVal = "Type ID not recognized.";
                break;
        }
        return authCodeRetVal;
    }

    //we probably need to pass in a thermostat list to get the token from...or
    //we make the API user pass it in.
    //What's best?
    public String getThermostatTokenFromEnum (ThermostatTypeId myThermostatTypeId) {
        String authCodeRetVal;
        switch (myThermostatTypeId) {
            case NEST:
                authCodeRetVal = myNestAuthInfo.authCode;
                break;
            case ECOBEE:
                authCodeRetVal = myEcobeeAuthInfo.authCode;
                break;
            default:
                authCodeRetVal = "Type ID not recognized.";
                break;
        }
        return authCodeRetVal;
    }


    public void getThermostatTypes(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASThermostatTypes> call = mBasCloudAPI.getThermostatTypes("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASThermostatTypes>() {
            @Override
            public void onResponse(Response<BASThermostatTypes> response,
                                   Retrofit retrofit) {

                if (goodResponse(response)) {
                    handleGoodResponse(response, delegate);
                }
                else {
                    handleErrorResponse(response, delegate);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }
}
