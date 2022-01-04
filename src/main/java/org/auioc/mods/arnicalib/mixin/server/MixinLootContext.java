package org.auioc.mods.arnicalib.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;

@Mixin(value = LootContext.class)
public abstract class MixinLootContext {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Inject(
        method = "Lnet/minecraft/world/level/storage/loot/LootContext;setQueriedLootTableId(Lnet/minecraft/resources/ResourceLocation;)V",
        at = @At(value = "HEAD"),
        require = 1,
        allow = 1,
        cancellable = true,
        remap = false
    )
    private void setQueriedLootTableId(ResourceLocation queriedLootTableId, CallbackInfo ci) {
        ci.cancel();
        this.queriedLootTableId = queriedLootTableId;
    }


    @Shadow
    private ResourceLocation queriedLootTableId;

}
