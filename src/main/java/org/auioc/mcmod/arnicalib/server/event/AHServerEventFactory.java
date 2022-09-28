package org.auioc.mcmod.arnicalib.server.event;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import org.auioc.mcmod.arnicalib.game.chat.TextUtils;
import org.auioc.mcmod.arnicalib.server.event.impl.EyeOfEnderSurvivableEvent;
import org.auioc.mcmod.arnicalib.server.event.impl.LivingEatAddEffectEvent;
import org.auioc.mcmod.arnicalib.server.event.impl.ServerLoginEvent;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ClientboundLoginDisconnectPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public final class AHServerEventFactory {

    private static final Marker MARKER = LogUtil.getMarker("ServerHooks");
    private static final IEventBus BUS = MinecraftForge.EVENT_BUS;

    // Return true if the event was Cancelable cancelled

    public static boolean onServerLogin(final ClientIntentionPacket packet, final Connection manager) {
        ServerLoginEvent event = new ServerLoginEvent(packet, manager);
        boolean cancelled = BUS.post(event);
        if (cancelled) {
            var message = TextUtils.literal(event.getMessage());
            manager.send(new ClientboundLoginDisconnectPacket(message));
            manager.disconnect(message);
            LOGGER.info(
                LogUtil.getMarker("ServerLogin").addParents(MARKER),
                String.format("Disconnecting %s connection attempt from %s: %s", event.getPacket().getIntention(), event.getNetworkManager().getRemoteAddress(), event.getMessage())
            );
            return true;
        }
        return false;
    }

    public static List<MobEffectInstance> onLivingEatAddEffect(LivingEntity entity, ItemStack food, List<MobEffectInstance> effects) {
        LivingEatAddEffectEvent event = new LivingEatAddEffectEvent(entity, food, effects);
        if (BUS.post(event)) {
            event.getEffects().clear();
        }
        return event.getEffects();
    }

    public static Function<Random, Boolean> onEyeOfEnderSetSurvivable(ServerPlayer player, EyeOfEnder eye) {
        EyeOfEnderSurvivableEvent event = new EyeOfEnderSurvivableEvent(player, eye);
        BUS.post(event);
        return event.getSurvivable();
    }

}
