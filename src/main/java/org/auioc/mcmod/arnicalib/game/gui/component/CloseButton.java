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

package org.auioc.mcmod.arnicalib.game.gui.component;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.gui.screen.HScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CloseButton<T extends Screen> extends AbstractButton {

    private static final ResourceLocation TEXTURE = ArnicaLib.id("textures/gui/close_button.png");
    private static final int TEXTURE_SIZE = 16;

    public static final int CROSS_SIZE = 5;
    private static final int CROSS_U_OFFSET = 0;
    private static final int CROSS_V_OFFSET = 0;
    private static final int CROSS_HOVERED_U_OFFSET = 5;
    private static final int CROSS_HOVERED_V_OFFSET = 0;

    protected final T parentScreen;

    public CloseButton(int x, int y, T parentScreen) {
        super(x, y, CROSS_SIZE, CROSS_SIZE, Component.literal("Close button"));
        this.parentScreen = parentScreen;
    }

    @Override
    public final void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        setX(overrideX());
        setY(overrideY());
        boolean flag = isHoveredOrFocused();
        HScreen.blitSquare(
            guiGraphics, TEXTURE, this.getX(), this.getY(),
            (flag) ? CROSS_HOVERED_U_OFFSET : CROSS_U_OFFSET,
            (flag) ? CROSS_HOVERED_V_OFFSET : CROSS_V_OFFSET,
            CROSS_SIZE, TEXTURE_SIZE
        );
    }

    protected int overrideX() { return getX(); }

    protected int overrideY() { return getY(); }

    @Override
    public void onPress() {
        this.parentScreen.onClose();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
        narrationElementOutput.add(NarratedElementType.HINT, getMessage());
    }

}
