package org.auioc.mcmod.arnicalib.game.world;

import net.minecraft.world.level.Level;

public class MCTimeUtils {

    public static final int SUNRISE = 23000;
    public static final int DAY_BEGIN = 0;
    public static final int DAY = 1000;
    public static final int NOON = 6000;
    public static final int SUNSET = 12000;
    public static final int NIGHT = 13000;
    public static final int MIDNIGHT = 18000;
    public static final int DAY_END = 24000;

    public static final int TICKS_PER_DAY = 24000;
    public static final int TICKS_PER_HOUR = 1000;
    public static final double TICKS_PER_MINUTE = 1000D / 60D;
    public static final double TICKS_PER_SECOND = TICKS_PER_MINUTE / 60D;
    public static final double TICKS_PER_MILLISECOND = TICKS_PER_SECOND / 1000D;

    public static long[] getTime(Level level) {
        return new long[] {level.getDayTime(), level.getGameTime(), System.currentTimeMillis()};
    }

    public static int getDayTime(Level level) {
        return (int) (level.getDayTime() % 24000L);
    }

    public static int[] formatDayTime(long rawDayTime) {
        int dayTime = (int) (rawDayTime % 2147483647L);

        int day = dayTime / TICKS_PER_DAY;

        int ticks = dayTime - day * TICKS_PER_DAY;
        int hour = (int) (ticks / TICKS_PER_HOUR + 6) % 24;
        int min = (int) (ticks / TICKS_PER_MINUTE) % 60;
        int sec = (int) (ticks / TICKS_PER_SECOND) % 60;
        int msec = (int) (ticks / TICKS_PER_MILLISECOND) % 1000;

        return new int[] {day, hour, min, sec, msec};
    }

    public static int getDay(long dayTime) {
        return ((int) (dayTime % 2147483647L)) / TICKS_PER_DAY;
    }

}
