package udovyk.dribbleclimoxydaggerrx.manager;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import udovyk.dribbleclimoxydaggerrx.common.ApiConstants;
import udovyk.dribbleclimoxydaggerrx.common.ShotsRequestConstants;
import udovyk.dribbleclimoxydaggerrx.network.ApiService;
import udovyk.dribbleclimoxydaggerrx.network.model.Attachment;
import udovyk.dribbleclimoxydaggerrx.network.model.OAuthToken;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;
import udovyk.dribbleclimoxydaggerrx.network.model.User;
import udovyk.dribbleclimoxydaggerrx.network.retrofit.RetrofitBuilder;

public class ApiManager {

    private Context context;
    private ApiService simpleClient;
    private ApiService authClient;

    public ApiManager() {
        simpleClient = RetrofitBuilder.getSimpleClient();
    }

    public ApiManager(Context context) {
        this.context = context;
        authClient = RetrofitBuilder.getOAuthClient(this.context);
    }


    public Observable<OAuthToken> getAccessToken(String code) {
        Observable<OAuthToken> getRequestTokenFormCall = simpleClient.getAcccessToken(
                ApiConstants.DRIBBBLE_CLIENT_ID,
                ApiConstants.DRIBBBLE_CLIENT_SECRET,
                code,
                ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI)
                .compose(applyTransformers());
        return getRequestTokenFormCall;
    }

    public Observable<User> getUserInfo() {
        return authClient
                .getAuthUser()
                .compose(applyTransformers());
    }

    public Observable<List<Shot>> callForShots(int currentPage, String sortValue) {
        return authClient
                .fetchShotsPerPageSort(currentPage, ShotsRequestConstants.PER_PAGE, ShotsRequestConstants.LIST_BY_DEFAULT, sortValue)
                .compose(applyTransformers());
    }

    public Observable<List<Attachment>> callForShotsAttachmetns(int idOfShot) {
        return authClient
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
