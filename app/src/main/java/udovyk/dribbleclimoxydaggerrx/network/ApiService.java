package udovyk.dribbleclimoxydaggerrx.network;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.Call;
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
    @FormUrlEncoded
    @POST
    Observable<OAuthToken> getAcccessToken(
            @Url String url,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            @Field("code") String code,
            @Field("redirect_uri") String redirect_uri

    );

    /**
     * The call to refresh a token
     */
    @FormUrlEncoded
    @POST("oauth/token")
    Observable<OAuthToken> refreshAccessToken(
            @Field("refresh_token") String refresh_token,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret
    );

    /**
     * Get shots
     */
    @GET("shots")
    Observable<List<Shot>> fetchShots();

    @GET("shots/:id")
    Observable<Shot> fetchShot(@Query("id") int id);

    @GET("shots")
    Observable<List<Shot>> fetchShotsPerPage(@Query("page") int page, @Query("per_page") int perPage);

    @GET("shots")
    Observable<List<Shot>> fetchShotsPerPage(@Query("page") int page);

    @GET("shots")
    Observable<List<Shot>> fetchShotsPerPageSort(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list, @Query("sort") String sort);

    /*
    * Get authenticated user
    * */
    @GET("user")
    Observable<User> getAuthUser();

    /*
    * Get attachments
    * */
    @GET("shots/{id}/attachments")
    Observable<List<Attachment>> getShotAttachments(@Path("id") long shotId);



}
