package india.ajay.snackbarandbottomnavigationapp

import android.Manifest.permission
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import android.widget.Toast
import pub.devrel.easypermissions.AfterPermissionGranted

class BottomNavigationActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks,
EasyPermissions.RationaleCallbacks, BottomNavigationView.OnNavigationItemSelectedListener {


    private var bottomNavigationView: BottomNavigationView? = null
    private var textView: TextView? = null

    companion object{
        const val OPEN_THING = 99
        const val RC_STORAGE_AND_CAMERA = 123
        val TAG = "DocScannerCamera"
        private val STORAGE_AND_CAMERA =
            arrayOf(permission.CAMERA, permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        textView = findViewById(R.id.textView)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
        //locationAndContactsTask()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.recent_item -> { }
            R.id.favourite_item -> { }
            R.id.near_by_item -> { }
        }
        return true
    }



    // region EasyPermissions
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size)

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size)
    }

    override fun onRationaleDenied(requestCode: Int) {
    }

    override fun onRationaleAccepted(requestCode: Int) {
    }

    // endregion

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(RC_STORAGE_AND_CAMERA)
    fun locationAndContactsTask() {
        if (hasLocationAndContactsPermissions()) {
            openCamera()
            // Have permissions, do the thing!
            Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show()
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_location_contacts),
                RC_STORAGE_AND_CAMERA,
                *STORAGE_AND_CAMERA
            )
        }
    }

    fun navigateToCameraActivity() {
    }


    private fun hasLocationAndContactsPermissions(): Boolean {
        return EasyPermissions.hasPermissions(this, *STORAGE_AND_CAMERA)
    }

    fun openCamera(){
//        val preference = ScanConstants.OPEN_CAMERA
//        val intent = Intent(this, ScanActivity::class.java)
//        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference)
//        startActivityForResult(intent, OPEN_THING)
    }

//    fun demoFunction(){
//        val intent = Intent(this, ScanActivity::class.java)
//        intent.putExtra(
//            ScanActivity.EXTRA_BRAND_IMG_RES,
//            R.drawable.ic_crop_white_24dp
//        ) // Set image for title icon - optional
//        intent.putExtra(ScanActivity.EXTRA_TITLE, "Crop Document") // Set title in action Bar - optional
//        intent.putExtra(ScanActivity.EXTRA_ACTION_BAR_COLOR, R.color.green) // Set title color - optional
//        intent.putExtra(ScanActivity.EXTRA_LANGUAGE, "en") // Set language - optional
//        startActivityForResult(intent, REQUEST_CODE_SCAN)
//    }
}
