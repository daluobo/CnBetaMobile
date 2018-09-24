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
    LiveData<ApiResponse<String>> articlePage(@Path("page") int page);

    @GET("commentshow/p{page}.htm")
    LiveData<ApiResponse<String>> commentShow(@Path("page") int page);

    @GET("/view/{articleId}.htm")
    LiveData<ApiResponse<String>> article(@Path("articleId") int id);

    @GET("/comment/{articleId}.htm")
    LiveData<ApiResponse<String>> comment(@Path("articleId") int id, @Query("page") int page);

}
