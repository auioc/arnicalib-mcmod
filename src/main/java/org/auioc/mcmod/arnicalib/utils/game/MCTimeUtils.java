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
        int dayTime = (int) (rawDayTime % 2147483647L);

        int day = dayTime / ticksPerDay;

        int ticks = dayTime - day * ticksPerDay;
        int hour = (int) (ticks / ticksPerHour + 6) % 24;
        int min = (int) (ticks / ticksPerMinute) % 60;
        int sec = (int) (ticks / ticksPerSecond) % 60;
        int msec = (int) (ticks / ticksPerMillisecond) % 1000;

        return new int[] {day, hour, min, sec, msec};
    }

    static int getDay(long dayTime) {
        return ((int) (dayTime % 2147483647L)) / ticksPerDay;
    }

}
