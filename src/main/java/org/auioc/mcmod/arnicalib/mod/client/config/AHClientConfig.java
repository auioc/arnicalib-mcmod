package org.auioc.mcmod.arnicalib.mod.client.config;

import org.auioc.mcmod.arnicalib.game.config.ConfigUtils;
import org.auioc.mcmod.arnicalib.mod.client.widget.AdvancedItemTooltip;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;

@OnlyIn(Dist.CLIENT)
public class AHClientConfig {

    public static final ForgeConfigSpec CONFIG;

    static {
        final ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        ConfigUtils.push(b, "advanced_item_tooltip", AdvancedItemTooltip.Config::build);

        CONFIG = b.build();
    }

}
