package tw.fatminmin.xposed.minminguard.blocker.adnetwork;

import tw.fatminmin.xposed.minminguard.Main;
import tw.fatminmin.xposed.minminguard.blocker.ApiBlocking;
import tw.fatminmin.xposed.minminguard.blocker.Blocker;
import tw.fatminmin.xposed.minminguard.blocker.Util;
import android.view.View;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Inmobi extends Blocker {
    
    public static final String BANNER = "com.inmobi.ads.IMBanner";
    public static final String BANNER_PREFIX = "com.inmobi.ads";

	public static final String INTER_ADS = "com.inmobi.ads.InMobiInterstitial";

    public static final String NATIVE_ADS = "com.inmobi.ads.InMobiNative";

	@Override
	public String getBannerPrefix() {
		return BANNER_PREFIX;
	}

	@Override
	public String getBanner() {
		return BANNER;
	}
	public boolean handleLoadPackage(final String packageName, LoadPackageParam lpparam, final boolean removeAd) {
        boolean result = false;
        result |= ApiBlocking.removeBanner(packageName, BANNER, "load", lpparam, removeAd);
        result |= ApiBlocking.blockAdFunction(packageName, INTER_ADS, "load", lpparam, removeAd);
        result |= ApiBlocking.blockAdFunction(packageName, INTER_ADS, "show", lpparam, removeAd);
        result |= ApiBlocking.blockAdFunction(packageName, NATIVE_ADS, "load", lpparam, removeAd);
		return result;
	}
}
