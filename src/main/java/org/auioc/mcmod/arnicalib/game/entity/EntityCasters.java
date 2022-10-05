package org.auioc.mcmod.arnicalib.game.entity;

import java.util.function.Function;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class EntityCasters {

    public static final Function<Entity, LivingEntity> TO_LIVING = (entity) -> (LivingEntity) entity;
    public static final Function<Player, ServerPlayer> TO_SERVER_PLAYER = (player) -> (ServerPlayer) player;

}
