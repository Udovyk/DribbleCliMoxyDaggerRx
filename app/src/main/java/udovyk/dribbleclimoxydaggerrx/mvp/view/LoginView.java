package udovyk.dribbleclimoxydaggerrx.mvp.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by udovik.s on 09.01.2018.
 */

public interface LoginView {

    AlertDialog.Builder buildDialog(Context c);

    void setLoginActivityContentView();

    void startMainActivity();
}
