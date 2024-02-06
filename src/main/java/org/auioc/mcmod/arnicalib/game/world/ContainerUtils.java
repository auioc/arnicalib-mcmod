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

package org.auioc.mcmod.arnicalib.game.world;

import java.util.function.Predicate;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

public class ContainerUtils {

    public static int countItem(ItemStack stack, Predicate<ItemStack> predicate) {
        if (!stack.isEmpty() && predicate.test(stack)) {
            return stack.getCount();
        }
        return 0;
    }

    public static int countItem(Container container, Predicate<ItemStack> predicate) {
        int r = 0;
        for (int i = 0, l = container.getContainerSize(); i < l; i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                r += countItem(stack, predicate);
            }
        }
        return r;
    }

    public static int countItem(Container container) {
        return countItem(container, (stack) -> true);
    }


    public static int clearItem(ItemStack stack, Predicate<ItemStack> predicate, int count, boolean required) {
        if (count > 0 && !stack.isEmpty() && predicate.test(stack)) {
            int r = 0;
            if (stack.getCount() < count) {
                if (required) {
                    return stack.getCount() - count;
                }
                r = stack.getCount();
            } else {
                r = count;
            }
            stack.shrink(r);
            return r;
        }
        return 0;
    }

    public static int clearItem(Container container, Predicate<ItemStack> predicate, int count, boolean required) {
        if (count <= 0) {
            return 0;
        }

        {
            int c = countItem(container, predicate);
            if (required && c < count) {
                return c - count;
            }
        }

        int r = 0;
        for (int i = 0, l = container.getContainerSize(); i < l; i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                int c = clearItem(stack, predicate, count - r, false);
                if (c > 0 && stack.isEmpty()) {
                    container.setItem(i, ItemStack.EMPTY);
                }
                r += c;
                if (r >= count) {
                    return r;
                }
            }
        }
        return r - count;
    }

}
