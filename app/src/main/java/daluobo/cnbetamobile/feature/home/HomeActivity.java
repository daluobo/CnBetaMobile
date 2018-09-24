package daluobo.cnbetamobile.feature.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import daluobo.cnbetamobile.R;
import daluobo.cnbetamobile.base.view.SimplePageAdapter;

public class HomeActivity extends AppCompatActivity {
    private List<Fragment> mFragments = new ArrayList<>();
    private SimplePageAdapter mAdapter;
    protected MenuItem mArticleMenu;
    protected MenuItem mCommentMenu;

    @BindDrawable(R.drawable.ic_dashboard)
    Drawable mIcDashboard;
    @BindDrawable(R.drawable.ic_dashboard_light_blue)
    Drawable mIcDashboardBlue;
    @BindDrawable(R.drawable.ic_whatshot)
    Drawable mIcWhatHot;
    @BindDrawable(R.drawable.ic_whatshot_light_blue)
    Drawable mIcWhatHotBlue;

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
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mArticleMenu.setIcon(mIcDashboard);
                        mCommentMenu.setIcon(mIcWhatHotBlue);
                        break;
                    case 1:
                        mArticleMenu.setIcon(mIcDashboardBlue);
                        mCommentMenu.setIcon(mIcWhatHot);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        mArticleMenu = menu.findItem(R.id.menu_article);
        mArticleMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mArticleMenu.setIcon(mIcDashboard);
                mCommentMenu.setIcon(mIcWhatHotBlue);
                mViewPager.setCurrentItem(0, true);
                return false;
            }
        });
        mCommentMenu = menu.findItem(R.id.menu_comment);
        mCommentMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mArticleMenu.setIcon(mIcDashboardBlue);
                mCommentMenu.setIcon(mIcWhatHot);
                mViewPager.setCurrentItem(1, true);
                return false;
            }
        });

        return true;
    }


}
