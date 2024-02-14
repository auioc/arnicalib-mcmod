/*
 * Copyright (C) 2022-2024 AUIOC.ORG
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

package org.auioc.mcmod.arnicalib.mod.server.event;

import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ClientboundLoginDisconnectPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.game.event.server.FishingRodCastEvent;
import org.auioc.mcmod.arnicalib.game.event.server.ItemHurtEvent;
import org.auioc.mcmod.arnicalib.game.event.server.LivingFoodEffectEvent;
import org.auioc.mcmod.arnicalib.game.event.server.ProjectileWeaponReleaseEvent;
import org.auioc.mcmod.arnicalib.game.event.server.ServerLoginEvent;
import org.auioc.mcmod.arnicalib.mod.coremod.AHCoreModHandler;

import javax.annotation.Nullable;
import java.util.List;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;

public final class AHServerEventFactory {

    private static final Marker MARKER = LogUtil.getMarker("ServerHooks");

    private static final IEventBus BUS = NeoForge.EVENT_BUS;

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.server.MixinServerHandshakePacketListenerImpl#handleServerLogin
     */
    public static boolean onServerLogin(final ClientIntentionPacket packet, final Connection connection) {
        var event = new ServerLoginEvent(packet, connection);
        boolean cancelled = BUS.post(event).isCanceled();
        if (cancelled) {
            var message = Component.literal(event.getMessage());
            connection.send(new ClientboundLoginDisconnectPacket(message));
            connection.disconnect(message);
            LOGGER.info(
                LogUtil.getMarker("ServerLogin").addParents(MARKER),
                String.format("Disconnecting %s connection attempt from %s: %s",
                    event.getPacket().intention(), event.getNetworkManager().getRemoteAddress(), event.getMessage()
                )
            );
            return true;
        }
        return false;
    }

    /**
     * @see org.auioc.mcmod.arnicalib.mod.mixin.server.MixinBowItem#releaseUsing
     * @see AHCoreModHandler#preCrossbowRelease
     */
    public static void preProjectileWeaponRelease(LivingEntity living, ItemStack weapon, Projectile projectile) {
        BUS.post(new ProjectileWeaponReleaseEvent(living, weapon, projectile));
    }

    /**
     * @see AHCoreModHandler#preFishingRodCast
     */
    public static FishingRodCastEvent preFishingRodCast(Player player, ItemStack fishingRod, int speedBonus, int luckBonus) {
        var event = new FishingRodCastEvent((ServerPlayer) player, fishingRod, speedBonus, luckBonus);
        return BUS.post(event);
    }

    /**
     * @see AHCoreModHandler#onItemHurt
     */
    public static ItemHurtEvent onItemHurt(ItemStack itemStack, int damage, RandomSource random, @Nullable ServerPlayer player) {
        var event = new ItemHurtEvent(itemStack, damage, random, player);
        return BUS.post(event);
    }

    public static List<MobEffectInstance> onLivingEatAddEffect(LivingEntity living, ItemStack food, List<MobEffectInstance> effects) {
        var event = new LivingFoodEffectEvent(living, food, effects);
        BUS.post(event);
        if (event.isCanceled()) {
            event.getEffects().clear();
        }
        return event.getEffects();
    }

}
