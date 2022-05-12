package org.auioc.mcmod.arnicalib.client.event.handler;

import static org.auioc.mcmod.arnicalib.utils.game.TextUtils.I18nText;
import static org.auioc.mcmod.arnicalib.utils.game.TextUtils.StringText;
import com.mojang.blaze3d.platform.InputConstants;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.client.config.ClientConfig;
import org.lwjgl.glfw.GLFW;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

@OnlyIn(Dist.CLIENT)
public class TooltipEventHandler {

    private static Minecraft mc = Minecraft.getInstance();

    public static void handle(ItemTooltipEvent event) {
        if (!ClientConfig.EnableAdvancedTooltip.get()) return;

        ItemStack itemStack = event.getItemStack();
        if (itemStack.isEmpty()) return;

        var darkGary = Style.EMPTY.withColor(ChatFormatting.DARK_GRAY);

        if (itemStack.hasTag()) {
            addLine(
                event,
                I18nText(ArnicaLib.i18n("advanced_tooltip.nbt"))
                    .setStyle(darkGary)
                    .append(
                        StringText("")
                            .setStyle(Style.EMPTY.withColor(ChatFormatting.WHITE))
                            .append(NbtUtils.toPrettyComponent(itemStack.getTag()))
                    )
            );
        }

        var tags = itemStack.getTags().toList();
        if (tags.size() > 0) {
            addLine(event, I18nText(ArnicaLib.i18n("advanced_tooltip.tag")).setStyle(darkGary));
            for (TagKey<Item> tag : tags) {
                addLine(event, StringText("  " + tag.location()).setStyle(darkGary));
            }
        }

    }

    private static void addLine(ItemTooltipEvent event, Component tooltip) {
        if (ClientConfig.AdvancedTooltipOnlyOnDebug.get() && !isDebugMode()) return;
        if (ClientConfig.AdvancedTooltipOnlyOnShift.get() && !isShiftKeyDown()) return;
        event.getToolTip().add(tooltip);
    }

    private static boolean isDebugMode() {
        return mc.options.advancedItemTooltips;
    }

    private static boolean isShiftKeyDown() {
        return InputConstants.isKeyDown(mc.getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) ||
            InputConstants.isKeyDown(mc.getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

}
