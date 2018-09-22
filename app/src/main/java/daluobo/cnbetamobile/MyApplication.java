package daluobo.cnbetamobile;

import android.app.Application;

/**
 * Created by daluobo on 2017/8/25.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initLeakCanary();

    }

    private void initLeakCanary() {
    }
}
