package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import retrofit2.Call;
import retrofit2.Callback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BaseCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatTypes;
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
        Callback<BASThermostatTypes> basThermostatTypesCallback = new BaseCallback(this.mDelegate);
        //asynchronous call
        call.enqueue(basThermostatTypesCallback);
    }
}
