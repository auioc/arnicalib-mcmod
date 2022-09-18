package org.auioc.mcmod.arnicalib.api.game.enchantment;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.auioc.mcmod.arnicalib.api.java.function.QuadConsumer;
import org.auioc.mcmod.arnicalib.api.java.function.TriConsumer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public interface EnchantmentVisitor extends Consumer<Enchantment> {

    public interface BiEnchantmentVisitor extends BiConsumer<Enchantment, Integer> {

    }

    public interface TriEnchantmentVisitor extends TriConsumer<ItemStack, Enchantment, Integer> {

    }

    public interface QuadEnchantmentVisitor extends QuadConsumer<EquipmentSlot, ItemStack, Enchantment, Integer> {

    }

}
