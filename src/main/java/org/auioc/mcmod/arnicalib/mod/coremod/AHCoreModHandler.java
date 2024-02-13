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

package org.auioc.mcmod.arnicalib.mod.coremod;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.auioc.mcmod.arnicalib.game.event.server.FishingRodCastEvent;
import org.auioc.mcmod.arnicalib.game.tag.HBlockTags;
import org.auioc.mcmod.arnicalib.mod.server.event.AHServerEventFactory;

import javax.annotation.Nullable;

public class AHCoreModHandler {

    /**
     * @see <code>coremod: arnicalib.cross_bow_item.shoot_projectile<code/>
     */
    public static void preCrossbowRelease(LivingEntity living, ItemStack weapon, Projectile projectile) {
        AHServerEventFactory.preProjectileWeaponRelease(living, weapon, projectile);
    }

    /**
     * @see <code>coremod: arnicalib.fishing_rod_item.use<code/>
     */
    public static FishingRodCastEvent preFishingRodCast(Player player, ItemStack fishingRod, int speedBonus, int luckBonus) {
        return AHServerEventFactory.preFishingRodCast(player, fishingRod, speedBonus, luckBonus);
    }

    /**
     * @see <code>coremod: arnicalib.piston_base_block.is_pushable<code/>
     */
    public static boolean checkPistonInteractivity(BlockState blockState) {
        return blockState.is(HBlockTags.PISTON_NONINTERACTIVE);
    }

    /**
     * @see <code>coremod: arnicalib.item_stack.hurt<code/>
     */
    public static int onItemHurt(ItemStack itemStack, int damage, RandomSource random, @Nullable ServerPlayer player) {
        return AHServerEventFactory.onItemHurt(itemStack, damage, random, player).getDamage();
    }

}
