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
        if (!MC.options.reducedDebugInfo) handleGameInformation(gameInfo);
        handleSystemInformation(systemInfo);
    }

    private static void handleGameInformation(ArrayList<String> info) {
        if (Config.enabledTweaks.get()) {
            if (Config.hideLeftSide.get() && _clear(info)) return;
        }
        if (Config.enabledAddons.get()) {
            if (Config.speedmeterEnabled.get()) _addSpeedmeter(info);
        }
    }

    private static void handleSystemInformation(ArrayList<String> info) {
        if (Config.enabledTweaks.get()) {
            if (Config.hideRightSide.get() && _clear(info)) return;
            if (Config.hideSystemInformation.get()) _removeSystemInfo(info);
        }
    }


    private static boolean _clear(ArrayList<String> info) {
        info.clear();
        return true;
    }

    private static void _addSpeedmeter(ArrayList<String> info) {
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

    private static void _removeSystemInfo(ArrayList<String> info) {
        int targetedIndex = ListUtils.indexOf(info, (l) -> l.startsWith("§nTargeted"));
        int toIndex = targetedIndex != -1 ? targetedIndex : info.size();
        info.subList(0, toIndex).clear();
    }


    public static class Config {

        public static BooleanValue enabledAddons;
        public static BooleanValue speedmeterEnabled;
        public static EnumValue<SpeedUnit> speedmeterUnit;
        public static BooleanValue speedmeterShowUnitSymbol;

        public static BooleanValue enabledTweaks;
        public static BooleanValue hideLeftSide;
        public static BooleanValue hideRightSide;
        public static BooleanValue hideSystemInformation;

        public static void build(final ForgeConfigSpec.Builder b) {
            {
                b.push("addons");
                enabledAddons = b.define("enabled", true);
                {
                    b.push("speedmeter");
                    speedmeterEnabled = b.define("enabled", true);
                    speedmeterUnit = b.defineEnum("unit", SpeedUnit.METRES_PER_SECOND);
                    speedmeterShowUnitSymbol = b.define("show_unit_symbol", true);
                    b.pop();
                }
                b.pop();
            }

            {
                b.push("tweaks");
                enabledTweaks = b.define("enabled", false);
                hideLeftSide = b.define("hide_left_side", false);
                hideRightSide = b.define("hide_right_side", false);
                hideSystemInformation = b.define("hide_system_information", false);
                b.pop();
            }
        }

    }

}
