package com.walterxiong.googlestart.utils

import android.content.pm.PackageManager
import com.walterxiong.GoogleStartApplication

private const val TAG = "GoogleServiceUtil"

object GoogleServiceUtil {

    // "com.google.android.backuptransport"           // Google 备份传输
    private val googleServices = listOf(
        "com.google.android.gms",                      // Google Play 服务
        "com.google.android.gsf",                      // Google 服务框架
        "com.android.vending",                         // Google Play 商店
        "com.google.android.onetimeinitializer",       // Google 一次性初始化程序
    )

    // 懒初始化 PackageManager 实例
    private val pm: PackageManager by lazy {
        GoogleStartApplication.context.packageManager
    }

    fun isGoogleStarted(): Boolean {
        var result = true
        for (pkg in googleServices) {
            pm.getPackageInfo(pkg, 0)
            val enabledSetting = pm.getApplicationEnabledSetting(pkg)

            val isStarted = when (enabledSetting) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT -> true

                else -> false
            }

            result = result && isStarted
        }
        return result
    }


    fun disableGoogleServices() {
        for (pkg in googleServices) {
            pm.setApplicationEnabledSetting(
                pkg, PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER, 0
            )
        }
    }

    fun enableGoogleServices() {
        for (pkg in googleServices) {
            pm.setApplicationEnabledSetting(
                pkg, PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER, 0
            )
        }
    }
}
