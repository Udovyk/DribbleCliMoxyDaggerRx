package udovyk.dribbleclimoxydaggerrx.manager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import udovyk.dribbleclimoxydaggerrx.common.ApiConstants;
import udovyk.dribbleclimoxydaggerrx.common.ShotsRequestConstants;
import udovyk.dribbleclimoxydaggerrx.network.ApiService;
import udovyk.dribbleclimoxydaggerrx.network.model.Attachment;
import udovyk.dribbleclimoxydaggerrx.network.model.OAuthToken;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;
import udovyk.dribbleclimoxydaggerrx.network.model.User;
import udovyk.dribbleclimoxydaggerrx.network.retrofit.RetrofitBuilder;

public class ApiManager {

    private ApiService apiService;

    public ApiManager(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Response<OAuthToken>> getAccessToken(String code) {
        return apiService.getAcccessToken(
                ApiConstants.DRIBBBLE_GET_ACCESS_TOKEN_URL,
                ApiConstants.DRIBBBLE_CLIENT_ID,
                ApiConstants.DRIBBBLE_CLIENT_SECRET,
                code,
                ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI)
                .compose(applyTransformers());
    }

    public Observable<Response<User>> getUserInfo() {
        return apiService
                .getAuthUser()
                .compose(applyTransformers());
    }

    public Observable<List<Shot>> callForShots(int currentPage, String sortValue) {
        return apiService
                .fetchShotsPerPageSort(currentPage, ShotsRequestConstants.PER_PAGE, ShotsRequestConstants.LIST_BY_DEFAULT, sortValue)
                .compose(applyTransformers());
    }

    public Observable<List<Attachment>> callForShotsAttachmetns(int idOfShot) {
        return apiService
                .getShotAttachments(idOfShot)
                .compose(applyTransformers());
    }

    private <T> ObservableTransformer<T, T> applyTransformers() {
        return upstream -> upstream.compose(applySchedulers());
    }

    private ObservableTransformer applySchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
