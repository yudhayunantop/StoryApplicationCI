package com.submission.storyapplication.assets

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.submission.storyapplication.R

class MyEmail : AppCompatEditText, View.OnTouchListener {

    private lateinit var buttonIconError: Drawable

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setOnTouchListener(this)
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.toString().contains('@') || !s.toString().contains('.')){
                    buttonIconError = ContextCompat.getDrawable(context, R.drawable.ic_baseline_error_24) as Drawable
                }
                else{
                    buttonIconError = ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_24) as Drawable
                }
                setButtonDrawables(endOfTheText = buttonIconError)
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })

    }
    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ){
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }
    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        return false
    }
}
