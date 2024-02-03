package expo.modules.maoads;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.sjm.sjmsdk.SjmSdk;

import java.util.Objects;

import expo.modules.core.interfaces.ReactActivityLifecycleListener;

public class MyLibReactActivityLifecycleListener implements ReactActivityLifecycleListener {
  @Override
  public void onCreate(Activity activity, Bundle savedInstanceState) {
    ApplicationInfo info = null;
    try {
      info = activity.getPackageManager().getApplicationInfo(activity.getApplicationInfo().packageName,
          PackageManager.GET_META_DATA);
    } catch (PackageManager.NameNotFoundException e) {
      throw new RuntimeException(e);
    }
    SjmSdk.init(activity, info.metaData.getString("MAOADS_APP"));
    if(!Objects.requireNonNull(info.metaData.getString("MAOADS_SPLASH")).isEmpty()){
      ComponentName com = new ComponentName(activity.getPackageName(), "expo.modules.maoads.MaoAdsSpActivity");
      Intent intent = new Intent();
      intent.setComponent(com);
      Bundle bundle = new Bundle();
      bundle.putString("sjm_adId", info.metaData.getString("MAOADS_SPLASH"));
      intent.putExtras(bundle);
      activity.startActivity(intent);
    }

  }
}
