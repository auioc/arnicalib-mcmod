package org.auioc.mcmod.arnicalib.game.enchantment.visitor;

import java.util.function.BiConsumer;
import net.minecraft.world.item.enchantment.Enchantment;

public interface BiEnchantmentVisitor extends BiConsumer<Enchantment, Integer> {

}
