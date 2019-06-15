package india.ajay.snackbarandbottomnavigationapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var fabButton: FloatingActionButton? = null
    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        fabButton = findViewById(R.id.fab_button)
        fabButton?.setOnClickListener { view ->
            showSnackBar(view)
        }
    }

    private fun showSnackBar(view: View){
        snackBar = Snackbar.make(view, getString(R.string.message_sent), Snackbar.LENGTH_INDEFINITE)
        snackBar?.duration = 8000
        snackBar?.setAction("Ok", View.OnClickListener {
            snackBar?.dismiss()
        })
        //Customizing snackbar
        val viewSnack = snackBar?.view
        viewSnack?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSnakeBarBackground))

        val textView = viewSnack?.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView?.setTextColor(ContextCompat.getColor(this, R.color.colorSnakeBarText))

        val textViewAction = viewSnack?.findViewById<TextView>(android.support.design.R.id.snackbar_action)
        textViewAction?.setTextColor(ContextCompat.getColor(this, R.color.colorSnakeBarAction))

        snackBar?.show()

    }
}
