package com.walterxiong.googlestart.logic.service

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import com.walterxiong.GoogleStartApplication
import com.walterxiong.googlestart.R
import com.walterxiong.googlestart.utils.GoogleServiceUtil


private const val TAG = "GoogleStartService"

class GoogleStartService : TileService() {

    companion object {
        fun getComponentName(context: Context): ComponentName {
            return ComponentName(context.applicationContext, GoogleStartService::class.java)
        }

        fun getIcon(context: Context): Icon {
            return Icon.createWithResource(context, R.drawable.ic_google)
        }
    }

    private val googleStarted by lazy { GoogleServiceUtil.isGoogleStarted() }

    // Called when the tile should start listening to some state change that it needs to react to.
    // Typically, this is invoked when the app calls [TileService.requestListeningState].
    override fun onStartListening() {
        super.onStartListening()
        Log.d(TAG, "onStartListening")

        updateTile(GoogleServiceUtil.isGoogleStarted())
    }

    override fun onClick() {
        super.onClick()
        Log.d(TAG, "onClick ${qsTile?.state}")
        /*if (googleStarted) {
            GoogleServiceUtil.disableGoogleServices()
        } else {
            GoogleServiceUtil.enableGoogleServices()
        }*/
        toggle()
    }

    private fun toggle() {

        val intent = Intent().apply {
            component = ComponentName(
                "com.miui.securitycenter",
                "com.miui.googlebase.ui.GmsCoreSettings"
            )
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startActivityAndCollapse(
                PendingIntent.getActivity(
                    GoogleStartApplication.context,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
        }

        updateTile(googleStarted)
    }

    private fun updateTile(active: Boolean) {
        val tile = qsTile ?: return

        tile.icon = getIcon(this)
        tile.label = getString(R.string.tile_label)
        tile.state = if (active) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        if (Build.VERSION.SDK_INT >= 30) {
            tile.stateDescription = if (active) "Active" else "Inactive"
        }
        tile.updateTile()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    // Called when the tile is added to the Quick Settings by the user.
    // Note that this won't be called when the tile was added by
    // [StatusBarManager.requestAddTileService()].
    override fun onTileAdded() {
        super.onTileAdded()
        Log.d(TAG, "onTileAdded + ${Thread.currentThread().name}")
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
        Log.d(TAG, "onTileRemoved")
    }

    override fun onStopListening() {
        super.onStopListening()
        Log.d(TAG, "onStopListening")
    }
}
