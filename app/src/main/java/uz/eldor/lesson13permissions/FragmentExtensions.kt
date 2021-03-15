package uz.eldor.lesson13permissions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions


fun Fragment.checkPermissions(permission: String, granted: () -> Unit) {
    val myContext = context ?: return
    val options = Permissions.Options()
    options.setCreateNewTask(true)
    Permissions.check(myContext, arrayOf(permission),null,options,object :PermissionHandler(){
        override fun onGranted() {
            granted()
        }
    })
}

fun FragmentActivity.checkPermissions(permission: String, granted: () -> Unit) {
    val myContext = this
    val options = Permissions.Options()
    options.setCreateNewTask(true)
    Permissions.check(myContext, arrayOf(permission),null,options,object :PermissionHandler(){
        override fun onGranted() {
            granted()
        }
    })
}