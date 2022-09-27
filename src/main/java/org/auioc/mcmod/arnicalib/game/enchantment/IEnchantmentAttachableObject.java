package org.auioc.mcmod.arnicalib.game.enchantment;

import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.world.item.enchantment.Enchantment;

public interface IEnchantmentAttachableObject {

    @Nullable
    default Map<Enchantment, Integer> getEnchantments() {
        return null;
    }

    default void addEnchantment(Enchantment ench, int lvl) {
        throw new UnsupportedOperationException("Adding enchantment is not supported by default");
    }

}
