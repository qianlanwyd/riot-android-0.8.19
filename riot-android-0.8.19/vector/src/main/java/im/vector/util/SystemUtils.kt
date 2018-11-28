/*
 * Copyright 2018 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.core.widget.toast
import im.vector.R
import im.vector.settings.VectorLocale
import org.matrix.androidsdk.util.Log
import java.util.*

private const val LOG_TAG = "SystemUtils"

/**
 * Tells if the application ignores battery optimizations.
 *
 * Ignoring them allows the app to run in background to make background sync with the homeserver.
 * This user option appears on Android M but Android O enforces its usage and kills apps not
 * authorised by the user to run in background.
 *
 * @param context the context
 * @return true if battery optimisations are ignored
 */
fun isIgnoringBatteryOptimizations(context: Context): Boolean {
    // no issue before Android M, battery optimisations did not exist
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
            || (context.getSystemService(Context.POWER_SERVICE) as PowerManager?)?.isIgnoringBatteryOptimizations(context.packageName) == true
}

/**
 * display the system dialog for granting this permission. If previously granted, the
 * system will not show it (so you should call this method).
 *
 * Note: If the user finally does not grant the permission, PushManager.isBackgroundSyncAllowed()
 * will return false and the notification privacy will fallback to "LOW_DETAIL".
 */
@TargetApi(Build.VERSION_CODES.M)
fun requestDisablingBatteryOptimization(activity: Activity, requestCode: Int) {
    val intent = Intent()
    intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
    intent.data = Uri.parse("package:" + activity.packageName)
    activity.startActivityForResult(intent, requestCode)
}

//==============================================================================================================
// Clipboard helper
//==============================================================================================================

/**
 * Copy a text to the clipboard, and display a Toast when done
 *
 * @param context the context
 * @param text    the text to copy
 */
fun copyToClipboard(context: Context, text: CharSequence) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.primaryClip = ClipData.newPlainText("", text)
    context.toast(R.string.copied_to_clipboard)
}

/**
 * Provides the device locale
 *
 * @return the device locale
 */
fun getDeviceLocale(context: Context): Locale {
    var locale: Locale

    locale = try {
        val packageManager = context.packageManager
        val resources = packageManager.getResourcesForApplication("android")
        resources.configuration.locale
    } catch (e: Exception) {
        Log.e(LOG_TAG, "## getDeviceLocale() failed " + e.message, e)
        // Fallback to application locale
        VectorLocale.applicationLocale
    }

    return locale
}
