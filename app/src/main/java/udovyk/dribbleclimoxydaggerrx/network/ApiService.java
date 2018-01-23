package udovyk.dribbleclimoxydaggerrx.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import udovyk.dribbleclimoxydaggerrx.network.model.Attachment;
import udovyk.dribbleclimoxydaggerrx.network.model.OAuthToken;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;
import udovyk.dribbleclimoxydaggerrx.network.model.User;

public interface ApiService {

    /**
     * The call to request a token
     */
    @POST
    @FormUrlEncoded
    Observable<Response<OAuthToken>> getAcccessToken(
            @Url String url,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            @Field("code") String code,
            @Field("redirect_uri") String redirect_uri

    );

    /**
     * Get shots
     */
    @GET("shots")
    Observable<Response<List<Shot>>> fetchShots();

    @GET("shots/:id")
    Observable<Response<Shot>> fetchShot(@Query("id") int id);

    @GET("shots")
    Observable<Response<List<Shot>>> fetchShotsPerPage(@Query("page") int page, @Query("per_page") int perPage);

    @GET("shots")
    Observable<Response<List<Shot>>> fetchShotsPerPage(@Query("page") int page);

    @GET("shots")
    Observable<Response<List<Shot>>> fetchShotsPerPageSort(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list, @Query("sort") String sort);

    /*
    * Get authenticated user
    * */
    @GET("user")
    Observable<Response<User>> getAuthUser();

    /*
    * Get attachments
    * */
    @GET("shots/{id}/attachments")
    Observable<Response<List<Attachment>>> getShotAttachments(@Path("id") long shotId);


}
