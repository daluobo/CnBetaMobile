package daluobo.cnbetamobile.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import daluobo.cnbetamobile.base.arch.ApiResponse;
import daluobo.cnbetamobile.base.arch.NetworkResource;
import daluobo.cnbetamobile.base.arch.Resource;
import daluobo.cnbetamobile.data.local.Comment;
import daluobo.cnbetamobile.data.remote.CbServiceImpl;
import daluobo.cnbetamobile.helper.HtmlHelper;

public class CommentRepository extends BaseRepository {

    public static LiveData<Resource<List<Comment>>> getCommentShowPage(final int page) {
        return new NetworkResource<List<Comment>, String>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<String>> createCall() {
                return CbServiceImpl.getService().commentShow(page);
            }

            @Override
            protected List<Comment> convertResult(@NonNull String item) {
                return HtmlHelper.getCommentShow(item);
            }
        }.getAsLiveData();
    }

    public static LiveData<Resource<List<Comment>>> getCommentPage(final int articleId, final int page) {
        return new NetworkResource<List<Comment>, String>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<String>> createCall() {
                return CbServiceImpl.getService().comment(articleId, page);
            }

            @Override
            protected List<Comment> convertResult(@NonNull String item) {
                return HtmlHelper.getComment(item);
            }
        }.getAsLiveData();
    }
}
