package daluobo.cnbetamobile.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import daluobo.cnbetamobile.data.local.Article;


/**
 * Created by daluobo on 2017/8/30.
 */
@Dao
public interface ArticleDao {
    @Query("SELECT * FROM article")
    LiveData<List<Article>> getArticleList();

    @Query("SELECT * FROM article WHERE id = :id")
    LiveData<Article> getById(int id);

    @Query("SELECT COUNT (id) FROM article")
    Long count();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addArticle(Article article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<Article> articles);

    @Delete()
    void deleteArticle(Article article);

    @Delete()
    void deleteAll(List<Article> articles);
}
