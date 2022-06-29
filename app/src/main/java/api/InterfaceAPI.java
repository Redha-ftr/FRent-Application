package api;

import java.util.List;

import model.GedungModel;
import model.Login;
import model.Register;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfaceAPI {

    @GET("showgedung.php")
    Call<List<GedungModel>> callGedungList();

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("nama") String nama,
            @Field("username") String username,
            @Field("password") String password,
            @Field("telepon") String telepon
    );

}
