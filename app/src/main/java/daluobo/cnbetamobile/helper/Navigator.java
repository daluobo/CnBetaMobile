package daluobo.cnbetamobile.helper;

import android.content.Context;
import android.content.Intent;

import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.feature.article.ArticleActivity;

public class Navigator {
    public static void toArticle(Context context, Article id) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(ArticleActivity.ARG_ARTICLE, id);
        context.startActivity(intent);
    }
}
