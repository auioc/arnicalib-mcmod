package org.auioc.mcmod.arnicalib.mixin.common;

import org.auioc.mcmod.arnicalib.common.event.CommonEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(value = ItemStack.class)
public class MixinItemStack {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Inject(
        method = "Lnet/minecraft/world/item/ItemStack;inventoryTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;IZ)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;", ordinal = 1),
        require = 1,
        allow = 1,
        cancellable = true
    )
    private void onInventoryTick(Level p_41667_, Entity p_41668_, int p_41669_, boolean p_41670_, CallbackInfo ci) {
        if (p_41670_ && p_41668_ instanceof Player player) {
            if (CommonEventFactory.onSelectedItemItemInventoryTick(player, p_41667_, ((ItemStack) (Object) this), p_41669_)) {
                ci.cancel();
            }
        }
    }

}
