package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import udovyk.dribbleclimoxydaggerrx.App;
import udovyk.dribbleclimoxydaggerrx.manager.ApiManager;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotAttachmentsView;
import udovyk.dribbleclimoxydaggerrx.network.model.Attachment;

/**
 * Created by udovik.s on 10.01.2018.
 */

@InjectViewState
public class ShotAttachmentsPresenter extends BasePresenter<ShotAttachmentsView> {
    private static String TAG = "ShotAttachmentsPresenter";

    @Inject
    ApiManager apiManager;

    public ShotAttachmentsPresenter() {
        App.getApplicationComponent().inject(this);
    }


    public void addAttachmentsToList(List<Attachment> listOfAttachments, int idOfShot) {
        getViewState().hideStatusBar();
        apiManager.callForShotsAttachmetns(idOfShot).subscribe(
                listResponse -> {
                    for (Attachment at : listResponse.body()) {
                        listOfAttachments.add(at);
                        Log.d(TAG, "---att was added to list : url = " + at.getUrl() + "---");
                    }

                    getViewState().showViewPager();
                },
                throwable -> {
                    Log.d(TAG, "--- Something was failed while requesting attachments ( ----");
                    throwable.printStackTrace();
                }
        );
    }

}
