package org.auioc.mcmod.arnicalib.mod.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;

@Mixin(value = CommandSourceStack.class)
public interface MixinAccessorCommandSourceStack {

    @Accessor("source")
    CommandSource getSource();

}
