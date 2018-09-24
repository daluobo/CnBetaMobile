package daluobo.cnbetamobile.feature.article;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daluobo.cnbetamobile.R;
import daluobo.cnbetamobile.base.view.LoadableAdapter;
import daluobo.cnbetamobile.data.local.Comment;

public class CommentAdapter extends LoadableAdapter<Comment> {

    public CommentAdapter(Context context, List<Comment> data) {
        super(context, data);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(inflateItemView(parent, R.layout.item_comment));
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder viewHolder, Comment item, int position) {
        CommentViewHolder vh = (CommentViewHolder) viewHolder;

        vh.mUserName.setText(item.userName);
        vh.mTime.setText(item.time);
        vh.mContent.setText(item.content);
        vh.mUp.setText(item.up);
        vh.mDown.setText(item.down);

        if (item.conBox.length() > 0) {
            vh.mConBox.setText(item.conBox);
            vh.mConBox.setVisibility(View.VISIBLE);
        } else {
            vh.mConBox.setVisibility(View.GONE);
        }
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_name)
        TextView mUserName;
        @BindView(R.id.time)
        TextView mTime;
        @BindView(R.id.con_box)
        TextView mConBox;
        @BindView(R.id.content)
        TextView mContent;
        @BindView(R.id.up)
        TextView mUp;
        @BindView(R.id.down)
        TextView mDown;


        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
