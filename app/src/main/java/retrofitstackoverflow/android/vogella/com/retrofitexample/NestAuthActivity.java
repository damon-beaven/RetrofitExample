package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.NestAuthInfo;

public class NestAuthActivity extends Activity {

    private NestAuthInfo nestAuthInfo = NestAuthInfo.getInstance(); //singleton so we can share auth token
    private WebView webview;

    protected void finishActivity() {
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_auth);
        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (nestAuthInfo.authCode == "") {

            // need to get access token with OAuth2.0
            //...
            // set up webview for OAuth2 login
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if ( url.startsWith(nestAuthInfo.redirectUrl) ) {

                        // extract OAuth2 access_token appended in url
                        if ( url.indexOf("code=") != -1 ) {
                            Uri myUri = Uri.parse(url);
                            String stateToken = myUri.getQueryParameter("state");
                            String authCode = myUri.getQueryParameter("code");
                            // here I could do some checking on state and code
                            // to make sure they are valid and that auth was granted
                            nestAuthInfo.authCode = authCode;

//                            nestAuthInfo.authCode = mExtractToken(url);

//                            // store in default SharedPreferences
//                            Editor e = getPreferences(Context.MODE_PRIVATE).edit();
//                            e.putString(SHPREF_KEY_ACCESS_TOKEN, authCode);
//                            e.commit();
//
//                            // spawn worker thread to do api calls to get list of contacts to display
//                            new MyWebservicesAsyncTask().execute(authCode);
                            finishActivity();
                        }

                        // don't go to redirectUri
                        return true;
                    }

                    // load the webpage from url: login and grant access
                    return super.shouldOverrideUrlLoading(view, url); // return false;
                }
            });

            // do OAuth2 login
            String authorizationUri = nestAuthInfo.loginUrl;
            webview.loadUrl(authorizationUri);

        }
        else {
            // we already have access token just exit
            finishActivity();
        }
    }
}
