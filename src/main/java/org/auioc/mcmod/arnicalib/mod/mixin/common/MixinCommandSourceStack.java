package org.auioc.mcmod.arnicalib.mod.mixin.common;

import org.auioc.mcmod.arnicalib.mod.mixinapi.common.IMixinCommandSourceStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;

@Mixin(value = CommandSourceStack.class)
public abstract class MixinCommandSourceStack implements IMixinCommandSourceStack {

    @Shadow
    @Final
    private CommandSource source;

    @Override
    public CommandSource getSource() {
        return this.source;
    }

}
