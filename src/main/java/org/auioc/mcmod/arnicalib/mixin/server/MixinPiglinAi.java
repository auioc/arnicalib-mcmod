package org.auioc.mcmod.arnicalib.mixin.server;

import org.auioc.mcmod.arnicalib.server.event.ServerEventFactory;
import org.auioc.mcmod.arnicalib.server.event.impl.PiglinStanceEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;

@Mixin(value = PiglinAi.class)
public class MixinPiglinAi {

    @Inject(
        method = "Lnet/minecraft/world/entity/monster/piglin/PiglinAi;isWearingGold(Lnet/minecraft/world/entity/LivingEntity;)Z",
        at = @At(value = "HEAD"),
        cancellable = true,
        require = 1,
        allow = 1
    )
    private static void isWearingGold(LivingEntity p_34809_, CallbackInfoReturnable<Boolean> cri) {
        var stance = ServerEventFactory.firePiglinStanceEvent(p_34809_);
        if (stance == PiglinStanceEvent.Stance.NEUTRAL) {
            cri.setReturnValue(true);
        } else if (stance == PiglinStanceEvent.Stance.HOSTILE) {
            cri.setReturnValue(false);
        }
    }

}
