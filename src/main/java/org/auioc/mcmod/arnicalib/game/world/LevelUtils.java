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

package org.auioc.mcmod.arnicalib.game.world;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.ITeleporter;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.auioc.mcmod.arnicalib.game.server.ServerUtils;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LevelUtils {

    public static ResourceKey<Level> createKey(ResourceLocation id) {
        return ResourceKey.create(Registries.DIMENSION, id);
    }

    public static ServerLevel getLevel(ResourceKey<Level> key) {
        return ServerLifecycleHooks.getCurrentServer().getLevel(key);
    }

    // ============================================================================================================== //

    public static ITeleporter createSimpleTeleporter(Vec3 pos, boolean playTeleportSound) {
        return new ITeleporter() {
            @Override
            public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                entity = repositionEntity.apply(false);
                entity.teleportTo(pos.x, pos.y, pos.z);
                return entity;
            }

            @Override
            public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld) {
                return playTeleportSound;
            }
        };
    }

    public static ITeleporter createSimpleTeleporter(Vec3i pos, boolean playTeleportSound) {
        return createSimpleTeleporter(Vec3.atCenterOf(pos), playTeleportSound);
    }

    public static ITeleporter createSimpleTeleporter(Vec3i pos) {
        return createSimpleTeleporter(Vec3.atCenterOf(pos), false);
    }

    public static ITeleporter createSimpleTeleporter(Vec3 pos) {
        return createSimpleTeleporter(pos, false);
    }

    public static ITeleporter createSimpleTeleporter(double x, double y, double z) {
        return createSimpleTeleporter(new Vec3(x, y, z), false);
    }

    // ============================================================================================================== //

    public static Component getMoonphaseName(Level level) {
        return Component.translatable("moonphase." + level.getMoonPhase());
    }

    public static Component getDimensionName(Level level) {
        return Component.translatable(Util.makeDescriptionId("dimension", level.dimension().location()));
    }

    public static Component getBiomeName(Level level, BlockPos pos) {
        var id = getBiomeId(level, pos);
        return (id != null) ? Component.translatable(Util.makeDescriptionId("biome", id)) : Component.empty();
    }

    @Nullable
    public static ResourceLocation getBiomeId(Level level, BlockPos pos) {
        var b = level.getBiome(pos).unwrapKey();
        return (b.isPresent()) ? b.get().location() : null;
    }

    // ============================================================================================================== //

    public static Map<ServerPlayer, ServerLevel> getPlayerLevelMap() {
        return ServerUtils.getServer().getPlayerList().getPlayers()
            .stream().collect(Collectors.toMap(Function.identity(), ServerPlayer::serverLevel));
    }

    public static Map<UUID, ResourceLocation> getPlayerDimensionMap() {
        return ServerUtils.getServer().getPlayerList().getPlayers()
            .stream().collect(Collectors.toMap(ServerPlayer::getUUID, (p) -> p.serverLevel().dimension().location()));
    }

    // ============================================================================================================== //

    public static boolean isSlimeChunk(ChunkPos chunkPos, long seed) {
        return WorldgenRandom.seedSlimeChunk(chunkPos.x, chunkPos.z, seed, 987234911L).nextInt(10) == 0;
    }

}
