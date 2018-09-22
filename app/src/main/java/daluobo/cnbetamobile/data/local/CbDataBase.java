package daluobo.cnbetamobile.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import daluobo.cnbetamobile.data.local.dao.ArticleDao;
import daluobo.cnbetamobile.data.local.dao.CommentDao;


/**
 * Created by daluobo on 2017/8/30.
 */

@Database(entities = {Article.class, Comment.class}, version = 1, exportSchema = false)
public abstract class CbDataBase extends RoomDatabase {
    private static CbDataBase sInstance;

    public static CbDataBase getDatabase(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), CbDataBase.class,
                    "cb.db").build();
        }
        return sInstance;
    }

    public static void onDestroy() {
        sInstance = null;
    }

    public abstract ArticleDao articleDao();

    public abstract CommentDao commentDao();
}
