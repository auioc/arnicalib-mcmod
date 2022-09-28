package org.auioc.mcmod.arnicalib.game.enchantment.visitor;

import org.auioc.mcmod.arnicalib.base.function.QuadConsumer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public interface QuadEnchantmentVisitor extends QuadConsumer<EquipmentSlot, ItemStack, Enchantment, Integer> {

}
