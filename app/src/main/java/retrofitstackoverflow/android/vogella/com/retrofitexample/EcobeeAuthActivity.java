package retrofitstackoverflow.android.vogella.com.retrofitexample;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import retrofitstackoverflow.android.vogella.com.retrofitexample.pojo.EcobeeAuthInfo;

public class EcobeeAuthActivity extends Activity {

    private EcobeeAuthInfo ecobeeAuthInfo = EcobeeAuthInfo.getInstance(); //singleton so we can share auth token
    private WebView webview;

    protected void finishActivity() {
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecobee_auth);
        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (ecobeeAuthInfo.authCode == "") {

            // need to get access token with OAuth2.0
            //...
            // set up webview for OAuth2 login
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if ( url.startsWith(ecobeeAuthInfo.SENSEME_REDIRECT_URL) ) {

                        // extract OAuth2 access_token appended in url
                        if ( url.indexOf("code=") != -1 ) {
                            Uri myUri = Uri.parse(url);
                            String stateToken = myUri.getQueryParameter("state");
                            String authCode = myUri.getQueryParameter("code");
                            // here I could do some checking on state and code
                            // to make sure they are valid and that auth was granted
                            ecobeeAuthInfo.authCode = authCode;

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
            String authorizationUri = ecobeeAuthInfo.LOGIN_URL;
            webview.loadUrl(authorizationUri);

        }
        else {
            // we already have access token just exit
            finishActivity();
        }
    }
}
