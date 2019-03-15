package rmg.prsolution.dfm.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun SwipeRefreshLayout.startRefreshing() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}

//Inflate a Layout
/**
 * context.inflate(R.layout.my_layout)
 */

fun Context.inflate(res: Int, parent: ViewGroup? = null) : View {
    return LayoutInflater.from(this).inflate(res, parent, false)
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)


// fun ImageView.loadImage(url: String) = Glide.with(this).load(url).into(this)

fun TextView.setColor(color: Int) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    this.setTextColor(context.getColor(color))
} else {
    TODO("VERSION.SDK_INT < M")
}

// Retrieving a Color resource across API levels
/**
 * use context.color(R.color.my_color)
 */

fun Context.color(@ColorRes id: Int) = when {
    isAtLeastMarshmallow() -> {
        resources.getColor(id, null)
    }
    else -> resources.getColor(id)
}

fun isAtLeastMarshmallow(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

// View with layout
/**
 * view.setHeight(newHeight)
 */
fun View.setHeight(height: Int) {
    val params = layoutParams
    params.height = height
    layoutParams = params
}

// Validate login
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}
fun EditText.validate(validator: (String) -> Boolean, message: String): Boolean {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
    return this.error == null
}
//                Navigation.findNavController(requireActivity(), R.id.splash_host_fragment).navigate(action)


// EditText on end icon click listener
@SuppressLint("ClickableViewAccessibility")
fun TextView.onRightDrawableClicked(onClicked: (view: TextView) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is TextView) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}

