package udovyk.dribbleclimoxydaggerrx.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import udovyk.dribbbleclimvp.MyApplication;
import udovyk.dribbbleclimvp.R;
import udovyk.dribbbleclimvp.presenters.LoginPresenterImpl;
import udovyk.dribbbleclimvp.utils.TokenDetails;
import udovyk.dribbleclimoxydaggerrx.MyApplication;
import udovyk.dribbleclimoxydaggerrx.mvp.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private static String TAG = "LoginActivity";

    private LoginPresenterImpl loginPresenter;

    private Button btnLogin;
    private TextView loginLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginPresenter = new LoginPresenterImpl(LoginActivity.this);
        loginPresenter.attachActivityView(this);

        if (!loginPresenter.isConnected(LoginActivity.this)) {
            loginPresenter.buildDialog(LoginActivity.this).show();
        } else {
            if (MyApplication.instance.getSharedPreferences(TokenDetails.OAUTH_SHARED_PREFERENCE_NAME, MODE_PRIVATE).contains(TokenDetails.SP_TOKEN_KEY)) {
                loginPresenter.startMainActivity();
            } else {
                //set content view
                loginPresenter.setLoginActivityContentView();

                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoginFragment authFragment = (LoginFragment) getSupportFragmentManager()
                                .findFragmentByTag(LoginFragment.class.getSimpleName());
                        if (authFragment == null) {
                            authFragment = LoginFragment.newInstance();
                            loginPresenter.attachFragmentView(authFragment);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .add(R.id.container, authFragment, LoginFragment.class.getSimpleName())
                                    .commit();
                        }
                    }
                });
            }
        }
    }


    //arertDialog if no internet connection
    @Override
    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to use this app. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        return builder;
    }

    //set content view to LoginActivity
    @Override
    public void setLoginActivityContentView() {
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        loginLabel = (TextView) findViewById(R.id.loginLabel);
        Typeface labelTypeface = Typeface.createFromAsset(getAssets(), "fonts/pacifico.ttf");
        Typeface buttonTypeface = Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf");
        loginLabel.setTypeface(labelTypeface);
        btnLogin.setTypeface(buttonTypeface);
    }

    @Override
    public void startMainActivity() {
        Intent i = new Intent(MyApplication.instance.getBaseContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }


}
