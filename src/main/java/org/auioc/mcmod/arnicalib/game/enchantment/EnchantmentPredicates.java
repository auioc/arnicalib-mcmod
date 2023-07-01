package org.auioc.mcmod.arnicalib.game.enchantment;

import java.util.function.Predicate;
import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;
import org.auioc.mcmod.arnicalib.game.registry.VanillaPredicates;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;

public class EnchantmentPredicates {

    public static final Predicate<Enchantment> IS_VANILLA = (e) -> VanillaPredicates.ID.test(RegistryUtils.id(e));

    public static final Predicate<Enchantment> IS_TREASURE_ONLY = (e) -> e.isTreasureOnly();
    public static final Predicate<Enchantment> IS_CURSE = (e) -> e.isCurse();
    public static final Predicate<Enchantment> IS_TRADEABLE = (e) -> e.isTradeable();
    public static final Predicate<Enchantment> IS_DISCOVERABLE = (e) -> e.isDiscoverable();

    public static final Predicate<Enchantment> IS_COMMON = (e) -> e.getRarity() == Rarity.COMMON;
    public static final Predicate<Enchantment> IS_UNCOMMON = (e) -> e.getRarity() == Rarity.UNCOMMON;
    public static final Predicate<Enchantment> IS_RARE = (e) -> e.getRarity() == Rarity.RARE;
    public static final Predicate<Enchantment> IS_VERY_RARE = (e) -> e.getRarity() == Rarity.VERY_RARE;

}
