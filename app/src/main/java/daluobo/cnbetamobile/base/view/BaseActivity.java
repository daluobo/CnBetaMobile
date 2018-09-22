package daluobo.cnbetamobile.base.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by daluobo on 2017/11/1.
 */

public abstract class BaseActivity extends AppCompatActivity implements IContentView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void initContent() {

    }

}
