package daluobo.cnbetamobile.feature.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daluobo.cnbetamobile.R;
import daluobo.cnbetamobile.base.view.SimplePageAdapter;

public class HomeActivity extends AppCompatActivity {
    private List<Fragment> mFragments = new ArrayList<>();
    private SimplePageAdapter mAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    protected void initData(){
        mFragments.add(ArticleListFragment.newInstance());
        mFragments.add(CommentShowFragment.newInstance());
        mAdapter = new SimplePageAdapter(getSupportFragmentManager(), mFragments);
    }

    protected void initView(){
        mToolbar.setTitle(R.string.home_title);
        mToolbar.setSubtitle(R.string.home_subtitle);
        setSupportActionBar(mToolbar);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
