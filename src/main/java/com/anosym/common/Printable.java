package com.anosym.common;

import com.anosym.common.converter.CalendarConverter;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marembo
 */
@XmlTransient
public abstract class Printable implements Serializable {

    private static final Predicate<Field> NON_STATIC_FIELDS = new Predicate<Field>() {

        @Override
        public boolean apply(Field input) {
            final int modifiers = input.getModifiers();
            return !Modifier.isStatic(modifiers);
        }
    };
    private static final Comparator<Field> FIELD_NAME_COMPARATOR = new Comparator<Field>() {

        @Override
        public int compare(Field o1, Field o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    private static final CalendarConverter CALENDAR_CONVERTER = new CalendarConverter();

    private transient Iterable<Field> fields;

    private void getFields(@Nonnull final List<Field> fields, @Nonnull Class<?> clazz) {
        if (clazz.isAssignableFrom(Printable.class)) {
            return;
        }
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        getFields(fields, clazz.getSuperclass());
    }

    private Iterable<Field> getfields() {
        if (this.fields != null) {
            return this.fields;
        }
        final List<Field> fieldLists = Lists.newArrayList();
        getFields(fieldLists, getClass());
        Collections.sort(fieldLists, FIELD_NAME_COMPARATOR);
        return this.fields = Collections2.filter(fieldLists, NON_STATIC_FIELDS);
    }

    @Override
    public String toString() {
        final ToStringHelper toStringHelper = MoreObjects.toStringHelper(getClass());
        for (final Field f : getfields()) {
            if (f.isAnnotationPresent(StringTransient.class)) {
                continue;
            }
            try {
                f.setAccessible(true);
                final Class<?> type = f.getType();
                //special handling for calendars.
                Object value = f.get(this);
                if (value != null && Calendar.class.isAssignableFrom(type)) {
                    value = CALENDAR_CONVERTER.to((Calendar) value, "yyyy-MM-dd HH:mm:ss");
                } else if (type.isArray() && value != null) {
                    value = Arrays.toString((Object[]) value);
                }
                toStringHelper.add(f.getName(), value);
            } catch (final IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return toStringHelper.toString();
    }

}
