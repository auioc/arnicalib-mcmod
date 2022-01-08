package org.auioc.mods.arnicalib.utils.java;

import com.google.common.collect.Range;

public class Validate extends org.apache.commons.lang3.Validate {

    private static final String INVALID_INTERVAL_MESSAGE = "The left bound must be less than the right bound";
    private static final String NOT_IN_INTERVAL_MESSAGE = "The value %s is not in the interval %s";
    private static final String NOT_IN_OPEN_INTERVAL_MESSAGE = "The value %s is not in the interval (%s, %s)";
    private static final String NOT_IN_CLOSED_INTERVAL_MESSAGE = "The value %s is not in the interval [%s, %s]";
    private static final String NOT_IN_OPEN_CLOSED_INTERVAL_MESSAGE = "The value %s is not in the interval (%s, %s]";
    private static final String NOT_IN_CLOSED_OPEN_INTERVAL_MESSAGE = "The value %s is not in the interval [%s, %s)";
    private static final String NOT_IN_LEFT_OPEN_INTERVAL_MESSAGE = "The value %s is not in the interval (%s, +∞)";
    private static final String NOT_IN_LEFT_CLOSED_INTERVAL_MESSAGE = "The value %s is not in the interval [%s, +∞)";
    private static final String NOT_IN_RIGHT_OPEN_INTERVAL_MESSAGE = "The value %s is not in the interval (-∞, %s)";
    private static final String NOT_IN_RIGHT_CLOSED_INTERVAL_MESSAGE = "The value %s is not in the interval (-∞, %s]";

    /*================================================================================================================*/

    /**
     * Validate that the specified primitive value is in the specified interval; otherwise, throws an exception.
     *
     * @param interval
     * @param value the value to validate
     * @param message the exception message if invalid
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInInterval(Range, double, String)
     */
    public static void isInInterval(Range<Long> interval, long value, String message) {
        isTrue(interval.contains(value), message + String.format(NOT_IN_INTERVAL_MESSAGE, value, interval.toString()));
    }

    /**
     * @see #isInInterval(Range, long, String)
     */
    public static void isInInterval(Range<Double> interval, double value, String message) {
        isTrue(interval.contains(value), message + String.format(NOT_IN_INTERVAL_MESSAGE, value, interval.toString()));
    }

    /*================================================================================================================*/

    /**
     * Validate that the specified bounds are valid the interval bounds; otherwise, throws an exception.
     *
     * @param left the interval left bound
     * @param right the interval right bound
     */
    public static void isBoundedInterval(long left, long right) {
        isTrue(left < right, INVALID_INTERVAL_MESSAGE);
    }


    /**
     * @see #isBoundedInterval(long, long)
     */
    public static void isBoundedInterval(double left, double right) {
        isTrue(left < right, INVALID_INTERVAL_MESSAGE);
    }


    /*================================================================================================================*/

    /**
     * Validate that the specified primitive value is in the interval <b>(left, right)</b>; otherwise, throws an exception.
     *
     * @param left the interval left bound
     * @param right the interval right bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInOpenInterval(double, double, double)
     */
    public static void isInOpenInterval(long left, long right, long value) {
        isBoundedInterval(left, right);
        isTrue(value > left && value < right, NOT_IN_OPEN_INTERVAL_MESSAGE, value, left, right);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>[left, right]</b>; otherwise, throws an exception.
     *
     * @param left the interval left bound
     * @param right the interval right bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInCloseInterval(double, double, double)
     */
    public static void isInCloseInterval(long left, long right, long value) {
        isBoundedInterval(left, right);
        isTrue(value >= left && value <= right, NOT_IN_CLOSED_INTERVAL_MESSAGE, value, left, right);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>(left, right]</b>; otherwise, throws an exception.
     *
     * @param left the interval left bound
     * @param right the interval right bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInOpenCloseInterval(double, double, double)
     */
    public static void isInOpenCloseInterval(long left, long right, long value) {
        isBoundedInterval(left, right);
        isTrue(value > left && value <= right, NOT_IN_OPEN_CLOSED_INTERVAL_MESSAGE, value, left, right);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>[left, right)</b>; otherwise, throws an exception.
     *
     * @param left the interval left bound
     * @param right the interval right bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInCloseOpenInterval(double, double, double)
     */
    public static void isInCloseOpenInterval(long left, long right, long value) {
        isBoundedInterval(left, right);
        isTrue(value >= left && value < right, NOT_IN_CLOSED_OPEN_INTERVAL_MESSAGE, value, left, right);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>(left, +∞)</b>; otherwise, throws an exception.
     *
     * @param left the interval left bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInLeftOpenInterval(double, double)
     */
    public static void isInLeftOpenInterval(long left, long value) {
        isTrue(value > left, NOT_IN_LEFT_OPEN_INTERVAL_MESSAGE, value, left);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>[left, +∞)</b>; otherwise, throws an exception.
     *
     * @param left the interval left bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInLeftClosedInterval(double, double)
     */
    public static void isInLeftClosedInterval(long left, long value) {
        isTrue(value >= left, NOT_IN_LEFT_CLOSED_INTERVAL_MESSAGE, value, left);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>(-∞, right)</b>; otherwise, throws an exception.
     *
     * @param right the interval left bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInRightOpenInterval(double, double)
     */
    public static void isInRightOpenInterval(long right, long value) {
        isTrue(value < right, NOT_IN_RIGHT_OPEN_INTERVAL_MESSAGE, value, right);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>(-∞, right]</b>; otherwise, throws an exception.
     *
     * @param right the interval left bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInRightClosedInterval(double, double)
     */
    public static void isInRightClosedInterval(long right, long value) {
        isTrue(value <= right, NOT_IN_RIGHT_CLOSED_INTERVAL_MESSAGE, value, right);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>(right, left)</b>; it always throws an exception.
     *
     * @param left the interval left bound
     * @param right the interval right bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInEmptyInterval(double, double, double)
     */
    public static void isInEmptyInterval(long left, long right, long value) {
        isBoundedInterval(left, right);
        isTrue(false, NOT_IN_OPEN_INTERVAL_MESSAGE, value, right, left);
    }

    /**
     * Validate that the specified primitive value is in the interval <b>[bound, bound]</b>; otherwise, throws an exception.
     *
     * @param bound the interval bound
     * @param value the value to validate
     * @throws IllegalArgumentException if the value is not in the interval
     * @see #isInDegenerateInterval(double, double)
     */
    public static void isInDegenerateInterval(long bound, long value) {
        isTrue(value == bound, NOT_IN_CLOSED_INTERVAL_MESSAGE, value, bound, bound);
    }


    /**
     * Validate that the specified primitive value is in the interval <b>(-∞, +∞)</b>; it never throws an exception.
     *
     * @param value the value to validate
     * @see #isInUnboundedInterval(double)
     */
    public static void isInUnboundedInterval(long value) {}

    /*================================================================================================================*/

    /**
     * @see #isInOpenInterval(long, long, long)
     */
    public static void isInOpenInterval(double left, double right, double value) {
        isBoundedInterval(left, right);
        isTrue(value > left && value < right, NOT_IN_OPEN_INTERVAL_MESSAGE, value, left, right);
    }

    /**
     * @see #isInCloseInterval(long, long, long)
     */
    public static void isInCloseInterval(double left, double right, double value) {
        isBoundedInterval(left, right);
        isTrue(value >= left && value <= right, NOT_IN_CLOSED_INTERVAL_MESSAGE, value, left, right);
    }

    /**
     * @see #isInOpenCloseInterval(long, long, long)
     */
    public static void isInOpenCloseInterval(double left, double right, double value) {
        isBoundedInterval(left, right);
        isTrue(value > left && value <= right, NOT_IN_OPEN_CLOSED_INTERVAL_MESSAGE, value, left, right);
    }

    /**
     * @see #isInCloseOpenInterval(long, long, long)
     */
    public static void isInCloseOpenInterval(double left, double right, double value) {
        isBoundedInterval(left, right);
        isTrue(value >= left && value < right, NOT_IN_CLOSED_OPEN_INTERVAL_MESSAGE, value, left, right);
    }

    /**
     * @see #isInLeftOpenInterval(long, long)
     */
    public static void isInLeftOpenInterval(double left, double value) {
        isTrue(value > left, NOT_IN_LEFT_OPEN_INTERVAL_MESSAGE, value, left);
    }

    /**
     * @see #isInLeftClosedInterval(long, long)
     */
    public static void isInLeftClosedInterval(double left, double value) {
        isTrue(value >= left, NOT_IN_LEFT_CLOSED_INTERVAL_MESSAGE, value, left);
    }

    /**
     * @see #isInRightOpenInterval(long, long)
     */
    public static void isInRightOpenInterval(double right, double value) {
        isTrue(value < right, NOT_IN_RIGHT_OPEN_INTERVAL_MESSAGE, value, right);
    }

    /**
     * @see #isInRightClosedInterval(long, long)
     */
    public static void isInRightClosedInterval(double right, double value) {
        isTrue(value <= right, NOT_IN_RIGHT_CLOSED_INTERVAL_MESSAGE, value, right);
    }

    /**
     * @see #isInEmptyInterval(long, long, long)
     */
    public static void isInEmptyInterval(double left, double right, double value) {
        isBoundedInterval(left, right);
        isTrue(false, NOT_IN_OPEN_INTERVAL_MESSAGE, value, right, left);
    }

    /**
     * @see #isInDegenerateInterval(long, long)
     */
    public static void isInDegenerateInterval(double bound, double value) {
        isTrue(value == bound, NOT_IN_CLOSED_INTERVAL_MESSAGE, value, bound, bound);
    }


    /**
     * @see #isInUnboundedInterval(long)
     */
    public static void isInUnboundedInterval(double value) {}

}
