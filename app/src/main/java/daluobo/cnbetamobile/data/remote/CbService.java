package daluobo.cnbetamobile.data.remote;

import android.arch.lifecycle.LiveData;

import daluobo.cnbetamobile.base.arch.ApiResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by daluobo on 2017/8/22.
 */

public interface CbService {

    @GET("/list/latest_{page}.htm")
    LiveData<ApiResponse<String>> getArticlePage(@Path("page") int page);

    @GET("commentshow/p{page}.htm")
    LiveData<ApiResponse<String>> commentShow(@Path("page") int page);

    @GET("/view/{articlId}.htm")
    LiveData<ApiResponse<String>> getArticle(@Path("articlId") int id);

    @GET("/comment/{articlId}.htm")
    LiveData<ApiResponse<String>> getComment(@Path("articlId") int id, @Query("page") int page);

}
