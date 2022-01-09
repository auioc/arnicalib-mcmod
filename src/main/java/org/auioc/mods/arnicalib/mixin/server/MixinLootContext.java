package org.auioc.mods.arnicalib.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;

@Mixin(value = LootContext.class)
public abstract class MixinLootContext {

    // @org.spongepowered.asm.mixin.Debug(export = true, print = true)
    @Overwrite(remap = false)
    public void setQueriedLootTableId(ResourceLocation queriedLootTableId) {
        this.queriedLootTableId = queriedLootTableId;
    }


    @Shadow(remap = false)
    private ResourceLocation queriedLootTableId;

}
