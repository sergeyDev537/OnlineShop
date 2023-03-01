package com.most4dev.onlineshop.utils

import android.content.res.Resources
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.snackbar.Snackbar
import com.most4dev.onlineshop.R

fun View.showSnackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun BottomNavigationView.cornersBottomNavigationView(resources: Resources, idRadius: Int){
    val radius = resources.getDimension(idRadius)
    val bottomNavigationViewBackground = this.background as MaterialShapeDrawable
    bottomNavigationViewBackground.shapeAppearanceModel =
        bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .build()
}