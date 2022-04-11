package org.auioc.mcmod.arnicalib.utils.game;

import java.util.function.Predicate;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

public interface ContainerUtils {

    static int countItem(ItemStack stack, Predicate<ItemStack> predicate) {
        if (!stack.isEmpty() && predicate.test(stack)) {
            return stack.getCount();
        }
        return 0;
    }

    static int countItem(Container container, Predicate<ItemStack> predicate) {
        int r = 0;
        for (int i = 0, l = container.getContainerSize(); i < l; i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                r += countItem(stack, predicate);
            }
        }
        return r;
    }

    static int countItem(Container container) {
        return countItem(container, (stack) -> true);
    }


    static int clearItem(ItemStack stack, Predicate<ItemStack> predicate, int count, boolean required) {
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

    static int clearItem(Container container, Predicate<ItemStack> predicate, int count, boolean required) {
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
