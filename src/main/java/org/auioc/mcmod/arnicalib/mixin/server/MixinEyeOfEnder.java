package org.auioc.mcmod.arnicalib.mixin.server;

import java.util.Random;
import java.util.function.Function;
import org.auioc.mcmod.arnicalib.mixin.server.api.IMixinEyeOfEnder;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.level.Level;

@Mixin(value = EyeOfEnder.class)
public abstract class MixinEyeOfEnder extends Entity implements IMixinEyeOfEnder {

    public MixinEyeOfEnder(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow
    private boolean surviveAfterDeath;

    @Override
    public void setSurvivable(boolean survivable) {
        this.surviveAfterDeath = survivable;
    }

    @Override
    public void setSurvivable(Function<Random, Boolean> survivable) {
        this.surviveAfterDeath = survivable.apply(this.random);
    }

    @Redirect(
        method = "Lnet/minecraft/world/entity/projectile/EyeOfEnder;signalTo(Lnet/minecraft/core/BlockPos;)V",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/entity/projectile/EyeOfEnder;surviveAfterDeath:Z",
            opcode = Opcodes.PUTFIELD
        ),
        require = 1,
        allow = 1
    )
    private void ignorePutFiled_surviveAfterDeath(EyeOfEnder e, boolean z) {}

}

