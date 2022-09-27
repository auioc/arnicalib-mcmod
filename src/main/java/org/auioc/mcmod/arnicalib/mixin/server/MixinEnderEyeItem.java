package org.auioc.mcmod.arnicalib.mixin.server;

import org.auioc.mcmod.arnicalib.game.mixin.server.IMixinEyeOfEnder;
import org.auioc.mcmod.arnicalib.server.event.AHServerEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

@Mixin(value = EnderEyeItem.class)
public abstract class MixinEnderEyeItem {

    @SuppressWarnings("rawtypes")
    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Inject(
        method = "Lnet/minecraft/world/item/EnderEyeItem;use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z",
            shift = At.Shift.BEFORE
        ),
        locals = LocalCapture.CAPTURE_FAILHARD,
        require = 1,
        allow = 1
    )
    private void use(
        Level p_41184_, Player p_41185_, InteractionHand p_41186_,
        CallbackInfoReturnable<InteractionResultHolder> cir,
        ItemStack itemstack, HitResult hitresult, ServerLevel serverlevel, BlockPos blockpos, EyeOfEnder eyeofender
    ) {
        ((IMixinEyeOfEnder) eyeofender).setSurvivable(AHServerEventFactory.onEyeOfEnderSetSurvivable((ServerPlayer) p_41185_, eyeofender));
    }

}
