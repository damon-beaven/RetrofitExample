package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASCloudTask;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASUserAuth;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASUserCreate;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASUserDelete;
import retrofitstackoverflow.android.vogella.com.retrofitexample.cloud.BASUserRead;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;
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
        basNewUserAuthInfo.email = "rickyfirst@notlast.com";
        basNewUserAuthInfo.password = "qwertyui";
        basNewUserAuthInfo.first_name = "Ricky";
        basNewUserAuthInfo.last_name = "Bobby";
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_auth:
                doNewAuth(basAuthInfo);
                return true;
            case R.id.menu_userInfo:
                doNewUserInfo(basAuthInfo);
                return true;
            case R.id.menu_userDevices:
                Toast.makeText(MainActivity.this, "not implemented", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_getCreateUserToken:
                doCreateUserToken();
                return true;
            case R.id.menu_authNewUser:
                doNewAuth(basNewUserAuthInfo);
                return true;
            case R.id.menu_createUserFromToken:
                doCreateUserFromToken();
                return true;
            case R.id.menu_getNewUserInfo:
                doNewUserInfo(basNewUserAuthInfo);
                return true;
            case R.id.menu_deleteUser:
                doDeleteUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doDeleteUser() {
        BASUserDelete userDelete = new BASUserDelete();

        if (basNewUserAuthInfo == null) {
            Toast.makeText(MainActivity.this, "Create user first", Toast.LENGTH_LONG).show();
            return;
        }

        userDelete.deleteExistingUser(basNewUserInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
//                if (response.body() != null) myToken = (CloudMessage) response.body();
            }
        });
    }

    private void doCreateUserToken() {
        BASUserAuth userAuth = new BASUserAuth();
//        mBaseURL = userAuth.getBaseURL();

        userAuth.getCreateUserToken(basNewUserAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myToken = (BASAccessToken) response.body();
            }
        });
    }

    private void doCreateUserFromToken() {
        BASUserCreate userCreate = new BASUserCreate();

        userCreate.createUserFromToken(basNewUserAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) basNewUserInfo = (BASUserInfo) response.body();
            }
        });
    }

    private void doNewAuth(BASAuthInfo basAuthInfo) {
        BASUserAuth userAuth = new BASUserAuth();
        mBaseURL = userAuth.getBaseURL();

        userAuth.loginExistingUser(basAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
//                Toast.makeText(MainActivity.this, "got it", Toast.LENGTH_SHORT).show();
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myToken = (BASAccessToken) response.body();
            }
        });
    }

    private void doNewUserInfo(BASAuthInfo myBasAuthInfo) {
        BASUserRead userRead = new BASUserRead();
        mBaseURL = userRead.getBaseURL();

        userRead.getExistingUserInfo(myBasAuthInfo, new BASCloudTask.CloudAsyncResponse() {

            @Override
            public void onCloudResponse(Response response) {
//                Toast.makeText(MainActivity.this, "got it", Toast.LENGTH_SHORT).show();
                updateTextViewFromResponse(response);
                //you have to know what the object "should" be to do your cast
                if (response.body() != null) myUserInfo = (BASUserInfo) response.body();
            }
        });
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
//            updateTextView(returncodeString + mBaseURL, response.errorBody().toString());
        }
    }
}