package edu.neu.madsea.apekshaagarwal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class BitmapConverter {

    @TypeConverter
    public static byte[] fromBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            return outputStream.toByteArray();
        }
        return null;
    }

    @TypeConverter
    public static Bitmap toBitmap(byte[] bytes) {
        if (bytes != null) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return null;
    }
}
