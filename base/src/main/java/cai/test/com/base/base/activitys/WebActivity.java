package cai.test.com.base.base.activitys;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import cai.test.com.base.R;
import cai.test.com.base.annotation.ViewInject;

/**
 * Created by Administrator on 2017/11/17.
 */

public class WebActivity extends BaseTitleActivity {


    private WebView webView;
    private String url;
    private String title;


    @Override
    public void init() {
        webView = (WebView) findViewById(R.id.web_view);
        initWebView();
    }


    /**
     * 跳转到WebActivity界面
     */
    public static void launch(Context context, String url, String title) {
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(context, "url的路径为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(context, "标题为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * 初始化
     */
    private void initWebView() {
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setSupportZoom(true);
        // 设置是否支持执行JS，如果设置为true会存在XSS攻击风险
        webView.getSettings().setJavaScriptEnabled(true);
        // mWebView.addJavascriptInterface(new HTMLheaderJavaScriptInterface(), "local_obj");
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // 水平不显示
        webView.setHorizontalScrollBarEnabled(false);
        // 垂直不显示
        webView.setVerticalScrollBarEnabled(false);

        webView.getSettings().setUseWideViewPort(true);
        // 安全考虑，防止密码泄漏，尤其是root过的手机
        webView.getSettings().setSavePassword(false);
        String ua = webView.getSettings().getUserAgentString();
        String appUA = ua + "; MYAPP";
        webView.getSettings().setUserAgentString(appUA);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);


        webView.getSettings().setDatabaseEnabled(true);
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();

        // 启用地理定位
        webView.getSettings().setGeolocationEnabled(true);
        // 设置定位的数据库路径
        webView.getSettings().setGeolocationDatabasePath(dir);

        // 最重要的方法，一定要设置，这就是出不来的主要原因
        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl(url);
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    protected boolean isShowBack() {
        return true;
    }

    @Override
    protected String getTitleName() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        return title;
    }

    @Override
    protected void onBackClick() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }



}
