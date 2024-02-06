package org.auioc.mcmod.arnicalib.game.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class ServerUtils {

    private static final TpsRecord UNLOADED_LEVEL_TICK = new TpsRecord(-1.0D, -1.0D);

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static TpsRecord calc(long[] tickArray) {
        if (tickArray == null) return UNLOADED_LEVEL_TICK;

        long sum = 0L;
        for (long tickTime : tickArray) sum += tickTime;

        double mspt = (sum / tickArray.length) * 1.0E-006D;
        double tps = Math.min(1000.0D / mspt, 20.0D);

        return new TpsRecord(mspt, tps);
    }

    public static TpsRecord getTpsMspt() {
        return calc(getServer().getTickTimesNanos());
    }

    public static TpsRecord getTpsMspt(ServerLevel level) {
        return calc(getServer().getTickTime(level.dimension()));
    }

}
