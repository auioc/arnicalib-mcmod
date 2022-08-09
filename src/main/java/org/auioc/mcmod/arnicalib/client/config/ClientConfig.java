package org.auioc.mcmod.arnicalib.client.config;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

@OnlyIn(Dist.CLIENT)
public class ClientConfig {

    public static final ForgeConfigSpec CONFIG;

    public static final BooleanValue EnableAdvancedTooltip;
    public static final BooleanValue AdvancedTooltipOnlyOnDebug;
    public static final BooleanValue AdvancedTooltipOnlyOnShift;

    static {
        final ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        {
            b.push("advanced_tooltip");

            EnableAdvancedTooltip = b.define("enable", true);
            AdvancedTooltipOnlyOnDebug = b.define("only_on_debug", true);
            AdvancedTooltipOnlyOnShift = b.define("only_on_shift", false);

            b.pop();
        }

        CONFIG = b.build();
    }

}
