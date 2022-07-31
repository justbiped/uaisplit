package com.favoriteplaces.core.extensions

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.requestPermission(
    permission: String,
    result: (RequestPermissionResult) -> Unit
) {
    val permissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) result(RequestPermissionResult.Granted) else result(RequestPermissionResult.Denied)
    }

    if (isPermissionGranted(permission)) {
        result(RequestPermissionResult.Granted)
    } else if (shouldShowRequestPermissionRationale(permission)) {
        result(RequestPermissionResult.RationaleRequest)
    } else {
        permissionRequest.launch(permission)
    }
}

sealed interface RequestPermissionResult {
    object Granted : RequestPermissionResult
    object Denied : RequestPermissionResult
    object RationaleRequest : RequestPermissionResult
}

fun Context.isPermissionGranted(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
