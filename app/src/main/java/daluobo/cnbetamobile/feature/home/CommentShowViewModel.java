package daluobo.cnbetamobile.feature.home;

import android.arch.lifecycle.LiveData;

import java.util.List;

import daluobo.cnbetamobile.base.arch.Resource;
import daluobo.cnbetamobile.base.view.BasePageViewModel;
import daluobo.cnbetamobile.data.local.Comment;

public class CommentShowViewModel extends BasePageViewModel<Comment>{
    @Override
    public LiveData<Resource<List<Comment>>> loadPage(int page) {
        return null;
    }
}
