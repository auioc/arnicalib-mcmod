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
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.enchantment.Enchantment;
import org.auioc.mcmod.arnicalib.game.codec.EnumCodec;

import java.util.Optional;
import java.util.function.Predicate;

public record HEnchantmentPropertiesPredicate(
    Optional<Enchantment.Rarity> rarity,
    Optional<Boolean> curse,
    Optional<Boolean> treasureOnly,
    Optional<Boolean> tradeable,
    Optional<Boolean> discoverable,
    Optional<Boolean> allowedOnBooks
) implements Predicate<Enchantment> {

    @Override
    public boolean test(Enchantment ench) {
        if (rarity.isPresent() && !rarity.get().equals(ench.getRarity())) {
            return false;
        }
        if (curse.isPresent() && !curse.get().equals(ench.isCurse())) {
            return false;
        }
        if (treasureOnly.isPresent() && !treasureOnly.get().equals(ench.isTreasureOnly())) {
            return false;
        }
        if (tradeable.isPresent() && !tradeable.get().equals(ench.isTradeable())) {
            return false;
        }
        if (discoverable.isPresent() && !discoverable.get().equals(ench.isDiscoverable())) {
            return false;
        }
        if (allowedOnBooks.isPresent() && !allowedOnBooks.get().equals(ench.isAllowedOnBooks())) {
            return false;
        }
        return true;
    }

    // ============================================================================================================== //

    public static final Codec<HEnchantmentPropertiesPredicate> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
                ExtraCodecs.strictOptionalField(EnumCodec.byNameLowerCase(Enchantment.Rarity.class), "rarity").forGetter(HEnchantmentPropertiesPredicate::rarity),
                ExtraCodecs.strictOptionalField(Codec.BOOL, "curse").forGetter(HEnchantmentPropertiesPredicate::curse),
                ExtraCodecs.strictOptionalField(Codec.BOOL, "treasure_only").forGetter(HEnchantmentPropertiesPredicate::treasureOnly),
                ExtraCodecs.strictOptionalField(Codec.BOOL, "tradeable").forGetter(HEnchantmentPropertiesPredicate::tradeable),
                ExtraCodecs.strictOptionalField(Codec.BOOL, "discoverable").forGetter(HEnchantmentPropertiesPredicate::discoverable),
                ExtraCodecs.strictOptionalField(Codec.BOOL, "allowed_on_books").forGetter(HEnchantmentPropertiesPredicate::allowedOnBooks)
            )
            .apply(instance, HEnchantmentPropertiesPredicate::new)
    );

    // ============================================================================================================== //

    public static HEnchantmentPropertiesPredicate.Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Optional<Enchantment.Rarity> rarity = Optional.empty();
        private Optional<Boolean> curse = Optional.empty();
        private Optional<Boolean> treasureOnly = Optional.empty();
        private Optional<Boolean> tradeable = Optional.empty();
        private Optional<Boolean> discoverable = Optional.empty();
        private Optional<Boolean> allowedOnBooks = Optional.empty();

        private Builder() { }

        public Builder rarity(Enchantment.Rarity rarity) {
            this.rarity = Optional.of(rarity);
            return this;
        }

        public Builder curse(boolean curse) {
            this.curse = Optional.of(curse);
            return this;
        }

        public Builder treasureOnly(boolean treasureOnly) {
            this.treasureOnly = Optional.of(treasureOnly);
            return this;
        }

        public Builder tradeable(boolean tradeable) {
            this.tradeable = Optional.of(tradeable);
            return this;
        }

        public Builder discoverable(boolean discoverable) {
            this.discoverable = Optional.of(discoverable);
            return this;
        }

        public Builder allowedOnBooks(boolean allowedOnBooks) {
            this.allowedOnBooks = Optional.of(allowedOnBooks);
            return this;
        }

        public HEnchantmentPropertiesPredicate build() {
            return new HEnchantmentPropertiesPredicate(rarity, curse, treasureOnly, tradeable, discoverable, allowedOnBooks);
        }

    }

}
