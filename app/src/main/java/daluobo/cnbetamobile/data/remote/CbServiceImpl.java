package daluobo.cnbetamobile.data.remote;

import java.util.concurrent.TimeUnit;

import daluobo.cnbetamobile.AppConstant;
import daluobo.cnbetamobile.base.ConfigConstant;
import daluobo.cnbetamobile.base.arch.LiveDataCallAdapterFactory;
import daluobo.cnbetamobile.base.arch.StringConverter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by daluobo on 2017/9/4.
 */

public class CbServiceImpl {

    public static CbService getService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(ConfigConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        OkHttpClient client = httpClientBuilder
                .addInterceptor(loggingInterceptor)
                .build();


        return new Retrofit.Builder()
                .client(client)
                .baseUrl(AppConstant.ENDPOINT)
                .addConverterFactory(StringConverter.FACTORY)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(CbService.class);
    }
}
