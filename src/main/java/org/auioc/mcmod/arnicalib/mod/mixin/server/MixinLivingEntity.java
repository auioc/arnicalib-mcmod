package org.auioc.mcmod.arnicalib.mod.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;

@Mixin(value = LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

    public MixinLivingEntity(EntityType<?> pEntityType, Level pLevel) { super(pEntityType, pLevel); }

    @Shadow
    public abstract long getLootTableSeed();

    @Inject(
        method = "Lnet/minecraft/world/entity/LivingEntity;dropFromLootTable(Lnet/minecraft/world/damagesource/DamageSource;Z)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/storage/loot/LootParams$Builder;create(Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;)Lnet/minecraft/world/level/storage/loot/LootParams;",
            ordinal = 0,
            shift = At.Shift.BY,
            by = 2
        ),
        locals = LocalCapture.CAPTURE_FAILHARD,
        require = 1,
        allow = 1,
        cancellable = true
    )
    private void handleServerLogin(
        DamageSource pDamageSource, boolean pHitByPlayer,
        CallbackInfo ci,
        ResourceLocation resourcelocation, LootTable loottable, LootParams.Builder lootparams$builder, LootParams lootparams
    ) {
        for (var dropped : loottable.getRandomItems(lootparams, getLootTableSeed())) {
            this.spawnAtLocation(dropped);
        }
        ci.cancel();
    }

}
