package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASDevices;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostats;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatsLinkedDevicesInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserConfirmInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASThermostat;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.EcobeeAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.NestAuthInfo;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASUser extends BASCloudTask{
    private static final String TAG = BASUser.class.getSimpleName();

    public void confirmUserFromPin(BASAuthInfo basAuthInfo, Integer userPin, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;
        mPasswordResetPin = userPin.toString(); // we keep this for later

        Call<BASUserConfirmInfo> call = mBasCloudAPI.confirmUserFromPin(
                "Bearer " + mToken.getAccess_token(),
                basAuthInfo.email,
                mPasswordResetPin);

        //asynchronous call
        call.enqueue(new Callback<BASUserConfirmInfo>() {
            @Override
            public void onResponse(Response<BASUserConfirmInfo> response,
                                   Retrofit retrofit) {
                //let's save that auth token for subsequent cloud calls!!
                if (goodResponse(response)) {
                    mConfirmInfo = (BASUserConfirmInfo)response.body();
                    handleGoodResponse(response, delegate);
                }
                else {
                    handleErrorResponse(response, delegate);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void createUserFromToken(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASUserInfo> call = mBasCloudAPI.createUserFromToken(
                "Bearer " + mToken.getAccess_token(),
                basAuthInfo.email,
                basAuthInfo.password,
                basAuthInfo.first_name,
                basAuthInfo.last_name);

        //asynchronous call
        call.enqueue(new Callback<BASUserInfo>() {
            @Override
            public void onResponse(Response<BASUserInfo> response,
                                   Retrofit retrofit) {
                //let's save that auth token for subsequent cloud calls!!
                if (goodResponse(response)) {
                    handleGoodResponse(response, delegate);
                }
                else {
                    handleErrorResponse(response, delegate);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void getExistingUserInfo(final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASUserInfo> call = mBasCloudAPI.getUserInfo("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASUserInfo>() {
            @Override
            public void onResponse(Response<BASUserInfo> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    //Deprecated?
    public void getExistingUserDevices(final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASDevices> call = mBasCloudAPI.getUserDevices("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASDevices>() {
            @Override
            public void onResponse(Response<BASDevices> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void createExistingUserThermostatAccountAssociation(BASThermostat.ThermostatTypeId thermostatEnum,
                                                                final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;
        BASThermostat myThermostat = new BASThermostat();
        String myThermostatId = myThermostat.getThermostatTypeIdFromEnum(thermostatEnum);
        String myAuthCode = myThermostat.getThermostatAuthCodeFromEnum(thermostatEnum);

        Call<BASThermostats> call = mBasCloudAPI.createUserThermostat("Bearer " + mToken.getAccess_token(),
                myThermostatId, myAuthCode);

        //asynchronous call
        call.enqueue(new Callback<BASThermostats>() {
            @Override
            public void onResponse(Response<BASThermostats> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void deleteExistingUserThermostatAccountAssociation(String thermostatTokenId,
                                                               final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.deleteUserThermostat("Bearer " + mToken.getAccess_token(),
                thermostatTokenId);

        //asynchronous call
        call.enqueue(new Callback<CloudMessage>() {
            @Override
            public void onResponse(Response<CloudMessage> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void getExistingUserThermostats(final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASThermostats> call = mBasCloudAPI.getUserThermostats("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASThermostats>() {
            @Override
            public void onResponse(Response<BASThermostats> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void getExistingUserThermostatsLinkedDevices(final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASThermostatsLinkedDevicesInfo> call = mBasCloudAPI.getUserThermostatsLinkedDevices("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(new Callback<BASThermostatsLinkedDevicesInfo>() {
            @Override
            public void onResponse(Response<BASThermostatsLinkedDevicesInfo> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    // This will update all of the field in basAuthInfo.
    // Do NOT send blanks or null values unless that is what you really want.
    // Normal process is to just use the same BASAuthInfo you used when you logged in
    // and change the specific fields you intend to update.
    public void updateExistingUser(BASAuthInfo basAuthInfo, String basUserId, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        //how to determine what to update based on BASUserInfo object
        Call<BASUserInfo> call = mBasCloudAPI.updateUserFromId(
                "Bearer " + mToken.getAccess_token(),
                basUserId,
                basAuthInfo.email,
                basAuthInfo.password,
                basAuthInfo.first_name,
                basAuthInfo.last_name);

        //asynchronous call
        call.enqueue(new Callback<BASUserInfo>() {
            @Override
            public void onResponse(Response<BASUserInfo> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void getResetPasswordEmail(String userEmail, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.getResetPasswordEmail(
                "Bearer " + mToken.getAccess_token(),
                userEmail);

        //asynchronous call
        call.enqueue(new Callback<CloudMessage>() {
            @Override
            public void onResponse(Response<CloudMessage> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void resetUserPasswordFromResetToken(BASAuthInfo basAuthInfo, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.resetUserPasswordFromResetToken(
                "Bearer " + mToken.getAccess_token(),
                basAuthInfo.email,
                mPasswordResetPin,
                mConfirmInfo.getUserConfirmation().getResetToken(),
                basAuthInfo.password);

        //asynchronous call
        call.enqueue(new Callback<CloudMessage>() {
            @Override
            public void onResponse(Response<CloudMessage> response,
                                   Retrofit retrofit) {
                //let's save that auth token for subsequent cloud calls!!
                if (goodResponse(response)) {
                    handleGoodResponse(response, delegate);
                }
                else {
                    handleErrorResponse(response, delegate);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }


    public void getNewPinEmail(String userEmail, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.getNewPinEmail(
                "Bearer " + mToken.getAccess_token(),
                userEmail);

        //asynchronous call
        call.enqueue(new Callback<CloudMessage>() {
            @Override
            public void onResponse(Response<CloudMessage> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

    public void deleteExistingUser(String userID, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.userDelete(
                "Bearer " + mToken.getAccess_token(),
                userID);

        //asynchronous call
        call.enqueue(new Callback<CloudMessage>() {
            @Override
            public void onResponse(Response<CloudMessage> response,
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
                Log.wtf(TAG, t.toString());
                //not even sure if Retrofit 2.0 calls this anymore...we can
                //add another call if it actually does
            }
        });
    }

}
