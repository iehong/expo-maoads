package expo.modules.maoads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sjm.sjmsdk.ad.SjmSplashAdListener;
import com.sjm.sjmsdk.ad.SjmAdError;
import com.sjm.sjmsdk.ad.SjmSplashAd;

public class MaoAdsSpActivity extends AppCompatActivity implements SjmSplashAdListener {
  private static final String TAG = MaoAdsSpActivity.class.getSimpleName();

  private SjmSplashAd splashAd;
  private ViewGroup container;

  private boolean isAdClick;
  private boolean isPause;
  String sjm_adId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    container = findViewById(R.id.splash_container);
    sjm_adId = "sjmad_test002";
    // show();

    // 如果启动图停留时间过短，返回未找到广告位，建议延时调用开屏广告，如下代码例子
    mHandler.postDelayed(new Runnable() {
      @Override
      public void run() {
        show();
      }
    }, 1000);

  }

  Handler mHandler = new Handler();

  private void show() {
    splashAd = new SjmSplashAd(this, this, sjm_adId, 60);
    if (splashAd.checkAndRequestPermission()) {
      fetchSplashAD();
    }
    // 请在 onRequestPermissionsResult 做相关业务处理
  }

  private void fetchSplashAD() {
    splashAd.fetchAndShowIn(container);
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (isAdClick)
      isPause = true;
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (isPause)
      jump();
  }

  @Override
  public void onSjmAdLoaded() {
    showStatus("onSjmAdLoaded:");
  }

  @Override
  public void onSjmAdLoadTimeOut() {
    jump();
  }

  @Override
  public void onSjmAdShow() {
    showStatus("onSjmAdShow:");
  }

  @Override
  public void onSjmAdClicked() {
    isAdClick = true;
    showStatus("onSjmAdClicked:");
  }

  @Override
  public void onSjmAdTickOver() {
    Log.d("test:", "onSjmAdTickOver:");
    if (!isPause)
      jump();
  }

  @Override
  public void onSjmAdDismissed() {
    showStatus("onSjmAdDismissed:");
    Log.d("test:", "onSjmAdDismissed:");
    if (!isPause)
      jump();
  }

  @Override
  public void onSjmAdError(SjmAdError error) {
    showStatus("onSjmAdError:" + error.getErrorMsg());
    jump();
  }

  private void jump() {
    MaoAdsSpActivity.this.finish();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (splashAd.hasAllPermissionsGranted(requestCode, grantResults)) {
      fetchSplashAD();
    } else {
      Toast.makeText(this, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show();
      fetchSplashAD();
    }
  }

  private void showStatus(String msg) {
    Log.i(TAG, msg);
  }

}
