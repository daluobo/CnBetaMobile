package daluobo.cnbetamobile.feature.article;

import android.arch.lifecycle.LiveData;

import java.util.List;

import daluobo.cnbetamobile.base.arch.Resource;
import daluobo.cnbetamobile.base.view.BasePageViewModel;
import daluobo.cnbetamobile.data.local.Comment;
import daluobo.cnbetamobile.data.repository.CommentRepository;

public class CommentViewModel extends BasePageViewModel<Comment> {
    private int mArticleId;

    @Override
    public LiveData<Resource<List<Comment>>> loadPage(int page) {
        return CommentRepository.getCommentPage(mArticleId, page);
    }

    public int getArticleId() {
        return mArticleId;
    }

    public void setArticleId(int articleId) {
        mArticleId = articleId;
    }
}
