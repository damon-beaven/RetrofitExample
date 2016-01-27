package retrofitstackoverflow.android.vogella.com.retrofitexample.cloud;

import android.os.AsyncTask;

import retrofit.Response;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAccessToken;
import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.BASAuthInfo;

/**
 * Created by dbeaven on 1/27/2016.
 */

    //  Implementation in the parent class should look something like this
    //  NOTE:  "implements AsyncResponse"
    //         "processFinish()..."
    //          Call the task normally with ".execute();"
//public class MainActivity implements AsyncResponse{
//
//    BASCloudTash asyncTask =new BASCloudTask(new AsyncResponse(){
//
//        @Override
//        void processFinish(String output){
//            //Here you will receive the result fired from async class
//            //of onPostExecute(result) method.
//        }
//    }).execute();
//
//}

public abstract class BASCloudTask { // extends AsyncTask<Object, Integer, Object> {

    public String getBaseURL() {
        return mBaseURL;
    }

    public BASAuthInfo getAuthInfo() {
        return mAuthInfo;
    }

    //    private String mBaseURL = "https://api.bigassfans.com";  // production
    protected String mBaseURL = "https://dev.sensemecloud.com"; // dev
    protected static BASAccessToken mToken = new BASAccessToken();
    protected BASAuthInfo mAuthInfo = new BASAuthInfo();

    public CloudAsyncResponse mDelegate = null;

    /**
     * Constructors
     */
    protected BASCloudTask() {
    }

//    protected BASCloudTask(CloudAsyncResponse mDelegate){
//        this.mDelegate = mDelegate;
//    }

    // This is how the caller will get the result of the operation
    // The calling class should "implements" CloudAsyncResponse and  "@Override" onResponse() will
    // be called when the process is done.  Hence, the parent gets the "result"
    public interface CloudAsyncResponse {
        void onCloudResponse(Response response);
    }

//    public abstract void onResponse(Response response);

//    @Override
//    protected void onCloudResponse(Response response) {
//        // callback to the parent class to send the result
//        mDelegate.onCloudResponse(response);
//    }
}
