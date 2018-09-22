package daluobo.cnbetamobile.feature.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import daluobo.cnbetamobile.R;
import daluobo.cnbetamobile.base.view.LoadableAdapter;
import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.helper.Navigator;

public class ArticleListAdapter extends LoadableAdapter<Article> {
    private Context mContext;


    public ArticleListAdapter(Context context, List<Article> data) {
        mContext = context;
        mData = data;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(inflateItemView(parent, R.layout.item_article));
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder viewHolder, Article item, int position) {
        ArticleViewHolder vh = (ArticleViewHolder) viewHolder;


        vh.mArticle = item;
        if (item.isHot) {
            vh.mTitle.setTextColor(vh.mColorCbRed);
        } else {
            vh.mTitle.setTextColor(vh.mColorTitle);
        }
        vh.mTitle.setText(item.title);
        vh.mPostTime.setText(item.post_time);
        vh.mView.setText(item.view);
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.post_time)
        TextView mPostTime;
        @BindView(R.id.view)
        TextView mView;
        @BindView(R.id.container)
        RelativeLayout mContainer;

        @BindColor(R.color.colorCbRed)
        public int mColorCbRed;
        @BindColor(R.color.colorTitle)
        public int mColorTitle;

        Article mArticle;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Navigator.toArticle(mContext, mArticle);
        }
    }
}
