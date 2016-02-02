package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASAuth;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASFirmware;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASThermostat;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASUser;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASThermostatTypes;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserConfirmInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASUserInfo;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.CloudMessage;

public class MainActivity extends Activity {
    private String mBaseURL;
    private String successString = "Success...>> ";
    private String failureString = "Failure...>> ";
    private String returncodeString = "Return code...>> ";
    protected BASAccessToken myToken = new BASAccessToken();
    protected BASUserInfo myUserInfo = new BASUserInfo();

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
        mBaseURL = tempUserAuth.getBaseURL();

        setContentView(R.layout.activity_main);
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
                doConfirmUserFromPin();
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
            case R.id.menu_getFirmwareDownloadToken:
                doGetFirmwareDownloadToken();
                return true;
            case R.id.menu_getFirmwareDownloadUrlFromToken:
                doGetFirmwareDownloadUrlFromToken();
                return true;
            case R.id.menu_getThermostatTypes:
                doGetThermostatTypes(basAuthInfo);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doGetThermostatTypes(BASAuthInfo myBasAuthInfo) {
        BASThermostat thermostatTypes = new BASThermostat();

        thermostatTypes.getThermostatTypes(myBasAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
//                Toast.makeText(MainActivity.this, "got it", Toast.LENGTH_SHORT).show();
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
                basNewUserInfo.getUser().getPin(),
                new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
//                if (response.body() != null) basNewUserInfo = (BASUserConfirmInfo) response.body();
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
        if (response.body() != null && response.isSuccess())
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
}