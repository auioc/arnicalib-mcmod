/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.base.validate;

import com.google.common.collect.Range;

public class Validate extends org.apache.commons.lang3.Validate {

    // ============================================================================================================== //
    // #region ThrowException

    public static void throwException(String message) {
        throw new IllegalArgumentException(message);
    }

    public static void throwException(String message, Object... objects) {
        throw new IllegalArgumentException(String.format(message, objects));
    }

    // #endregion ThrowException

    // ============================================================================================================== //
    // #region CompareToZero

    private static final String NOT_POSITIVE_MESSAGE = "The value must be positive: %s";
    private static final String NOT_NEGATIVE_MESSAGE = "The value must be negative: %s";
    private static final String NOT_NON_NEGATIVE_MESSAGE = "The value must be non-negative: %s";
    private static final String NOT_NON_POSITIVE_MESSAGE = "The value must be non-positive: %s";

    public static void isPositive(long value, String message) {
        isTrue(value > 0, message);
    }

    public static void isNonNegative(long value, String message) {
        isTrue(value >= 0, message);
    }

    public static void isNegative(long value, String message) {
        isTrue(value < 0, message);
    }

    public static void isNonPositive(long value, String message) {
        isTrue(value <= 0, message);
    }

    /*========================================================================*/

    public static void isPositive(double value, String message) {
        isTrue(value > 0, message);
    }

    public static void isNonNegative(double value, String message) {
        isTrue(value >= 0, message);
    }

    public static void isNegative(double value, String message) {
        isTrue(value < 0, message);
    }

    public static void isNonPositive(double value, String message) {
        isTrue(value <= 0, message);
    }

    /*========================================================================*/

    public static void isPositive(long value) {
        isTrue(value > 0, NOT_POSITIVE_MESSAGE, value);
    }

    public static void isNonNegative(long value) {
        isTrue(value >= 0, NOT_NON_NEGATIVE_MESSAGE, value);
    }

    public static void isNegative(long value) {
        isTrue(value < 0, NOT_NEGATIVE_MESSAGE, value);
    }

    public static void isNonPositive(long value) {
        isTrue(value <= 0, NOT_NON_POSITIVE_MESSAGE, value);
    }

    /*========================================================================*/

    public static void isPositive(double value) {
        isTrue(value > 0, NOT_POSITIVE_MESSAGE, value);
    }

    public static void isNonNegative(double value) {
        isTrue(value >= 0, NOT_NON_NEGATIVE_MESSAGE, value);
    }

    public static void isNegative(double value) {
        isTrue(value < 0, NOT_NEGATIVE_MESSAGE, value);
    }

    public static void isNonPositive(double value) {
        isTrue(value <= 0, NOT_NON_POSITIVE_MESSAGE, value);
    }

    // #endregion CompareToZero

    // ============================================================================================================== //
    // #region Interval

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

    /**
     * Validate that the specified primitive value is in the specified interval; otherwise, throws an exception.
     *
     * @param interval
     * @param value    the value to validate
     * @param message  the exception message if invalid
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

    /*========================================================================*/

    /**
     * Validate that the specified bounds are valid the interval bounds; otherwise, throws an exception.
     *
     * @param left  the interval left bound
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


    /*========================================================================*/

    /**
     * Validate that the specified primitive value is in the interval <b>(left, right)</b>; otherwise, throws an exception.
     *
     * @param left  the interval left bound
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
     * @param left  the interval left bound
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
     * @param left  the interval left bound
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
     * @param left  the interval left bound
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
     * @param left  the interval left bound
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
     * @param left  the interval left bound
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
     * @param left  the interval left bound
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
    public static void isInUnboundedInterval(long value) { }

    /*========================================================================*/

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
    public static void isInUnboundedInterval(double value) { }

    // #endregion Interval

    // ============================================================================================================== //
    // #region Fraction

    private static final String INVALID_FRACTION_MESSAGE = "The denominator must not be zero";
    private static final String NOT_A_PROPER_FRACTION_MESSAGE = "%s/%s is not a proper fraction";
    private static final String NOT_A_IMPROPER_FRACTION_MESSAGE = "%s/%s is not a improper fraction";
    private static final String NOT_A_UNIT_FRACTION_MESSAGE = "%s/%s is not a unit fraction";
    private static final String INVALID_FRACTION_CHANCE = "%s/%s is not a valid fraction chance value";

    public static void isFraction(long numerator, long denominator) {
        isTrue(denominator != 0, INVALID_FRACTION_MESSAGE);
    }

    public static void isProperFraction(long numerator, long denominator) {
        isFraction(numerator, denominator);
        isTrue(numerator > 0 && denominator > 0, NOT_A_PROPER_FRACTION_MESSAGE, numerator, denominator);
        isTrue(numerator < denominator, NOT_A_PROPER_FRACTION_MESSAGE, numerator, denominator);
    }

    public static void isImproperFraction(long numerator, long denominator) {
        isFraction(numerator, denominator);
        isTrue(Math.abs(numerator / denominator) >= 1, NOT_A_IMPROPER_FRACTION_MESSAGE, numerator, denominator);
    }

    public static void isUnitFraction(long numerator, long denominator) {
        isProperFraction(numerator, denominator);
        isTrue(numerator == 1, NOT_A_UNIT_FRACTION_MESSAGE, numerator, denominator);
    }

    public static void isFractionChance(long numerator, long denominator) {
        isFraction(numerator, denominator);
        isTrue(numerator >= 0 && denominator > 0 && numerator <= denominator, INVALID_FRACTION_CHANCE, numerator, denominator);
    }

    // #endregion ProperFraction

}
