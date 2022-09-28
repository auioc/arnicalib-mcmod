package org.auioc.mcmod.arnicalib.game.enchantment.visitor;

import org.auioc.mcmod.arnicalib.base.function.TriConsumer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public interface TriEnchantmentVisitor extends TriConsumer<ItemStack, Enchantment, Integer> {

}
