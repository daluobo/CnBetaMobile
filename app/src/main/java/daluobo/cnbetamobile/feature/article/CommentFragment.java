package daluobo.cnbetamobile.feature.article;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;

import java.util.List;

import daluobo.cnbetamobile.base.util.DimensionUtil;
import daluobo.cnbetamobile.base.view.SwipeListFragment;
import daluobo.cnbetamobile.data.local.Comment;
import daluobo.cnbetamobile.view.ItemLineDecoration;

public class CommentFragment extends SwipeListFragment<Comment> {
    public static final String ARG_ARTICLE_ID = "article_id";
    private int mArticleId;
    private OnCommentsGetListener mOnCommentsGetListener;

    public CommentFragment() {
    }

    public static CommentFragment newInstance(int mArticleId) {
        CommentFragment fragment = new CommentFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_ARTICLE_ID, mArticleId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCommentsGetListener) {
            mOnCommentsGetListener = (OnCommentsGetListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnCommentsGetListener");
        }
    }

    @Override
    public void initData() {
        mArticleId = getArguments().getInt(ARG_ARTICLE_ID);

        super.mViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        ((CommentViewModel)super.mViewModel).setArticleId(mArticleId);
        super.mAdapter = new CommentAdapter(getContext(), mViewModel.getData());
    }

    @Override
    public void initView() {
        super.mListView.addItemDecoration(new ItemLineDecoration(super.mColorPrimary, 4, 4, 4, DimensionUtil.dip2px(getContext(), 12)));
        initListView();
        super.onShowRefresh();
        super.onRefresh();
    }

    @Override
    protected void onRefreshSuccess(List<Comment> data) {
        super.onRefreshSuccess(data);

        mOnCommentsGetListener.setCommentNum(data.size());
    }
}