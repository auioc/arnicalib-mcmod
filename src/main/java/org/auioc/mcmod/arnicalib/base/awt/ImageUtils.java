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

package org.auioc.mcmod.arnicalib.base.awt;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;

import org.auioc.mcmod.arnicalib.base.function.BiIntFunction;
import org.auioc.mcmod.arnicalib.base.validate.Validate;

public class ImageUtils {

    public static Color getColor(BufferedImage image, int x, int y) {
        Validate.isTrue(image.getColorModel().getColorSpace().getType() == ColorSpace.TYPE_RGB);
        var pixel = image.getData().getPixel(x, y, new int[4]);
        return new Color(pixel[0], pixel[1], pixel[2], pixel[3]);
    }

    public static Color[] getColors(BufferedImage image, int x, int y, int w, int h) {
        int pixelCount = w * h;
        var colors = new Color[pixelCount];
        int i = 0;
        for (int _y = y, y0 = y + h; _y < y0; ++_y) {
            for (int _x = x, x0 = x + w; _x < x0; ++_x) {
                colors[i] = getColor(image, _x, _y);
                i++;
            }
        }
        return colors;
    }

    public static Color[] getColors(BufferedImage image, BiIntFunction<Rectangle> rectangle) {
        var rect = rectangle.apply(image.getWidth(), image.getHeight());
        return getColors(image, rect.x, rect.y, rect.width, rect.height);
    }

    public static int[] getARGBColors(BufferedImage image, BiIntFunction<Rectangle> rectangle) {
        var colors = ImageUtils.getColors(image, rectangle);
        var argbColors = new int[colors.length];
        for (int i = 0; i < colors.length; i++) argbColors[i] = colors[i].getRGB();
        return argbColors;
    }

}
