package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.world.level.Level;

public interface MCTimeUtils {

    int ticksAtSunrise = 23000;
    int ticksAtDayBegin = 0;
    int ticksAtDay = 1000;
    int ticksAtNoon = 6000;
    int ticksAtSunset = 12000;
    int ticksAtNight = 13000;
    int ticksAtMidnight = 18000;
    int ticksAtDayEnd = 24000;

    int ticksPerDay = 24000;
    int ticksPerHour = 1000;
    double ticksPerMinute = 1000D / 60D;
    double ticksPerSecond = ticksPerMinute / 60D;
    double ticksPerMillisecond = ticksPerSecond / 1000D;

    static long[] getTime(Level level) {
        return new long[] {level.getDayTime(), level.getGameTime(), System.currentTimeMillis()};
    }

    static int[] formatDayTime(long rawDayTime) {
        // change the server time started with 0 at midnight
        int dayTime = ((int) (rawDayTime % 2147483647L)) - ticksAtMidnight + ticksPerDay;

        int day = dayTime / ticksPerDay;

        /*
        *    int ticks = dayTime % ticksPerDay;
        *    int hour = ticks / ticksPerHour;
        *    ticks -= hour * ticksPerHour;
        *    int minutes = (int) (ticks / ticksPerMinute);
        *    ticks -= minutes * ticksPerMinute;
        *    int seconds = (int) (ticks / ticksPerSecond);
        */
        int ticks = dayTime - day * ticksPerDay;
        int hour = (ticks / ticksPerHour) % 24;
        int min = (int) (ticks / ticksPerMinute) % 60;
        int sec = (int) (ticks / ticksPerSecond) % 60;

        return new int[] {day, hour, min, sec};
    }

    static int getDay(long dayTime) {
        return ((int) (dayTime % 2147483647L)) / ticksPerDay;
    }

}
