package daluobo.cnbetamobile.feature.home;

import android.arch.lifecycle.LiveData;

import java.util.List;

import daluobo.cnbetamobile.base.arch.Resource;
import daluobo.cnbetamobile.base.view.BasePageViewModel;
import daluobo.cnbetamobile.data.local.Comment;
import daluobo.cnbetamobile.data.repository.CommentRepository;

public class CommentShowViewModel extends BasePageViewModel<Comment>{

    @Override
    public LiveData<Resource<List<Comment>>> loadPage(int page) {
        return CommentRepository.getCommentShowPage(page);
    }
}
