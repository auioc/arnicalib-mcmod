package org.auioc.mcmod.arnicalib.game.gui;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;

/**
 * @deprecated Use {@link GuiUtils} instead
 */
@Deprecated(since = "5.7.3", forRemoval = true)
@OnlyIn(Dist.CLIENT)
public class GuiComponentUtils {

    public static <T extends GuiEventListener> List<T> getEventListeners(List<GuiEventListener> listeners, Class<T> clazz) {
        return GuiUtils.getEventListeners(listeners, clazz);
    }

    public static <T extends GuiEventListener> List<T> getEventListeners(ScreenEvent.InitScreenEvent event, Class<T> clazz) {
        return GuiUtils.getEventListeners(event, clazz);
    }

    public static List<Button> getButtons(List<GuiEventListener> listeners, Predicate<Button> predicate) {
        return GuiUtils.getButtons(listeners, predicate);
    }

    public static Optional<Button> getButton(List<GuiEventListener> listeners, Predicate<Button> predicate) {
        return GuiUtils.getButton(listeners, predicate);
    }

    public static Optional<Button> getButton(ScreenEvent.InitScreenEvent event, String messageString) {
        return GuiUtils.getButton(event, messageString);
    }

}
