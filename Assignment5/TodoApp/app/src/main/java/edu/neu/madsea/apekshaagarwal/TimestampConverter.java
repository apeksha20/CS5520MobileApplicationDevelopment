package edu.neu.madsea.apekshaagarwal;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*
Credits  Adrienne Slaughter : Referred TimestampConverter from her
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class TimestampConverter {

    public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter isoDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//    DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null && !value.isEmpty()) {
            try {
                return format.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @TypeConverter
    public static String fromDate(Date value) {
        if (value == null) {
            return null;
        }
        return format.format(value);
    }

    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime value) {
        if (value != null) {
//            return value.format(localDateTimeFormatter);
            return value.format(isoDateTimeFormatter);
        } else {
            return "";
        }
    }

    @TypeConverter
    public static LocalDateTime fromString(String value) {
        if (value != null) {
            if (value.equalsIgnoreCase(""))
                return LocalDateTime.now();
            LocalDateTime thing;
            try {
//                thing = LocalDateTime.from(localDateTimeFormatter.parse(value));
                thing = LocalDateTime.from(isoDateTimeFormatter.parse(value));
            } catch (Exception e) {
                thing = null;
            }
            return thing;

        } else {
            return LocalDateTime.now();
        }
    }
}