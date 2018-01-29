package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.common.ApiConstants;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.LoginPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.LoginView;
import udovyk.dribbleclimoxydaggerrx.ui.activity.MainActivity;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class LoginFragment extends BaseFragment implements LoginView {
    private static String TAG = "LoginFragment";

    @InjectPresenter
    LoginPresenter presenter;

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).lockDrawer();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.clearWebView();

        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();

                if (ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI_SCHEMA.equals(uri.getScheme())
                        && ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI_HOST.equals(
                        uri.getHost())) {
                    String code = uri.getQueryParameter(ApiConstants.CODE);
                    String error = uri.getQueryParameter(ApiConstants.ERROR_CODE);
                    if (!TextUtils.isEmpty(code)) {
                        Log.d(TAG, "---" + code);
                        presenter.getAccessToken(code);
                    } else if (!TextUtils.isEmpty(error)) {
                        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });

        webView.loadUrl(ApiConstants.DRIBBBLE_AUTHORIZE_URL
                + "?client_id=" + ApiConstants.DRIBBBLE_CLIENT_ID
                + "&redirect_uri=" + ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI
                + "&scope=" + ApiConstants.DRIBBBLE_AUTHORIZE_SCOPE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_layout_login;
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }
}
