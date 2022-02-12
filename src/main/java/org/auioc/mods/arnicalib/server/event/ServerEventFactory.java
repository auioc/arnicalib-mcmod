package org.auioc.mods.arnicalib.server.event;

import static org.auioc.mods.arnicalib.ArnicaLib.LOGGER;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import org.apache.logging.log4j.Marker;
import org.auioc.mods.arnicalib.server.event.impl.LivingEatAddEffectEvent;
import org.auioc.mods.arnicalib.server.event.impl.ServerLoginEvent;
import org.auioc.mods.arnicalib.server.event.impl.ServerPlayerEntitySendMessageEvent;
import org.auioc.mods.arnicalib.server.event.impl.SetEyeOfEnderSurvivableEvent;
import org.auioc.mods.arnicalib.utils.LogUtil;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ClientboundLoginDisconnectPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public final class ServerEventFactory {

    private static final Marker marker = LogUtil.getMarker("ServerHooks");
    private static final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

    // Return true if the event was Cancelable cancelled

    public static boolean fireServerLoginEvent(final ClientIntentionPacket packet, final Connection manager) {
        ServerLoginEvent event = new ServerLoginEvent(packet, manager);
        boolean cancelled = forgeEventBus.post(event);
        if (cancelled) {
            TextComponent message = new TextComponent(event.getMessage());
            manager.send(new ClientboundLoginDisconnectPacket(message));
            manager.disconnect(message);
            LOGGER.info(
                LogUtil.getMarker("ServerLogin").addParents(marker),
                String.format("Disconnecting %s connection attempt from %s: %s", event.getPacket().getIntention(), event.getNetworkManager().getRemoteAddress(), event.getMessage())
            );
            return true;
        }
        return false;
    }

    public static boolean fireServerPlayerEntitySendMessageEvent(Component message, ChatType type, UUID uuid) {
        return forgeEventBus.post(new ServerPlayerEntitySendMessageEvent(message, type, uuid));
    }

    public static List<MobEffectInstance> fireLivingEatAddEffectEvent(LivingEntity entity, ItemStack food, List<MobEffectInstance> effects) {
        LivingEatAddEffectEvent event = new LivingEatAddEffectEvent(entity, food, effects);
        if (forgeEventBus.post(event)) {
            event.getEffects().clear();
        }
        return event.getEffects();
    }

    public static Function<Random, Boolean> fireSetEyeOfEnderSurvivableEvent(EyeOfEnder eye, ServerPlayer player) {
        SetEyeOfEnderSurvivableEvent event = new SetEyeOfEnderSurvivableEvent(eye, player);
        forgeEventBus.post(event);
        return event.getSurvivable();
    }

}
