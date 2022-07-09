package api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class serverAPI {
    private static final String baseURL = "http://kfrent.atwebpages.com/";
    private static Retrofit retrofit = null;

    public static InterfaceAPI getSelectAPI() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(InterfaceAPI.class);
    }


}