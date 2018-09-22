package daluobo.cnbetamobile.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import daluobo.cnbetamobile.base.arch.ApiResponse;
import daluobo.cnbetamobile.base.arch.NetworkResource;
import daluobo.cnbetamobile.base.arch.Resource;
import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.data.remote.CbServiceImpl;
import daluobo.cnbetamobile.helper.HtmlHelper;

public class ArticleRepository extends BaseRepository {

    public static LiveData<Resource<List<Article>>> getArticlePage(final int page) {
        return new NetworkResource<List<Article>, String>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<String>> createCall() {
                return CbServiceImpl.getService().getArticlePage(page);
            }

            @Override
            protected List<Article> convertResult(@NonNull String item) {
                return HtmlHelper.getArticleList(item);
            }
        }.getAsLiveData();
    }

    public static LiveData<Resource<Article>> getArticle(final int articleId) {
        return new NetworkResource<Article, String>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<String>> createCall() {
                return CbServiceImpl.getService().getArticle(articleId);
            }

            @Override
            protected Article convertResult(@NonNull String item) {
                return HtmlHelper.getArticle(item);
            }
        }.getAsLiveData();
    }
}
