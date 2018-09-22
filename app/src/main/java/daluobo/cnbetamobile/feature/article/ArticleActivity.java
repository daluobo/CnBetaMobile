package daluobo.cnbetamobile.feature.article;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daluobo.cnbetamobile.R;
import daluobo.cnbetamobile.data.local.Article;

public class ArticleActivity extends AppCompatActivity {
    public static final String ARG_ARTICLE = "article";

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mFragmentAdapter;
    private Article mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    protected void initData() {
        mArticle = getIntent().getParcelableExtra(ARG_ARTICLE);
    }

    protected void initView() {
        mTitle.setText(mArticle.title);
        setSupportActionBar(mToolbar);

        mFragments.add(ArticleFragment.newInstance(mArticle.id));
        mFragments.add(ArticleFragment.newInstance(mArticle.id));

        mFragmentAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mViewPager.setAdapter(mFragmentAdapter);



    }
}
