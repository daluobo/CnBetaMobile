package daluobo.cnbetamobile.helper;

import android.app.Activity;
import android.os.Handler;
import android.webkit.JavascriptInterface;

import daluobo.cnbetamobile.base.util.NetworkUtil;


public class JsHelper {
    private static final String TAG = "JsInterface";
    private NetworkUtil mNetworkUtil;
    private Activity mActivity;


    public JsHelper(Activity activity, NetworkUtil networkUtil) {
        mActivity = activity;
        mNetworkUtil = networkUtil;
    }


    @JavascriptInterface
    public boolean isAutoLoadImage() {
        return !mNetworkUtil.isNetworkOn() || mNetworkUtil.isWifiOn();
    }

    @JavascriptInterface
    public void openImageInActivity(final String pos, final String[] srcs) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
//                int curPos = Integer.parseInt(pos);
//                Intent intent = new Intent(mActivity, ImageActivity.class);
//                intent.putExtra(ImageActivity.CUR_POS, pos);
//                intent.putExtra(ImageActivity.IMG_URLS, srcs);
//                mActivity.startActivity(intent);
            }
        });

    }

    @JavascriptInterface
    public void showMessage(String message) {

    }
}