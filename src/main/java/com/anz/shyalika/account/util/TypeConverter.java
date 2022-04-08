package com.anz.shyalika.account.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.Converter;

/**
 * Utility class providing the converters to be used with model mapper to convert Entity classes to
 * DTO instances
 * 
 * @author Shyalika Benthotage
 */
public class TypeConverter {

    /**
     * Date formatter to format a date in to MMM dd,yyyy format. <br>
     * E.g. Jan. 24,2022
     */
    private static DateFormat DATE_FORMAT_1 = new SimpleDateFormat("MMM dd,yyyy");

    /**
     * Date formatter to format a date in to dd/MM/yyyy format. <br>
     * E.g. 24/01/2022
     */
    private static DateFormat DATE_FORMAT_2 = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Converter instance that can be used by a model mapper to extract a String value form the
     * AccountType enum
     */
    public static Converter<AccountType, String> accountTypeEnumConverter = ctx -> ctx.getSource() != null
            ? ctx.getSource().getName() : null;

    /**
     * Converter instance that can be used by a model mapper to extract a String value form the
     * Currency enum
     */
    public static Converter<Currency, String> currencyEnumConverter = ctx -> ctx.getSource() != null
            ? ctx.getSource().toString() : null;

    /**
     * Converter instance that can be used by a model mapper to extract a String value form the
     * TransactionType enum
     */
    public static Converter<TransactionType, String> transactionTypeEnumConverter = ctx -> ctx.getSource() != null
            ? ctx.getSource().getName() : null;

    /**
     * Converter instance that can be used by a model mapper to extract a formatted string value
     * form the AccountType enum
     */
    public static Converter<Date, String> formatDate = ctx -> ctx.getSource() != null
            ? formatDateToString(ctx.getSource(), DATE_FORMAT_2) : "";

    /**
     * Converter instance that can be used by a model mapper to extract a formatted string value
     * form the AccountType enum
     */
    public static Converter<Date, String> formatDateLong = ctx -> ctx.getSource() != null
            ? formatDateToString(ctx.getSource(), DATE_FORMAT_1) : "";

    /**
     * Formats a given date to a given string format
     * 
     * @param date
     *            - date to be formatted
     * @param dateFormat
     *            - string format
     * @return formatted string
     */
    private static String formatDateToString(Date date, DateFormat dateFormat) {
        if (date != null)
            return dateFormat.format(date).toString();
        else
            return "";

    }
}
