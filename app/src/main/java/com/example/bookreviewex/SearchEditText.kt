package com.example.bookreviewex

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

class SearchEditText(context: Context, attr: AttributeSet) : AppCompatEditText(context, attr) {

    private lateinit var searchIcon: Drawable
    lateinit var  handleIconClick : ()->Unit

    init {
        val temp = ContextCompat.getDrawable(context, R.drawable.ic_baseline_search_24)
        temp?.let {
            searchIcon = DrawableCompat.wrap(it)
        }


        DrawableCompat.setTintList(searchIcon, hintTextColors)

        searchIcon.setBounds(0, 0, searchIcon.intrinsicWidth, searchIcon.intrinsicHeight)

    }

    private fun setIconVisible(visible: Boolean) {
        searchIcon.setVisible(visible, false)
        setCompoundDrawables(null, null, if (visible) searchIcon else null, null)
    }


    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {

        if (isFocused) {
            setIconVisible(visible = (text?.length ?: 0) > 0)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x ?: return false

        if (searchIcon.isVisible && x > width - paddingRight - searchIcon.intrinsicWidth) {
            if (event.action == MotionEvent.ACTION_UP) {
                if(this::handleIconClick.isInitialized){
                    handleIconClick.invoke()
                }
            }
            return true
        }
        return false
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)

        if (focused) setIconVisible(text?.length?:0 > 0) else setIconVisible(false)
    }

}
