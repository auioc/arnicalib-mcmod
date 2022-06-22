package org.auioc.mcmod.arnicalib.utils.game;

import net.minecraft.world.level.Level;

public interface MCTimeUtils {

    int SUNRISE = 23000;
    int DAY_BEGIN = 0;
    int DAY = 1000;
    int NOON = 6000;
    int SUNSET = 12000;
    int NIGHT = 13000;
    int MIDNIGHT = 18000;
    int DAY_END = 24000;

    int TICKS_PER_DAY = 24000;
    int TICKS_PER_HOUR = 1000;
    double TICKS_PER_MINUTE = 1000D / 60D;
    double TICKS_PER_SECOND = TICKS_PER_MINUTE / 60D;
    double TICKS_PER_MILLISECOND = TICKS_PER_SECOND / 1000D;

    static long[] getTime(Level level) {
        return new long[] {level.getDayTime(), level.getGameTime(), System.currentTimeMillis()};
    }

    static int[] formatDayTime(long rawDayTime) {
        int dayTime = (int) (rawDayTime % 2147483647L);

        int day = dayTime / TICKS_PER_DAY;

        int ticks = dayTime - day * TICKS_PER_DAY;
        int hour = (int) (ticks / TICKS_PER_HOUR + 6) % 24;
        int min = (int) (ticks / TICKS_PER_MINUTE) % 60;
        int sec = (int) (ticks / TICKS_PER_SECOND) % 60;
        int msec = (int) (ticks / TICKS_PER_MILLISECOND) % 1000;

        return new int[] {day, hour, min, sec, msec};
    }

    static int getDay(long dayTime) {
        return ((int) (dayTime % 2147483647L)) / TICKS_PER_DAY;
    }

}
