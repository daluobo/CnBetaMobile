package daluobo.cnbetamobile.base.arch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by daluobo on 2016/10/24.
 */

public class StringConverter implements Converter<ResponseBody, String> {
    public static final Factory FACTORY = new Factory() {
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            /*if (type==StringConverter.class)return  new StringConverter();
            else return null;*/
            return new StringConverter();
        }
    };

    @Override
    public String convert(ResponseBody value) throws IOException {
        //values.string 把服务器上请求的数据，转换成string格式
//        String str=convertStream2String(value.byteStream());
        return value.string();
    }

    private String convertStream2String(InputStream in) throws IOException {
        //inputStream转换为String 要进行gbk或者utf-8转码，否则乱码
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
//        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }
}