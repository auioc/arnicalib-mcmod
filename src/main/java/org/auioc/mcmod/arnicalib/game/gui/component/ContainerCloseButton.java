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

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContainerCloseButton extends CloseButton<AbstractContainerScreen<?>> {

    private final int paddingX;
    private final int paddingY;

    public ContainerCloseButton(int paddingX, int paddingY, AbstractContainerScreen<?> parentScreen) {
        super(0, 0, parentScreen);
        this.paddingX = paddingX;
        this.paddingY = paddingY;
    }

    @Override
    protected int overrideX() {
        return this.parentScreen.getGuiLeft() + this.parentScreen.getXSize() - this.paddingX - this.width;
    }

    @Override
    protected int overrideY() {
        return this.parentScreen.getGuiTop() + this.paddingY;
    }

}
