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

package org.auioc.mcmod.arnicalib.game.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HScreen extends Screen {

    public static final int DEFAULT_TEXTURE_SIZE = 256;

    public HScreen(Component title) {
        super(title);
    }

    public static int center(int a) {
        return a / 2;
    }

    public static int center(int a, int b) {
        return (a - b) / 2;
    }

    public static void blitSquare(GuiGraphics guiGraphics, ResourceLocation texture, int x, int y, int size) {
        //              X, Y, U, V, W,    H,    TW,   TH
        guiGraphics.blit(texture, x, y, 0, 0, size, size, DEFAULT_TEXTURE_SIZE, DEFAULT_TEXTURE_SIZE);
    }

    public static void blitSquare(GuiGraphics guiGraphics, ResourceLocation texture, int x, int y, int size, int textureSize) {
        guiGraphics.blit(texture, x, y, 0, 0, size, size, textureSize, textureSize);
    }

    public static void blitSquare(GuiGraphics guiGraphics, ResourceLocation texture, int x, int y, int u, int v, int size, int textureSize) {
        guiGraphics.blit(texture, x, y, u, v, size, size, textureSize, textureSize);
    }

    public static void blit(GuiGraphics guiGraphics, ResourceLocation texture, int x, int y, int u, int v, int w, int h, int textureSize) {
        guiGraphics.blit(texture, x, y, u, v, w, h, textureSize, textureSize);
    }

    public static int adjustColor(int rgbColor) {
        return (rgbColor & -67108864) == 0 ? rgbColor | -16777216 : rgbColor;
    }

    public static void closeScreen() {
        Minecraft.getInstance().setScreen((Screen) null);
    }

}
