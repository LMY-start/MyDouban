package com.example.mydouban.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.mydouban.R
import kotlinx.android.synthetic.main.rating_progress_bar.view.*

class RatingProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var maxStar: Int = 5
        set(value) {
            field = value
            rpg_ratingBar.numberOfStars = value
        }

    var numStars: Int = 0
        set(value) {
            field = value
            rpg_ratingBar.rating = (maxStar - value).toFloat()
        }

    var starSize: Float = 0.0f
        set(value) {
            field = value
            rpg_ratingBar.starSize = value
        }

    var maxProgress: Int = 0
        set(value) {
            field = value
            rpg_progressBar.max = value
        }

    var progress: Int = 0
        set(value) {
            field = value
            rpg_progressBar.progress = value
        }

    init {
        View.inflate(context, R.layout.rating_progress_bar, this)
        parseAttrs(attrs)
    }

    private fun parseAttrs(attrs: AttributeSet?) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.RatingProgressBar)
        maxStar = typedArray.getInt(R.styleable.RatingProgressBar_maxStar, 5)
        numStars = typedArray.getInt(R.styleable.RatingProgressBar_numStars, 0)
        maxProgress = typedArray.getInt(R.styleable.RatingProgressBar_maxProgress, Int.MAX_VALUE)
        progress = typedArray.getInt(R.styleable.RatingProgressBar_progress, Int.MAX_VALUE)
        starSize = typedArray.getDimension(R.styleable.RatingProgressBar_starSize, Float.MAX_VALUE)
        typedArray.recycle()
    }

}