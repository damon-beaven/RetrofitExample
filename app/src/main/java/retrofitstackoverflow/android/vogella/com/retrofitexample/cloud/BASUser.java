package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BASDevicesCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BASThermostatsCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BASThermostatsLinkedDevicesInfoCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BASUserConfirmInfoCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.BASUserInfoCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.callbacks.CloudMessageCallback;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASDevices;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostats;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatsLinkedDevicesInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserConfirmInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

/**
 * Created by dbeaven on 1/27/2016.
 */
public class BASUser extends BASCloudTask{
    private static final String TAG = BASUser.class.getSimpleName();

    private Callback<CloudMessage> mCloudMessageCallback = new CloudMessageCallback<>(this.mDelegate);
    private Callback<BASUserConfirmInfo> mBASUserConfirmInfoCallback = new BASUserConfirmInfoCallback<>(this.mDelegate);
    private Callback<BASUserInfo> mBASUserInfoCallback = new BASUserInfoCallback<>(this.mDelegate);
    private Callback<BASDevices> mBASDevicesCallback = new BASDevicesCallback<>(this.mDelegate);
    private Callback<BASThermostats> mBASThermostatsCallback = new BASThermostatsCallback<>(this.mDelegate);
    private Callback<BASThermostatsLinkedDevicesInfo> mBASThermostatsLinkedDevicesInfoCallback = new BASThermostatsLinkedDevicesInfoCallback<>(this.mDelegate);

    public void confirmUserFromPin(BASAuthInfo basAuthInfo, Integer userPin, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;
        mPasswordResetPin = userPin.toString(); // we keep this for later

        Call<BASUserConfirmInfo> call = mBasCloudAPI.confirmUserFromPin(
                "Bearer " + mToken.getAccess_token(),
                basAuthInfo.email,
                mPasswordResetPin);

        //asynchronous call
        call.enqueue(this.mBASUserConfirmInfoCallback);
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
        call.enqueue(this.mBASUserInfoCallback);
    }

    public void getExistingUserInfo(final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASUserInfo> call = mBasCloudAPI.getUserInfo("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(this.mBASUserInfoCallback);
    }

    //Deprecated?
    public void getExistingUserDevices(final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASDevices> call = mBasCloudAPI.getUserDevices("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(this.mBASDevicesCallback);
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
        call.enqueue(this.mBASThermostatsCallback);
    }

    public void deleteExistingUserThermostatAccountAssociation(String thermostatTokenId,
                                                               final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.deleteUserThermostat("Bearer " + mToken.getAccess_token(),
                thermostatTokenId);

        //asynchronous call
        call.enqueue(this.mCloudMessageCallback);
    }

    public void getExistingUserThermostats(final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASThermostats> call = mBasCloudAPI.getUserThermostats("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(this.mBASThermostatsCallback);
    }

    public void getExistingUserThermostatsLinkedDevices(final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<BASThermostatsLinkedDevicesInfo> call = mBasCloudAPI.getUserThermostatsLinkedDevices("Bearer " + mToken.getAccess_token());

        //asynchronous call
        call.enqueue(this.mBASThermostatsLinkedDevicesInfoCallback);
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
        call.enqueue(this.mBASUserInfoCallback);
    }

    public void getResetPasswordEmail(String userEmail, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.getResetPasswordEmail(
                "Bearer " + mToken.getAccess_token(),
                userEmail);

        //asynchronous call
        call.enqueue(this.mCloudMessageCallback);
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
        call.enqueue(this.mCloudMessageCallback);
    }


    public void getNewPinEmail(String userEmail, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.getNewPinEmail(
                "Bearer " + mToken.getAccess_token(),
                userEmail);

        //asynchronous call
        call.enqueue(this.mCloudMessageCallback);
    }

    public void deleteExistingUser(String userID, final CloudAsyncResponse delegate) {
        this.mDelegate = delegate;

        Call<CloudMessage> call = mBasCloudAPI.userDelete(
                "Bearer " + mToken.getAccess_token(),
                userID);

        //asynchronous call
        call.enqueue(this.mCloudMessageCallback);
    }

}
