package org.auioc.mcmod.arnicalib.mixin.common;

import org.auioc.mcmod.arnicalib.common.event.AHCommonEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = PistonBaseBlock.class)
public abstract class MixinPistonBaseBlock {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Inject(
        method = "Lnet/minecraft/world/level/block/piston/PistonBaseBlock;isPushable(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;ZLnet/minecraft/core/Direction;)Z",
        at = @At(value = "HEAD"),
        require = 1,
        allow = 1,
        cancellable = true
    )
    private static void onCheckPushable(BlockState p_60205_, Level p_60206_, BlockPos p_60207_, Direction p_60208_, boolean p_60209_, Direction p_60210_, CallbackInfoReturnable<Boolean> cir) {
        if (AHCommonEventFactory.onPistonCheckPushable(p_60205_, p_60206_, p_60207_, p_60208_, p_60209_, p_60210_)) {
            cir.setReturnValue(false);
        }
    }

}
