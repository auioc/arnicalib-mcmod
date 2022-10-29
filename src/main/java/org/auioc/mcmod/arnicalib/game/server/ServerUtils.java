package org.auioc.mcmod.arnicalib.game.server;

import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.server.ServerLifecycleHooks;

@SuppressWarnings("resource")
public class ServerUtils {

    private static final Pair<Double, Double> UNLOADED_LEVEL_TICK = Pair.of(-1.0D, -1.0D);

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static Pair<Double, Double> calcTpsMspt(long[] tickArray) {
        if (tickArray == null) return UNLOADED_LEVEL_TICK;

        long sum = 0L;
        for (long tickTime : tickArray) sum += tickTime;

        double mspt = (sum / tickArray.length) * 1.0E-006D;
        double tps = Math.min(1000.0D / mspt, 20.0D);

        return Pair.of(tps, mspt);
    }

    public static Pair<Double, Double> getTpsMspt() {
        return calcTpsMspt(getServer().tickTimes);
    }

    public static Pair<Double, Double> getTpsMspt(ServerLevel level) {
        return calcTpsMspt(getServer().getTickTime(level.dimension()));
    }

}
