const {
  withAndroidManifest,
  AndroidConfig,
  createRunOncePlugin,
} = require("@expo/config-plugins");
const pkg = require("./package.json");
const withKey = (
  config,
  { id, splash, reward, interstitial, fullScreen, content }
) => {
  config = withAndroidManifest(config, (config) => {
    const mainApplication = AndroidConfig.Manifest.getMainApplicationOrThrow(
      config.modResults
    );
    AndroidConfig.Manifest.addMetaDataItemToMainApplication(
      mainApplication,
      "MAOADS_APP",
      id || "test0001"
    );
    AndroidConfig.Manifest.addMetaDataItemToMainApplication(
      mainApplication,
      "MAOADS_SPLASH",
      splash
    );
    AndroidConfig.Manifest.addMetaDataItemToMainApplication(
      mainApplication,
      "MAOADS_REWARD",
      reward || "sjmad_test001"
    );
    AndroidConfig.Manifest.addMetaDataItemToMainApplication(
      mainApplication,
      "MAOADS_INTERSTITIAL",
      interstitial || "sjmad_test004"
    );
    AndroidConfig.Manifest.addMetaDataItemToMainApplication(
      mainApplication,
      "MAOADS_FULLSCREEN",
      fullScreen || "sjmad_test006"
    );
    AndroidConfig.Manifest.addMetaDataItemToMainApplication(
      mainApplication,
      "MAOADS_CONTENT",
      content || "sjmad_test012"
    );
    return config;
  });

  return config;
};

exports.default = createRunOncePlugin(withKey, pkg.name, pkg.version);
