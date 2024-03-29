/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.FormattedCharSequence;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.ScreenEvent;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@OnlyIn(Dist.CLIENT)
public class GuiUtils {

    public static <T extends GuiEventListener> List<T> getEventListeners(List<GuiEventListener> listeners, Class<T> clazz) {
        return listeners.stream().filter(clazz::isInstance).map(clazz::cast).toList();
    }

    public static <T extends GuiEventListener> List<T> getEventListeners(ScreenEvent.Init event, Class<T> clazz) {
        return getEventListeners(event.getListenersList(), clazz);
    }

    // ====================================================================== //

    public static List<Button> getButtons(List<GuiEventListener> listeners, Predicate<Button> predicate) {
        return getEventListeners(listeners, Button.class).stream().filter(predicate).toList();
    }

    public static Optional<Button> getButton(List<GuiEventListener> listeners, Predicate<Button> predicate) {
        return getButtons(listeners, predicate).stream().findFirst();
    }

    public static Optional<Button> getButton(ScreenEvent.Init event, String messageString) {
        return getButton(event.getListenersList(), (button) -> {
            var contents = button.getMessage().getContents();
            String message;
            if (contents instanceof TranslatableContents t) message = t.getKey();
            else if (contents instanceof PlainTextContents t) message = t.text();
            else message = button.getMessage().getString();
            return message.equals(messageString);
        });
    }

    // ============================================================================================================== //

    public static int drawCharSequence(GuiGraphics guiGraphics, Font font, List<FormattedCharSequence> lines, int x, int y, int lineHeight, int color) {
        for (var line : lines) {
            guiGraphics.drawString(font, line, x, y, color, false);
            y += lineHeight;
        }
        return y;
    }

    public static int drawMultilineText(GuiGraphics guiGraphics, Font font, List<FormattedText> texts, int x, int y, int color) {
        return drawCharSequence(guiGraphics, font, Language.getInstance().getVisualOrder(texts), x, y, font.lineHeight, color);
    }

    public static int drawMultilineText(GuiGraphics guiGraphics, Font font, List<FormattedText> texts, int x, int y, int lineHeight, int color) {
        return drawCharSequence(guiGraphics, font, Language.getInstance().getVisualOrder(texts), x, y, lineHeight, color);
    }

    public static int drawTextWarp(GuiGraphics guiGraphics, Font font, FormattedText text, int x, int y, int width, int color) {
        return drawCharSequence(guiGraphics, font, font.split(text, width), x, y, font.lineHeight, color);
    }

    // ============================================================================================================== //

    public static final int SLOT_SIZE = 18;
    public static final int SLOT_BACKGROUND_COLOR = 0xFF8B8B8B;
    public static final int SLOT_LEFT_BORDER_COLOR = 0xFF373737;
    public static final int SLOT_RIGHT_BORDER_COLOR = 0xFFFFFFFF;

    public static void drawSlot(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        int x2 = x + width;
        int y2 = y + height;
        guiGraphics.fill(x, y, x2, y2, SLOT_BACKGROUND_COLOR);
        guiGraphics.fill(x, y, x2 - 1, y2 - 1, SLOT_LEFT_BORDER_COLOR);
        guiGraphics.fill(x + 1, y + 1, x2, y2, SLOT_RIGHT_BORDER_COLOR);
        guiGraphics.fill(x + 1, y + 1, x2 - 1, y2 - 1, SLOT_BACKGROUND_COLOR);
    }

    public static void drawSlot(GuiGraphics guiGraphics, int x, int y) {
        drawSlot(guiGraphics, x, y, SLOT_SIZE, SLOT_SIZE);
    }

    public static void drawSlots(GuiGraphics guiGraphics, int x, int y, int rows, int cols) {
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                drawSlot(guiGraphics, (x + c * SLOT_SIZE), (y + r * SLOT_SIZE));
            }
        }
    }

}
