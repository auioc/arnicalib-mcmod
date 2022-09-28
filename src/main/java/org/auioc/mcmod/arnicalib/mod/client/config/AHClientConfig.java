package org.auioc.mcmod.arnicalib.mod.client.config;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

@OnlyIn(Dist.CLIENT)
public class AHClientConfig {

    public static final ForgeConfigSpec CONFIG;

    public static final BooleanValue enableAdvancedTooltip;
    public static final BooleanValue advancedTooltipOnlyOnDebug;
    public static final BooleanValue advancedTooltipOnlyOnShift;

    static {
        final ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        {
            b.push("advanced_tooltip");

            enableAdvancedTooltip = b.define("enable", true);
            advancedTooltipOnlyOnDebug = b.define("only_on_debug", true);
            advancedTooltipOnlyOnShift = b.define("only_on_shift", false);

            b.pop();
        }

        CONFIG = b.build();
    }

}
