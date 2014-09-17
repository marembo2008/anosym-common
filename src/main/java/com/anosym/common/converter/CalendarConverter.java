package com.anosym.common.converter;

import com.anosym.common.converter.Converter;
import com.anosym.common.converter.ConverterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author marembo
 */
public class CalendarConverter implements Converter<Calendar, String> {

    private static final Logger LOG = Logger.getLogger(CalendarConverter.class.getName());
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public String to(@Nonnull final Calendar value, @Nullable final String... formats) {
        checkNotNull(value, "The calendar value must be null");

        final String format = formats == null ? DEFAULT_FORMAT : formats[0];
        LOG.log(Level.INFO, "Format: {0}", format);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(value.getTime());
        } catch (Exception ex) {
            throw new ConverterException(value.getClass(), String.class, ex);
        }
    }

    @Override
    public Calendar from(@Nonnull final String value, @Nullable final String... formats) {
        checkNotNull(value, "The calendar value must be null");

        final String format = formats == null ? DEFAULT_FORMAT : formats[0];
        LOG.log(Level.INFO, "Format: {0}", format);

        if (value.trim().equalsIgnoreCase("NA")) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date date = dateFormat.parse(value);
            Calendar instance = zeroCalendar();
            instance.setTime(date);
            return instance;
        } catch (ParseException ex) {
            throw new ConverterException(String.class, value.getClass(), ex);
        }
    }

    private Calendar zeroCalendar() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(0);
        return instance;
    }
}
