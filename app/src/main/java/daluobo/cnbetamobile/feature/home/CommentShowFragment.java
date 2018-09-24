package daluobo.cnbetamobile.feature.home;

import android.arch.lifecycle.ViewModelProviders;

import daluobo.cnbetamobile.base.util.DimensionUtil;
import daluobo.cnbetamobile.base.view.SwipeListFragment;
import daluobo.cnbetamobile.data.local.Comment;
import daluobo.cnbetamobile.view.ItemLineDecoration;

/**
 * Created by daluobo on 2017/8/22.
 */

public class CommentShowFragment extends SwipeListFragment<Comment> {

    public CommentShowFragment() {
    }

    public static CommentShowFragment newInstance() {
        CommentShowFragment fragment = new CommentShowFragment();
        return fragment;
    }

    @Override
    public void initData() {
        super.mViewModel = ViewModelProviders.of(this).get(CommentShowViewModel.class);
        super.mAdapter = new CommentShowAdapter(getContext(), mViewModel.getData());
    }

    @Override
    public void initView() {
        super.mListView.addItemDecoration(new ItemLineDecoration(super.mColorPrimary, 4, 4, 4, DimensionUtil.dip2px(getContext(), 12)));
        initListView();
        super.onShowRefresh();
        super.onRefresh();
    }
}
