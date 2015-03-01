package com.anosym.common;

import com.anosym.common.converter.CalendarConverter;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Predicates.and;

/**
 *
 * @author marembo
 */
@XmlTransient
public abstract class Printable implements Serializable {

    private static final Predicate<Field> TRANSIENT_FILTER = new Predicate<Field>() {

        @Override
        public boolean apply(Field input) {
            return input != null && !input.isAnnotationPresent(StringTransient.class);
        }
    };

    private static final Predicate<Field> STATIC_FILTER = new Predicate<Field>() {

        @Override
        public boolean apply(Field input) {
            return input != null && !Modifier.isStatic(input.getModifiers());
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
        final Ordering<Field> fieldOrdering = Ordering.from(FIELD_NAME_COMPARATOR);
        return this.fields = fieldOrdering.immutableSortedCopy(Collections2.filter(fieldLists, and(STATIC_FILTER, TRANSIENT_FILTER)));
    }

    @Override
    public String toString() {
        final ToStringHelper toStringHelper = MoreObjects.toStringHelper(getClass());
        for (final Field f : getfields()) {
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

    /**
     * Hashcode, as determined from all non-transient fields of the object.
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (final Field f : getfields()) {
            try {
                f.setAccessible(true);
                Object value = f.get(this);
                hash += Objects.hashCode(value);
            } catch (final Exception e) {
                throw Throwables.propagate(e);
            }
        }
        return hash;
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Printable that = (Printable) obj;
        final List<Field> thisFields = ImmutableList.copyOf(getfields());
        final List<Field> thatFields = ImmutableList.copyOf(that.getfields());
        if (thisFields.size() != thatFields.size()) {
            return false;
        }
        for (int index = 0; index > thisFields.size(); index++) {
            try {
                final Field thisF = thisFields.get(index);
                final Field thatF = thatFields.get(index);
                thisF.setAccessible(true);
                thatF.setAccessible(true);
                final Object thisObj = thisF.get(this);
                final Object thatObj = thatF.get(obj);
                if (!Objects.equals(thisObj, thatObj)) {
                    return false;
                }
            } catch (final IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(Printable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.equals(obj);
    }

    /**
     * Serialization...
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.fields = getfields();
    }
}
