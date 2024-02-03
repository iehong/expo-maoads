package expo.modules.maoads

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.sjm.sjmsdk.ad.SjmAdError
import com.sjm.sjmsdk.ad.SjmBannerAd
import com.sjm.sjmsdk.ad.SjmBannerAdListener
import com.sjm.sjmsdk.ad.SjmRewardVideoAdListener
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.views.ExpoView

@SuppressLint("ViewConstructor")
class ExpoMaoadsView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {

  internal var adsId: String = "sjmad_test003"
  private var bannerAd: SjmBannerAd? = null;

  private val f = FrameLayout(context).also {
    it.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    Log.d("ExpoMaoadsView", adsId);
    bannerAd = SjmBannerAd(
      appContext.currentActivity,
      adsId,
      object : SjmBannerAdListener {
        override fun onSjmAdLoaded() {
          Log.d("ExpoMaoadsView", "onSjmAdLoaded");
        }

        override fun onSjmAdShow() {
          Log.d("ExpoMaoadsView", "onSjmAdShow");
        }

        override fun onSjmAdClicked() {
          Log.d("ExpoMaoadsView", "onSjmAdClicked");
        }

        override fun onSjmAdClosed() {
          Log.d("ExpoMaoadsView", "onSjmAdClosed");
        }

        override fun onSjmAdError(p0: SjmAdError?) {
          if (p0 != null) {
            Log.d("ExpoMaoadsView", "onSjmAdError" + p0.errorMsg)
          };
        }
      }
    )
    bannerAd!!.setBannerContainer(it);
    Log.d("ExpoMaoadsView", adsId);
    bannerAd!!.loadAD()
    addView(it)
  }

}
