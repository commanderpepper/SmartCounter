package humzaahmad.smartcounter.util

import android.view.View

/**
 * Created by Humza on 3/4/2018.
 */
import android.support.design.widget.Snackbar


fun View.showSnackBar(message: String, duration: Int) {
    Snackbar.make(this, message, duration).show()
}