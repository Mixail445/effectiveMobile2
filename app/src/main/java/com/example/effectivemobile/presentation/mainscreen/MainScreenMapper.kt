package com.example.effectivemobile.presentation.mainscreen

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.effectivemobile.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainScreenMapper @Inject
constructor(
    @ApplicationContext val context: Context,
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
    val list = mutableListOf<Drawable?>().apply {
        add(drawableOne)
        add(drawableTwo)
        add(drawableThree)
    }
    fun formatNumber(number: Long): String {
        val formattedNumber = number.toString().reversed().chunked(3).joinToString(" ").reversed()
        return formattedNumber
    }
}
