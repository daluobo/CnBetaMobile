package daluobo.cnbetamobile.feature.article;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import daluobo.cnbetamobile.AppConstant;
import daluobo.cnbetamobile.R;
import daluobo.cnbetamobile.base.arch.Resource;
import daluobo.cnbetamobile.base.arch.ResourceObserver;
import daluobo.cnbetamobile.base.util.NetworkUtil;
import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.helper.JsHelper;


public class ArticleFragment extends Fragment {
    private static final String ARG_ARTICLE_ID = "article_id";

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private int mArticleId;
    private JsHelper mJsHelper;
    private NetworkUtil mNetworkUtil;
    private ArticleViewModel mViewModel;

    public ArticleFragment() {
    }

    public static ArticleFragment newInstance(int mArticleId) {
        ArticleFragment fragment = new ArticleFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_ARTICLE_ID, mArticleId);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);

        initData();
        initView();
        initContent(mArticleId);

        return view;
    }

    private void initData() {
        mArticleId = getArguments().getInt(ARG_ARTICLE_ID);
        mNetworkUtil = new NetworkUtil(getActivity().getApplication());
        mViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }

    private void initView() {
        WebSettings webSettings = mWebView.getSettings();
        if (mNetworkUtil.isNetworkOn()) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF -8");

        mWebView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorCbGrey));
        mWebView.addJavascriptInterface(mJsHelper, "Android");
    }

    private void initContent(int articleId) {
        mViewModel.getArticle(articleId).observe(this, new ResourceObserver<Resource<Article>, Article>(getContext()) {
            @Override
            protected void onSuccess(Article article) {
                mProgressBar.setVisibility(View.GONE);
                mWebView.loadDataWithBaseURL(AppConstant.ENDPOINT, article.content, "text/html", "UTF-8", null);
            }
        });
    }

}
