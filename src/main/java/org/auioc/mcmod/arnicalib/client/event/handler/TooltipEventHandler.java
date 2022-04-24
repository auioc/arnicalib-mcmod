package org.auioc.mcmod.arnicalib.client.event.handler;

import static org.auioc.mcmod.arnicalib.utils.game.TextUtils.StringText;
import com.mojang.blaze3d.platform.InputConstants;
import org.auioc.mcmod.arnicalib.client.config.ClientConfig;
import org.lwjgl.glfw.GLFW;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
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
        if (!ClientConfig.EnableAdvancedTooltip.get()) {
            return;
        }

        ItemStack itemStack = event.getItemStack();

        if (itemStack.isEmpty()) {
            return;
        }

        if (itemStack.hasTag()) {
            CompoundTag nbt = itemStack.getTag();
            Component nbtTooltip = StringText("NBT:").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY))
                .append(StringText("").setStyle(Style.EMPTY.withColor(ChatFormatting.WHITE)).append(NbtUtils.toPrettyComponent(nbt)));
            addLine(event, nbtTooltip);
        }

        var tags = itemStack.getTags().toList();
        if (tags.size() > 0) {
            addLine(event, StringText("Tags:").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            for (TagKey<Item> tag : tags) {
                addLine(event, StringText("    " + tag.location()).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
            }
        }

    }

    private static void addLine(ItemTooltipEvent event, Component tooltip) {
        if (ClientConfig.AdvancedTooltipOnlyOnDebug.get() && !isDebugMode()) {
            return;
        }
        if (ClientConfig.AdvancedTooltipOnlyOnShift.get() && !isShiftKeyDown()) {
            return;
        }
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
