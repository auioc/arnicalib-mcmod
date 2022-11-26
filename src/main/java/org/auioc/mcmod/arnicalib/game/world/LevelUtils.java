package org.auioc.mcmod.arnicalib.game.world;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import org.auioc.mcmod.arnicalib.game.server.ServerUtils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.server.ServerLifecycleHooks;

public class LevelUtils {

    public static ResourceKey<Level> createKey(ResourceLocation id) {
        return ResourceKey.create(Registry.DIMENSION_REGISTRY, id);
    }

    public static ServerLevel getLevel(ResourceKey<Level> key) {
        return ServerLifecycleHooks.getCurrentServer().getLevel(key);
    }


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

    public static Component getMoonphaseName(Level level) {
        return TextUtils.translatable("moonphase." + level.getMoonPhase());
    }

    public static Component getDimensionName(Level level) {
        return TextUtils.translatable(Util.makeDescriptionId("dimension", level.dimension().location()));
    }

    public static Component getBiomeName(Level level, BlockPos pos) {
        var id = getBiomeId(level, pos);
        return (id != null) ? TextUtils.translatable(Util.makeDescriptionId("biome", id)) : TextUtils.empty();
    }

    @Nullable
    public static ResourceLocation getBiomeId(Level level, BlockPos pos) {
        var b = level.getBiome(pos).unwrapKey();
        return (b.isPresent()) ? b.get().location() : null;
    }


    public static Map<ServerPlayer, ServerLevel> getPlayerLevelMap() {
        return ServerUtils.getServer().getPlayerList().getPlayers()
            .stream().collect(Collectors.toMap(Function.identity(), ServerPlayer::getLevel));
    }

    public static Map<UUID, ResourceLocation> getPlayerDimensionMap() {
        return ServerUtils.getServer().getPlayerList().getPlayers()
            .stream().collect(Collectors.toMap(ServerPlayer::getUUID, (p) -> p.getLevel().dimension().location()));
    }

}
