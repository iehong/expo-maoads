package expo.modules.maoads

import android.content.pm.PackageManager
import android.util.Log
import android.widget.FrameLayout
import com.sjm.sjmsdk.ad.SjmAdError
import com.sjm.sjmsdk.ad.SjmRewardVideoAd
import com.sjm.sjmsdk.ad.SjmRewardVideoAdListener
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoMaoadsModule : Module() {

  override fun definition() = ModuleDefinition {
    val applicationInfo =
      appContext.reactContext?.packageManager?.getApplicationInfo(
        appContext.reactContext?.packageName.toString(),
        PackageManager.GET_META_DATA
      )
    var rewardVideoAd: SjmRewardVideoAd? = null

    Name("ExpoMaoads")

    Events("onReward", "onRewardAdLoaded")

    Function("reward") {
      rewardVideoAd =
        SjmRewardVideoAd(
          appContext.currentActivity,
          applicationInfo?.metaData?.getString("MAOADS_REWARD"),
          object : SjmRewardVideoAdListener {
            override fun onSjmAdTradeId(p0: String?, p1: String?, p2: Boolean) {}

            override fun onSjmAdLoaded(p0: String?) {
              Log.d("SJMREWARD", "onSjmAdShow: ")
              this@ExpoMaoadsModule.sendEvent(
                "onReward",
                mapOf("loaded" to true, "close" to false, "rewarded" to false)
              )
              rewardVideoAd?.showAD()
            }

            override fun onSjmAdVideoCached() {}

            override fun onSjmAdShow() {}

            override fun onSjmAdShowError(p0: SjmAdError?) {}

            override fun onSjmAdClick() {}

            override fun onSjmAdVideoComplete() {}

            override fun onSjmAdExpose() {}

            override fun onSjmAdReward(p0: String?, p1: String?) {
              Log.d("SJMREWARD", "onSjmAdReward: ")
              this@ExpoMaoadsModule.sendEvent(
                "onReward",
                mapOf("loaded" to false, "close" to false, "rewarded" to true)
              )
            }

            override fun onSjmAdClose() {
              Log.d("SJMREWARD", "onSjmAdClose: ")
              this@ExpoMaoadsModule.sendEvent(
                "onReward",
                mapOf("loaded" to false, "close" to true, "rewarded" to false)
              )
            }

            override fun onSjmAdError(p0: SjmAdError?) {}
          }
        )
      rewardVideoAd!!.loadAd()
    }

    View(ExpoMaoadsView::class) {
      Prop("adsId") { view: ExpoMaoadsView, prop: String ->
        view.adsId = prop
      }
    }
  }
}
