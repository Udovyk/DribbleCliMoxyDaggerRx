package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;

import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotDetailView;

/**
 * Created by udovik.s on 10.01.2018.
 */

@InjectViewState
public class ShotDetailsPresenter extends BasePresenter<ShotDetailView>{
    private static String TAG = "ShotDetailFragmentPresenter";

    public ShotDetailsPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void setData(Bundle bundle) {
        getViewState().setData(bundle);
    }

}
