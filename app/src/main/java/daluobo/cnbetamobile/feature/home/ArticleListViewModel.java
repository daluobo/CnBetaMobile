package daluobo.cnbetamobile.feature.home;

import android.arch.lifecycle.LiveData;

import java.util.List;

import daluobo.cnbetamobile.base.arch.Resource;
import daluobo.cnbetamobile.base.view.BasePageViewModel;
import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.data.repository.ArticleRepository;

public class ArticleListViewModel extends BasePageViewModel<Article>{
    @Override
    public LiveData<Resource<List<Article>>> loadPage(int page) {
        return ArticleRepository.getArticlePage(page);
    }
}
