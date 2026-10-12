package io.github.vvb2060.ims.privileged

import android.app.IActivityManager
import android.system.Os

/**
 * 停止 shell 权限委托，兼容新旧两种签名。
 *
 * Android 17 QPR1 (CP3A) 把 stopDelegateShellPermissionIdentity() 改成了
 * stopDelegateShellPermissionIdentity(int uid)，旧签名会抛 NoSuchMethodError。
 * 先尝试新签名，找不到再回退到旧签名。
 */
fun IActivityManager.stopShellPermissionDelegation() {
    try {
        stopDelegateShellPermissionIdentity(Os.getuid())
    } catch (_: NoSuchMethodError) {
        stopDelegateShellPermissionIdentity()
    }
}
