package daluobo.cnbetamobile.feature.article;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import daluobo.cnbetamobile.base.arch.Resource;
import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.data.repository.ArticleRepository;

public class ArticleViewModel extends ViewModel {

    public LiveData<Resource<Article>> getArticle(int articleId) {
        return ArticleRepository.getArticle(articleId);
    }
}
