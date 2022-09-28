package org.auioc.mcmod.arnicalib.mod.client.event.handler;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import org.auioc.mcmod.arnicalib.mod.client.config.AHClientConfig;
import org.lwjgl.glfw.GLFW;
import com.mojang.blaze3d.platform.InputConstants;
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
        if (!AHClientConfig.enableAdvancedTooltip.get()) return;
        if (AHClientConfig.advancedTooltipOnlyOnDebug.get() && !isDebugMode()) return;
        if (AHClientConfig.advancedTooltipOnlyOnShift.get() && !isShiftKeyDown()) return;

        ItemStack itemStack = event.getItemStack();
        if (itemStack.isEmpty()) return;

        var darkGary = Style.EMPTY.withColor(ChatFormatting.DARK_GRAY);

        if (itemStack.hasTag()) {
            addLine(
                event,
                TextUtils.translatable(ArnicaLib.i18n("advanced_tooltip.nbt"))
                    .setStyle(darkGary)
                    .append(
                        TextUtils.empty()
                            .setStyle(Style.EMPTY.withColor(ChatFormatting.WHITE))
                            .append(NbtUtils.toPrettyComponent(itemStack.getTag()))
                    )
            );
        }

        var tags = itemStack.getTags().toList();
        if (tags.size() > 0) {
            addLine(event, TextUtils.translatable(ArnicaLib.i18n("advanced_tooltip.tag")).setStyle(darkGary));
            for (TagKey<Item> tag : tags) {
                addLine(event, TextUtils.literal("  " + tag.location()).setStyle(darkGary));
            }
        }

    }

    private static void addLine(ItemTooltipEvent event, Component tooltip) {
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
