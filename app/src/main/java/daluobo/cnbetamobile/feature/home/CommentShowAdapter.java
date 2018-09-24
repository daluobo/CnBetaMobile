package daluobo.cnbetamobile.feature.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daluobo.cnbetamobile.R;
import daluobo.cnbetamobile.base.view.LoadableAdapter;
import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.data.local.Comment;
import daluobo.cnbetamobile.helper.Navigator;
import daluobo.cnbetamobile.view.SpannableCommentShowFont;

public class CommentShowAdapter extends LoadableAdapter<Comment> {

    public CommentShowAdapter(Context context, List<Comment> data) {
        super(context, data);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new CommentShowViewHolder(inflateItemView(parent, R.layout.item_comment_show));
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder viewHolder, Comment item, int position) {
        CommentShowViewHolder vh = (CommentShowViewHolder) viewHolder;

        vh.mComment = item;
        vh.mContent.setText(item.content);
        vh.mInfo.setText(SpannableCommentShowFont.changeFont(item.location, item.author, item.article));
    }

    public class CommentShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.container)
        LinearLayout mContainer;
        @BindView(R.id.content)
        TextView mContent;
        @BindView(R.id.info)
        TextView mInfo;

        Comment mComment;

        public CommentShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Article article = new Article();
            article.id = mComment.articleId;
            article.title = mComment.article;

            Navigator.toArticle(mContext, article);
        }
    }
}
