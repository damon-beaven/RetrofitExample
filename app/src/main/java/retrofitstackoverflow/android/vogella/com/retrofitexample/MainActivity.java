package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASAuth;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASFirmware;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASThermostat;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASUser;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BigAssCloudApiInterface;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatTypes;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.EcobeeAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.NestAuthInfo;

public class MainActivity extends Activity {
    @Inject
    Retrofit mRetrofit;

    @Inject
    BigAssCloudApiInterface mBigAssCloudApiInterface;

    @Inject
    SharedPreferences mSharedPreferences;

    private enum LoginType {BASUSER, NESTUSER, ECOBEEUSER};
    private String mBaseURL;
    private Integer mPinFromEmail;
    private String successString = "Success...>> ";
    private String failureString = "Failure...>> ";
    private String returncodeString = "Return code...>> ";
    protected BASAccessToken myToken = new BASAccessToken();
    protected BASUserInfo myUserInfo = new BASUserInfo();
    private NestAuthInfo myNestAuthInfo = NestAuthInfo.getInstance();
    private EcobeeAuthInfo myEcobeeAuthInfo = EcobeeAuthInfo.getInstance();
    private final static int NEST_LOGIN_ACTIVITY = 1;
    private final static int ECOBEE_LOGIN_ACTIVITY = 2;

    //thermostat stuff
    private BASThermostatTypes myThermostatTypes= new BASThermostatTypes();

    //new user stuff
    private BASAuthInfo basAuthInfo = new BASAuthInfo();
    private BASAuthInfo basNewUserAuthInfo = new BASAuthInfo();
    protected BASUserInfo basNewUserInfo = new BASUserInfo();

    private static final String TAG = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //set up another user so we can add/delete/etc
//        basNewUserAuthInfo.email = "rickyfirst@notlast.com";
        basNewUserAuthInfo.email = "damon.beaven@bigasssolutions.com";       //dev
        basNewUserAuthInfo.password = "qwertyui";
        basNewUserAuthInfo.first_name = "Ricky";
        basNewUserAuthInfo.last_name = "Bobby";

        BASAuth tempUserAuth = new BASAuth();
        mBaseURL = BASCloudTask.API_BASE_URL;

        setContentView(R.layout.activity_main);
        ((MyApp) getApplication()).getNetComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        updateTitle(item.getTitle().toString());

        switch (item.getItemId()) {
            case R.id.menu_auth:
                doNewAuth(basAuthInfo);
                return true;
            case R.id.menu_userInfo:
                doNewUserInfo(basAuthInfo);
                return true;
            case R.id.menu_userDevices:
                doGetUserDevices(basAuthInfo);
                return true;
            case R.id.menu_userThermostats:
                doGetUserThermostats(basAuthInfo);
                return true;
            case R.id.menu_userThermostatsLinkedDevices:
                doGetUserThermostatsLinkedDevices(basAuthInfo);
                return true;
            case R.id.menu_getCreateUserToken:
                doCreateUserToken();
                return true;
            case R.id.menu_authNewUser:
                doNewAuth(basNewUserAuthInfo);
                return true;
            case R.id.menu_getNewPinEmail:
                doGetNewPinEmail();
                return true;
            case R.id.menu_createUserFromToken:
                doCreateUserFromToken();
                return true;
            case R.id.menu_confirmUserFromPin:
                showPinDialog();
                //doConfirmUserFromPin();
                return true;
            case R.id.menu_getNewUserInfo:
                doNewUserInfo(basNewUserAuthInfo);
                return true;
            case R.id.menu_updateNewUserInfo:
                doUpdateUserInfo(basNewUserAuthInfo);
                return true;
            case R.id.menu_deleteUser:
                doDeleteUser();
                return true;
            case R.id.menu_getResetPasswordToken:
                doGetPasswordResetToken();
                return true;
            case R.id.menu_getResetPasswordEmail:
                doGetPasswordResetEmail();
                return true;
            case R.id.menu_getEnteredPinForconfirmUserFromPin:
                showPinDialog();
                return true;
            case R.id.menu_resetPasswordFromResetToken:
                doResetPasswordFromResetToken();
                return true;
            case R.id.menu_getFirmwareDownloadToken:
                doGetFirmwareDownloadToken();
                return true;
            case R.id.menu_getFirmwareDownloadUrlFromToken:
                doGetFirmwareDownloadUrlFromToken();
                return true;
            case R.id.menu_getThermostatTypes:
                doGetThermostatTypes(basAuthInfo);
                return true;
            case R.id.menu_loginBAS:
//                doGetThermostatTypes();
                return true;
            case R.id.menu_loginNest:
                doNestLogin();
                return true;
            case R.id.menu_loginEcobee:
                doEcobeeLogin();
                return true;
            case R.id.menu_createNestAccountAssociation:
                doCreateThermostatAccountAssociation(BASThermostat.ThermostatTypeId.NEST);
                return true;
            case R.id.menu_createEcobeeAccountAssociation:
                doCreateThermostatAccountAssociation(BASThermostat.ThermostatTypeId.ECOBEE);
                return true;
            case R.id.menu_deleteNestAccountAssociation:
                doDeleteThermostatAccountAssociation(BASThermostat.ThermostatTypeId.NEST);
                return true;
            case R.id.menu_deleteEcobeeAccountAssociation:
                doDeleteThermostatAccountAssociation(BASThermostat.ThermostatTypeId.ECOBEE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doGetThermostatTypes(BASAuthInfo myBasAuthInfo) {
        BASThermostat thermostatTypes = new BASThermostat();
        mBigAssCloudApiInterface.getUserInfo("token");
        thermostatTypes.getThermostatTypes(myBasAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                //you have to know what the object "should" be to do your cast
                if (response.body() != null)
                    myThermostatTypes = (BASThermostatTypes) response.body();
                updateTextViewFromResponse(response);
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    //Deprecated?
    private void doGetUserDevices(BASAuthInfo myBasAuthInfo) {
        BASUser userDevices = new BASUser();

        userDevices.getExistingUserDevices(new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doCreateThermostatAccountAssociation(BASThermostat.ThermostatTypeId myThermostatId) {
        BASUser userDevices = new BASUser();

        userDevices.createExistingUserThermostatAccountAssociation(myThermostatId,
                new BASCloudTask.CloudAsyncResponse() {

                    @Override
                    public void onCloudResponse(Response response) {
                        updateTextViewFromResponse(response);
                    }

                    @Override
                    public void onCloudError(CloudMessage message) {
                        updateTextViewFromError(message);
                    }
                });
    }

    private void doDeleteThermostatAccountAssociation(BASThermostat.ThermostatTypeId myThermostatId) {
        BASUser userDevices = new BASUser();
        String myToken = ""; //= getThermostatTokenFromThermostatEnum();

        userDevices.deleteExistingUserThermostatAccountAssociation(myToken,
                new BASCloudTask.CloudAsyncResponse() {

                    @Override
                    public void onCloudResponse(Response response) {
                        updateTextViewFromResponse(response);
                    }

                    @Override
                    public void onCloudError(CloudMessage message) {
                        updateTextViewFromError(message);
                    }
                });
    }

    private void doGetUserThermostats(BASAuthInfo myBasAuthInfo) {
        BASUser userDevices = new BASUser();

        userDevices.getExistingUserThermostats(new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doGetUserThermostatsLinkedDevices(BASAuthInfo myBasAuthInfo) {
        BASUser userDevices = new BASUser();

        userDevices.getExistingUserThermostatsLinkedDevices(new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doGetFirmwareDownloadToken() {
        BASAuth userAuth = new BASAuth();

        userAuth.getFirmwareDownloadToken(basNewUserAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myToken = (BASAccessToken) response.body();
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doGetFirmwareDownloadUrlFromToken() {
        BASFirmware getFirmware = new BASFirmware();

        getFirmware.getFirmwareDownloadUrlFromToken(basNewUserAuthInfo,
                "FW000003",
                new BASCloudTask.CloudAsyncResponse() {

                    @Override
                    public void onCloudResponse(Response response) {
                        updateTextViewFromResponse(response);
                    }

                    @Override
                    public void onCloudError(CloudMessage message) {
                        updateTextViewFromError(message);
                    }
                });
    }

    private void doGetPasswordResetToken() {
        BASAuth userAuth = new BASAuth();

        userAuth.getResetPasswordToken(basNewUserAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myToken = (BASAccessToken) response.body();
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doGetPasswordResetEmail() {
        BASUser userPasswordReset = new BASUser();

        if (basNewUserInfo.getUser() == null) {
            Toast.makeText(MainActivity.this, "Need to get user info first", Toast.LENGTH_LONG).show();
            return;
        }

        userPasswordReset.getResetPasswordEmail(basNewUserInfo.getUser().getEmail(),
                new BASCloudTask.CloudAsyncResponse() {

                    @Override
                    public void onCloudResponse(Response response) {
                        updateTextViewFromResponse(response);
                    }

                    @Override
                    public void onCloudError(CloudMessage message) {
                        updateTextViewFromError(message);
                    }
                });
    }

    private void doResetPasswordFromResetToken() {
        BASUser userPasswordReset = new BASUser();

        if (basNewUserInfo.getUser() == null) {
            Toast.makeText(MainActivity.this, "Need to get user info first", Toast.LENGTH_LONG).show();
            return;
        }

        userPasswordReset.resetUserPasswordFromResetToken(basNewUserAuthInfo,
                new BASCloudTask.CloudAsyncResponse() {

                    @Override
                    public void onCloudResponse(Response response) {
                        updateTextViewFromResponse(response);
                    }

                    @Override
                    public void onCloudError(CloudMessage message) {
                        updateTextViewFromError(message);
                    }
                });
    }

    private void doGetNewPinEmail() {
        BASUser userNewPin = new BASUser();

        if (basNewUserInfo.getUser() == null) {
            Toast.makeText(MainActivity.this, "Need to get user info first", Toast.LENGTH_LONG).show();
            return;
        }

        userNewPin.getNewPinEmail(basNewUserInfo.getUser().getEmail(), new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doDeleteUser() {
        BASUser userDelete = new BASUser();

        if (basNewUserAuthInfo == null) {
            Toast.makeText(MainActivity.this, "Create user first", Toast.LENGTH_LONG).show();
            return;
        }

        if (basNewUserInfo.getUser() == null) {
            Toast.makeText(MainActivity.this, "Need to get user info first", Toast.LENGTH_LONG).show();
            return;
        }

        userDelete.deleteExistingUser(basNewUserInfo.getUser().getId(), new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doCreateUserToken() {
        BASAuth userAuth = new BASAuth();

        userAuth.getCreateUserToken(basNewUserAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myToken = (BASAccessToken) response.body();
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doCreateUserFromToken() {
        BASUser userCreate = new BASUser();

        userCreate.createUserFromToken(basNewUserAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) basNewUserInfo = (BASUserInfo) response.body();
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doConfirmUserFromPin() {
        BASUser userConfirm = new BASUser();

        userConfirm.confirmUserFromPin(basNewUserAuthInfo,
//                basNewUserInfo.getUser().getPin(),
                mPinFromEmail,
                new BASCloudTask.CloudAsyncResponse() {

                    @Override
                    public void onCloudResponse(Response response) {
                        updateTextViewFromResponse(response);
                    }

                    @Override
                    public void onCloudError(CloudMessage message) {
                        updateTextViewFromError(message);
                    }
                });
    }

    private void doNewAuth(BASAuthInfo basAuthInfo) {
        BASAuth userAuth = new BASAuth();

        userAuth.loginExistingUser(basAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myToken = (BASAccessToken) response.body();
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doNewUserInfo(BASAuthInfo myBasAuthInfo) {
        BASUser userRead = new BASUser();

        userRead.getExistingUserInfo(new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
//                Toast.makeText(MainActivity.this, "got it", Toast.LENGTH_SHORT).show();
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) basNewUserInfo = (BASUserInfo) response.body();
            }

            @Override
            public void onCloudError(CloudMessage message) {
                updateTextViewFromError(message);
            }
        });
    }

    private void doUpdateUserInfo(BASAuthInfo authInfo) {
        BASUser userUpdate = new BASUser();
        Boolean bError = false;

        if (basNewUserInfo == null) {
            bError = true;
        }
        else if (basNewUserInfo.getUser() == null) {
            bError = true;
        }
        else if (basNewUserInfo.getUser().getId() == null) {
            bError = true;
        }

        if (bError) {
            Toast.makeText(MainActivity.this, "User info is null.  Get user info first.", Toast.LENGTH_SHORT).show();
            return;
        }


        // this will allow us to just keep switching the name back and forth
        if (authInfo.first_name == "Ricky") {
            authInfo.first_name = "Richard";
            authInfo.last_name = "Robert";
        }
        else {
            authInfo.first_name = "Ricky";
            authInfo.last_name = "Bobby";
        }

        userUpdate.updateExistingUser(authInfo,
                basNewUserInfo.getUser().getId(),   //the id of the user we just created
                new BASCloudTask.CloudAsyncResponse() {

                    @Override
                    public void onCloudResponse(Response response) {
                        updateTextViewFromResponse(response);
                        //you have to know what the object "should" be to do your cast
                        if (response.body() != null) basNewUserInfo = (BASUserInfo) response.body();
                    }

                    @Override
                    public void onCloudError(CloudMessage message) {
                        updateTextViewFromError(message);
                    }
                });
    }

    private void doNestLogin() {
        Intent intent = new Intent(this, NestAuthActivity.class);
        startActivityForResult(intent, NEST_LOGIN_ACTIVITY);
        //when we exit the activity we should have the nest access token to add it to the BAS account
    }

    private void doEcobeeLogin() {
        Intent intent = new Intent(this, EcobeeAuthActivity.class);
        startActivityForResult(intent, ECOBEE_LOGIN_ACTIVITY);
        //when we exit the activity we should have the ecobee access token to add it to the BAS account
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case NEST_LOGIN_ACTIVITY:
                if (myNestAuthInfo.authCode != "")
                {
                    Toast.makeText(MainActivity.this, "Nest access token=" + myNestAuthInfo.authCode, Toast.LENGTH_LONG).show();
                    updateTextView(successString + mBaseURL, "authCode=" + myNestAuthInfo.authCode);
                }
                else {
                    Toast.makeText(MainActivity.this, "Failed to get Nest access token", Toast.LENGTH_LONG).show();
                    updateTextView(failureString + mBaseURL, "authCode=" + myNestAuthInfo.authCode);
                }
                break;
            case ECOBEE_LOGIN_ACTIVITY:
                if (myEcobeeAuthInfo.authCode != "")
                {
                    Toast.makeText(MainActivity.this, "ecobee access token=" + myEcobeeAuthInfo.authCode, Toast.LENGTH_LONG).show();
                    updateTextView(successString + mBaseURL, "authCode=" + myEcobeeAuthInfo.authCode);
                }
                else {
                    Toast.makeText(MainActivity.this, "Failed to get ecobee access token", Toast.LENGTH_LONG).show();
                    updateTextView(failureString + mBaseURL, "authCode=" + myEcobeeAuthInfo.authCode);
                }
                break;
            default:
                Toast.makeText(MainActivity.this, "Unknown login processed in switch statement", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void updateTitle(String title) {
        TextView myTitle = (TextView) findViewById(R.id.textTitle);

        myTitle.setText(title);
    }

    private void updateTextView(String text, String text2) {
        TextView myTitle = (TextView) findViewById(R.id.textView2);
        TextView myTextView = (TextView) findViewById(R.id.textView);

        myTitle.setText(text);
        myTextView.setText(text2);
    }

    private void updateTextViewFromResponse(Response response) {
        if (response.body() != null && response.isSuccessful())
        {
            updateTextView(successString + mBaseURL, response.body().toString());
        }
        else {
            updateTextView(returncodeString + mBaseURL, response.message() + response.errorBody());
        }
    }

    private void updateTextViewFromError(CloudMessage message) {
        updateTextView(failureString + mBaseURL, message.toString());
    }

    private void setPinInput(String myPin) {
        try {
            mPinFromEmail = Integer.parseInt(myPin);
            doConfirmUserFromPin();
        } catch (NumberFormatException nfe) {
            Toast.makeText(MainActivity.this, "Pin was not a number.", Toast.LENGTH_LONG).show();
        }
    }

    private void showPinDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        final View pinView = inflater.inflate(R.layout.enter_pin, null);
        final EditText pinInput = (EditText) pinView.findViewById(R.id.pinFromEmail);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Enter PIN");
        builder.setView(pinView);
        builder.setCancelable(false);

        builder.setPositiveButton(
                "Save",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String pinValue;
                        pinValue = pinInput.getText().toString();
                        setPinInput(pinValue);
                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setUserPasswordInput(LoginType myLoginType, String myUser, String myPassword) {
        doConfirmUserFromPin();
        switch (myLoginType) {
            case BASUSER: {
                break;
            }
            // Nest doesn't log in this way...use NestAuthActivity
//            case NESTUSER: {
//                break;
//            }
            case ECOBEEUSER: {
                break;
            }
            default: {
                break;
            }
        }
    }

    private void showUserLoginDialog(final LoginType myLoginType) {

        LayoutInflater inflater = LayoutInflater.from(this);
        final View pinView = inflater.inflate(R.layout.enter_user_password, null);
        final EditText userInput = (EditText) pinView.findViewById(R.id.usernameText);
        final EditText passwordInput = (EditText) pinView.findViewById(R.id.passwordText);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Enter PIN");
        builder.setView(pinView);
        builder.setCancelable(false);

        builder.setPositiveButton(
                "Save",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setUserPasswordInput(myLoginType,
                                userInput.getText().toString(),
                                passwordInput.getText().toString());
                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}