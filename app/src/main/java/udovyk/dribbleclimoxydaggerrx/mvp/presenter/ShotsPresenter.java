package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.Screens;
import udovyk.dribbleclimoxydaggerrx.manager.ApiManager;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotsView;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;

/**
 * Created by udovik.s on 10.01.2018.
 */

@InjectViewState
public class ShotsPresenter extends BasePresenter<ShotsView> {
    private static String TAG = "ShotsPresenter";

    @Inject
    Router router;
    @Inject
    ApiManager apiManager;

    public ShotsPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void loadShotsPage(int currentPage, String sortValue) {
        if (currentPage == 1) {
            getViewState().showLoadingPb();
        }

        apiManager.callForShots(currentPage, sortValue)
                .subscribe(
                        listResponse -> {
                            if (listResponse.isSuccessful()) {
                                if (currentPage == 1) {
                                    Log.d(TAG, "--Sending data to adapter");
                                    List<Shot> results = listResponse.body();
                                    getViewState().hideLoadingPb();
                                    getViewState().addAll(results);
                                    getViewState().addLoadingFooter();
                                } else {
                                    getViewState().removeLoadingFooter();
                                    getViewState().isLoading(false);

                                    List<Shot> results = listResponse.body();
                                    getViewState().addAll(results);

                                    getViewState().addLoadingFooter();
                                }
                            }
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            Log.d(TAG, "--Shots request was failed");
                        }
                );
    }

    public void onItemClick() {
        router.replaceScreen(Screens.SHOT_DETAILS_FRAGMENT_SCREEN);
        //router.navigateTo(Screens.SHOT_DETAILS_FRAGMENT_SCREEN, bundle);
    }




}
