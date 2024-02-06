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

import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

import org.auioc.mcmod.arnicalib.game.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlainTextWidget extends AbstractWidget {

    protected final Minecraft minecraft;
    protected final Font font;

    public PlainTextWidget(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
        this.minecraft = Minecraft.getInstance();
        this.font = this.minecraft.font;
    }

    public PlainTextWidget(int x, int y, int w, Component message) {
        this(x, y, w, 0, message);
        this.update(x, y, w, message);
    }

    public PlainTextWidget(int x, int y, Component message) {
        this(x, y, 0, 0, message);
        this.update(x, y, font.width(message), font.lineHeight, message);
    }

    // ====================================================================== //

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        GuiUtils.drawTextWarp(guiGraphics, font, getMessage(), getX(), getY(), width, (0 | Mth.ceil(this.alpha * 255.0F) << 24));
        if (this.isHovered()) {
            this.renderToolTip(guiGraphics, mouseX, mouseY);
        }
    }

    public void renderToolTip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        var tooltips = getTooltips();
        if (tooltips != null) {
            guiGraphics.renderTooltip(font, tooltips, Optional.empty(), mouseX, mouseY);
        }
    }

    // ====================================================================== //

    public void update(int x, int y, int width, int height, Component message) {
        setX(x);
        setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setMessage(message);
    }

    public void update(int x, int y, int width, Component message) {
        update(x, y, width, (font.split(message, width).size() * font.lineHeight), message);
    }

    public void update(int width, Component message) {
        update(getX(), getY(), width, message);
    }

    public void update(Component message) {
        update(getX(), getY(), font.width(message), font.lineHeight, message);
    }

    // ====================================================================== //

    @Override
    public void onClick(double mouseX, double mouseY) { }

    @Nullable
    public List<Component> getTooltips() { return null; }

    // ====================================================================== //

    @Override
    public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

}
