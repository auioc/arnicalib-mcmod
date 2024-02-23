/*
 * Copyright (C) 2024 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.game.enchantment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Map;
import java.util.Optional;

public record HEnchantmentPredicate(
    Optional<HolderSet<Enchantment>> enchantments,
    MinMaxBounds.Ints level,
    Optional<HEnchantmentPropertiesPredicate> properties
) {

    @SuppressWarnings("deprecation")
    public boolean test(Enchantment ench, int lvl) {
        if (level != MinMaxBounds.Ints.ANY && !level.matches(lvl)) {
            return false;
        }
        if (enchantments.isPresent() && enchantments.get().contains(ench.builtInRegistryHolder())) {
            return true;
        }
        if (properties.isPresent() && !properties.get().test(ench)) {
            return false;
        }
        return true;
    }

    public boolean test(Map.Entry<Enchantment, Integer> enchEntry) {
        return test(enchEntry.getKey(), enchEntry.getValue());
    }

    // ============================================================================================================== //

    private static final Codec<HolderSet<Enchantment>> ENCHANTMENT_CODEC = BuiltInRegistries.ENCHANTMENT
        .holderByNameCodec()
        .listOf()
        .xmap(HolderSet::direct, holderSet -> holderSet.stream().toList());

    public static final Codec<HEnchantmentPredicate> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
                ExtraCodecs.strictOptionalField(ENCHANTMENT_CODEC, "enchantments").forGetter(HEnchantmentPredicate::enchantments),
                ExtraCodecs.strictOptionalField(MinMaxBounds.Ints.CODEC, "level", MinMaxBounds.Ints.ANY).forGetter(HEnchantmentPredicate::level),
                ExtraCodecs.strictOptionalField(HEnchantmentPropertiesPredicate.CODEC, "properties").forGetter(HEnchantmentPredicate::properties)
            )
            .apply(instance, HEnchantmentPredicate::new)
    );

    // ============================================================================================================== //

    public static HEnchantmentPredicate.Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Optional<HolderSet<Enchantment>> enchantments = Optional.empty();
        private MinMaxBounds.Ints level = MinMaxBounds.Ints.ANY;
        private Optional<HEnchantmentPropertiesPredicate> properties = Optional.empty();

        private Builder() { }

        @SuppressWarnings("deprecation")
        public Builder enchantments(Enchantment... enchantments) {
            this.enchantments = Optional.of(HolderSet.direct(Enchantment::builtInRegistryHolder, enchantments));
            return this;
        }

        public Builder level(MinMaxBounds.Ints level) {
            this.level = level;
            return this;
        }

        public Builder properties(HEnchantmentPropertiesPredicate properties) {
            this.properties = Optional.of(properties);
            return this;
        }

        public HEnchantmentPredicate build() {
            return new HEnchantmentPredicate(enchantments, level, properties);
        }

    }

}
