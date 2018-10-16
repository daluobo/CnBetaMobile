package daluobo.cnbetamobile.feature.article;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import daluobo.cnbetamobile.R;
import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.view.MenuBadgeProvider;

public class ArticleActivity extends AppCompatActivity implements OnCommentsGetListener {
    public static final String ARG_ARTICLE = "article";

    @BindDrawable(R.drawable.ic_description)
    Drawable mIcDescription;
    @BindDrawable(R.drawable.ic_description_light_blue)
    Drawable mIcDescriptionBlue;
    @BindDrawable(R.drawable.ic_chat_bubble)
    Drawable mIcChatBubble;
    @BindDrawable(R.drawable.ic_chat_bubble_light_blue)
    Drawable mIcChatBubbleBlue;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mFragmentAdapter;
    private Article mArticle;
    private MenuItem mContentItem;
    private MenuBadgeProvider mCommentBadge;
    private MenuBadgeProvider.OnClickListener onMenuBadgeClickListener = new MenuBadgeProvider.OnClickListener() {
        @Override
        public void onClick(int index) {
            mContentItem.setIcon(mIcDescriptionBlue);
            mCommentBadge.setIcon(R.drawable.ic_chat_bubble);
            mViewPager.setCurrentItem(index, true);
        }
    };

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
        mFragments.add(CommentFragment.newInstance(mArticle.id));

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
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mContentItem.setIcon(mIcDescription);
                        mCommentBadge.setIcon(R.drawable.ic_chat_bubble_light_blue);
                        break;
                    case 1:
                        mContentItem.setIcon(mIcDescriptionBlue);
                        mCommentBadge.setIcon(R.drawable.ic_chat_bubble);
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
        getMenuInflater().inflate(R.menu.menu_article, menu);

        mContentItem = menu.findItem(R.id.menu_content);
        mContentItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mContentItem.setIcon(mIcDescription);
                mCommentBadge.setIcon(R.drawable.ic_chat_bubble_light_blue);
                mViewPager.setCurrentItem(0, true);
                return false;
            }
        });

        MenuItem commentItem = menu.findItem(R.id.menu_comment);
        mCommentBadge = (MenuBadgeProvider) MenuItemCompat.getActionProvider(commentItem);
        mCommentBadge.setOnClickListener(1, onMenuBadgeClickListener);

        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mCommentBadge.setIcon(R.drawable.ic_chat_bubble_light_blue);
    }

    @Override
    public void setCommentNum(int num) {
        mCommentBadge.setBadge(num);
    }
}
