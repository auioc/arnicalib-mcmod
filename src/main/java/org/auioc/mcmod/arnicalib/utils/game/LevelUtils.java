package org.auioc.mcmod.arnicalib.utils.game;

import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.server.ServerLifecycleHooks;

public interface LevelUtils {

    static ResourceKey<Level> createKey(ResourceLocation id) {
        return ResourceKey.create(Registry.DIMENSION_REGISTRY, id);
    }

    static ServerLevel getLevel(ResourceKey<Level> key) {
        return ServerLifecycleHooks.getCurrentServer().getLevel(key);
    }


    static ITeleporter createSimpleTeleporter(Vec3 pos, boolean playTeleportSound) {
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

    static ITeleporter createSimpleTeleporter(Vec3i pos, boolean playTeleportSound) {
        return createSimpleTeleporter(Vec3.atCenterOf(pos), playTeleportSound);
    }

    static ITeleporter createSimpleTeleporter(Vec3i pos) {
        return createSimpleTeleporter(Vec3.atCenterOf(pos), false);
    }

    static ITeleporter createSimpleTeleporter(double x, double y, double z) {
        return createSimpleTeleporter(new Vec3(x, y, z), false);
    }

}
