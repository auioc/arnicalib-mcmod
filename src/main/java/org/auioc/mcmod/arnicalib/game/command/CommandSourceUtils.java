package org.auioc.mcmod.arnicalib.game.command;

import java.util.function.Predicate;
import org.auioc.mcmod.arnicalib.mixin.common.api.IMixinCommandSourceStack;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BaseCommandBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper.UnableToAccessFieldException;

public class CommandSourceUtils {

    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_0 = (sourceStack) -> sourceStack.hasPermission(0);
    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_1 = (sourceStack) -> sourceStack.hasPermission(1);
    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_2 = (sourceStack) -> sourceStack.hasPermission(2);
    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_3 = (sourceStack) -> sourceStack.hasPermission(3);
    public static final Predicate<CommandSourceStack> PERMISSION_LEVEL_4 = (sourceStack) -> sourceStack.hasPermission(4);

    public static final Predicate<CommandSourceStack> IS_PLAYER = (sourceStack) -> sourceStack.getEntity() instanceof Player;
    public static final Predicate<CommandSourceStack> IS_COMMAND_BLOCK = (sourceStack) -> getRealSource(sourceStack) instanceof BaseCommandBlock;
    public static final Predicate<CommandSourceStack> IS_DEDICATED_SERVER = (sourceStack) -> getRealSource(sourceStack) instanceof DedicatedServer;

    /**
     * @param sourceStack
     * @return The real command source of the specified {@code CommandSourceStack}
     * @throws UnableToAccessFieldException
     * @since 4.0.0
     * @deprecated Use {@link #getPrivateSource} instead
     */
    @Deprecated(since = "4.1.5")
    public static CommandSource getRealSourceReflection(CommandSourceStack sourceStack) {
        return ObfuscationReflectionHelper.getPrivateValue(CommandSourceStack.class, sourceStack, "f_81288_");
    }

    public static CommandSource getRealSource(CommandSourceStack sourceStack) {
        return ((IMixinCommandSourceStack) sourceStack).getSource();
    }


    @OnlyIn(Dist.CLIENT)
    public static LocalPlayer getLocalPlayerOrException(CommandSourceStack sourceStack) throws CommandSyntaxException {
        var entity = sourceStack.getEntity();
        if (entity instanceof LocalPlayer) return (LocalPlayer) entity;
        throw CommandSourceStack.ERROR_NOT_PLAYER.create();
    }

}
