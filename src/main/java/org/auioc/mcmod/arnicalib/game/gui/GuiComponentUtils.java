package org.auioc.mcmod.arnicalib.game.gui;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;

@OnlyIn(Dist.CLIENT)
public class GuiComponentUtils {

    public static <T extends GuiEventListener> List<T> getEventListeners(List<GuiEventListener> listeners, Class<T> clazz) {
        return listeners.stream().filter(clazz::isInstance).map(clazz::cast).toList();
    }

    public static <T extends GuiEventListener> List<T> getEventListeners(ScreenEvent.InitScreenEvent event, Class<T> clazz) {
        return getEventListeners(event.getListenersList(), clazz);
    }


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

}
