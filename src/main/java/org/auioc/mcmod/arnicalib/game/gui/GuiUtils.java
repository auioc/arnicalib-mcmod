package org.auioc.mcmod.arnicalib.game.gui;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;

@OnlyIn(Dist.CLIENT)
public class GuiUtils {

    public static <T extends GuiEventListener> List<T> getEventListeners(List<GuiEventListener> listeners, Class<T> clazz) {
        return listeners.stream().filter(clazz::isInstance).map(clazz::cast).toList();
    }

    public static <T extends GuiEventListener> List<T> getEventListeners(ScreenEvent.InitScreenEvent event, Class<T> clazz) {
        return getEventListeners(event.getListenersList(), clazz);
    }

    // ====================================================================== //

    public static List<Button> getButtons(List<GuiEventListener> listeners, Predicate<Button> predicate) {
        return getEventListeners(listeners, Button.class).stream().filter(predicate).toList();
    }

    public static Optional<Button> getButton(List<GuiEventListener> listeners, Predicate<Button> predicate) {
        return getButtons(listeners, predicate).stream().findFirst();
    }

    public static Optional<Button> getButton(ScreenEvent.InitScreenEvent event, String messageString) {
        return getButton(event.getListenersList(), (button) -> {
            var message = button.getMessage();
            String _mStr;
            if (message instanceof TranslatableComponent t) _mStr = t.getKey();
            else if (message instanceof TextComponent t) _mStr = t.getText();
            else _mStr = message.getString();
            return _mStr.equals(messageString);
        });
    }

    // ============================================================================================================== //

    public static int drawCharSequence(PoseStack poseStack, Font font, List<FormattedCharSequence> lines, int x, int y, int lineHeight, int color) {
        for (var line : lines) {
            font.draw(poseStack, line, x, y, color);
            y += lineHeight;
        }
        return y;
    }

    public static int drawMultilineText(PoseStack poseStack, Font font, List<FormattedText> texts, int x, int y, int color) {
        return drawCharSequence(poseStack, font, Language.getInstance().getVisualOrder(texts), x, y, font.lineHeight, color);
    }

    public static int drawMultilineText(PoseStack poseStack, Font font, List<FormattedText> texts, int x, int y, int lineHeight, int color) {
        return drawCharSequence(poseStack, font, Language.getInstance().getVisualOrder(texts), x, y, lineHeight, color);
    }

    public static int drawTextWarp(PoseStack poseStack, Font font, FormattedText text, int x, int y, int width, int color) {
        return drawCharSequence(poseStack, font, font.split(text, width), x, y, font.lineHeight, color);
    }

}
