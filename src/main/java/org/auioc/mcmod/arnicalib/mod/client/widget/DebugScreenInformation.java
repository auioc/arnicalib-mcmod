package org.auioc.mcmod.arnicalib.mod.client.widget;

import java.util.ArrayList;
import org.auioc.mcmod.arnicalib.base.collection.ListUtils;
import org.auioc.mcmod.arnicalib.game.world.position.SpeedUnit;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;

public class DebugScreenInformation {

    private static final Minecraft MC = Minecraft.getInstance();

    public static void handle(ArrayList<String> gameInfo, ArrayList<String> systemInfo) {
        if (Config.enabled.get()) {
            if (!MC.options.reducedDebugInfo) handleGameInformation(gameInfo);
            handleSystemInformation(systemInfo);
        }
    }

    private static void handleGameInformation(ArrayList<String> info) {
        if (Config.speedmeterEnabled.get()) {
            var unit = Config.speedmeterUnit.get();
            var symbol = Config.speedmeterShowUnitSymbol.get() ? String.format(" (%s)", unit.getSymbol()) : "";

            var entity = MC.cameraEntity;
            double vX = unit.convertFrom(entity.getX() - entity.xOld);
            double vY = unit.convertFrom(entity.getY() - entity.yOld);
            double vZ = unit.convertFrom(entity.getZ() - entity.zOld);
            double vA = Math.sqrt(vX * vX + vY * vY + vZ * vZ);

            var velocity = String.format("Velocity: %.3f / %.3f / %.3f%s", vX, vY, vZ, symbol);
            var speed = String.format("Speed: %.3f%s", vA, symbol);

            int at = ListUtils.indexOf(info, (l) -> l.startsWith("XYZ")) + 1;
            ListUtils.add(info, at, speed);
            ListUtils.add(info, at, velocity);
        }
    }

    private static void handleSystemInformation(ArrayList<String> info) {

    }


    public static class Config {

        public static BooleanValue enabled;

        public static BooleanValue speedmeterEnabled;
        public static EnumValue<SpeedUnit> speedmeterUnit;
        public static BooleanValue speedmeterShowUnitSymbol;

        public static void build(final ForgeConfigSpec.Builder b) {
            enabled = b.define("enabled", true);

            {
                b.push("speedmeter");
                speedmeterEnabled = b.define("enabled", true);
                speedmeterUnit = b.defineEnum("unit", SpeedUnit.METRES_PER_SECOND);
                speedmeterShowUnitSymbol = b.define("show_unit_symbol", true);
                b.pop();
            }
        }

    }

}
