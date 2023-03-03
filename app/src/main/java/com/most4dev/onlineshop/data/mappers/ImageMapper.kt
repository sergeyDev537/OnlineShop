package com.most4dev.onlineshop.data.mappers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.room.TypeConverter
import com.most4dev.onlineshop.R
import java.io.ByteArrayOutputStream


class ImageMapper{

    fun mapDrawableToBitmap(context: Context, id: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, id)
        drawable?.let {
            return drawable.toBitmap()
        } ?: return Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
    }

    fun mapBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }

}