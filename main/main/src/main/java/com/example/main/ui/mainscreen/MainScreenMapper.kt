package com.example.main.ui.mainscreen

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.main.R
import javax.inject.Inject

class MainScreenMapper
    @Inject
    constructor(
        val context: Context,
    ) {
        private val drawableOne by lazy {
            ContextCompat.getDrawable(context, R.drawable.rc_one_hard)
        }
        private val drawableTwo by lazy {
            ContextCompat.getDrawable(context, R.drawable.rc_two_hard)
        }
        private val drawableThree by lazy {
            ContextCompat.getDrawable(context, R.drawable.img)
        }
        val list =
            mutableListOf<Drawable?>().apply {
                add(drawableOne)
                add(drawableTwo)
                add(drawableThree)
            }

        fun formatNumber(number: Long): String {
            val formattedNumber =
                number
                    .toString()
                    .reversed()
                    .chunked(3)
                    .joinToString(" ")
                    .reversed()
            return formattedNumber
        }
    }
