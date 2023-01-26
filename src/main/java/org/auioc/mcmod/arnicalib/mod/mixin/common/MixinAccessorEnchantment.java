package org.auioc.mcmod.arnicalib.mod.mixin.common;

import org.auioc.mcmod.arnicalib.game.enchantment.IValidSlotsVisibleEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

@Mixin(value = Enchantment.class)
public interface MixinAccessorEnchantment extends IValidSlotsVisibleEnchantment {

    @Accessor("slots")
    EquipmentSlot[] getValidSlots();

}
