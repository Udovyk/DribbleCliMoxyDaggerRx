package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;
import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.Screens;
import udovyk.dribbleclimoxydaggerrx.common.ShotDetailConstants;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotDetailView;

/**
 * Created by udovik.s on 10.01.2018.
 */

@InjectViewState
public class ShotDetailsPresenter extends BasePresenter<ShotDetailView>{
    private static String TAG = "ShotDetailFragmentPresenter";

    @Inject
    Router router;

    public ShotDetailsPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void setData(Bundle bundle) {
        getViewState().setData(bundle);
    }

    public void onItemClick(Bundle bundle) {
        Log.d(TAG, "-----" + bundle.getString(ShotDetailConstants.IMAGE_URL));
        router.navigateTo(Screens.SHOT_ATTACHMENTS_FRAGMENT_SCREEN, bundle);
    }

}
