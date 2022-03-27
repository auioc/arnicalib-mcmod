package org.auioc.mods.arnicalib.utils.game;

import static org.auioc.mods.arnicalib.ArnicaLib.i18n;
import static org.auioc.mods.arnicalib.utils.game.TextUtils.I18nText;
import java.lang.reflect.Field;
import java.util.function.Function;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import org.auioc.mods.arnicalib.api.mixin.server.IMixinCommandSourceStack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface CommandUtils {

    SimpleCommandExceptionType INTERNAL_ERROR = new SimpleCommandExceptionType(I18nText(i18n("command.failure.internal")));
    SimpleCommandExceptionType NOT_SERVER_ERROR = new SimpleCommandExceptionType(I18nText(i18n("command.failure.not_server")));
    SimpleCommandExceptionType NOT_DEDICATED_SERVER_ERROR = new SimpleCommandExceptionType(I18nText(i18n("command.failure.not_dedicated_server")));
    SimpleCommandExceptionType REFLECTION_ERROR = new SimpleCommandExceptionType(I18nText(i18n("command.failure.reflection")));

    /**
     * @param sourceStack
     * @return The real command source of the specified {@code CommandSourceStack}
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @since 4.0.0
     * @deprecated Use {@link IMixinCommandSourceStack} instead {@code ((IMixinCommandSourceStack)stack).getSource()}
     */
    @Deprecated(since = "4.1.5")
    static CommandSource getPrivateSource(CommandSourceStack sourceStack) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field privateSourceField = CommandSourceStack.class.getDeclaredField("source");
        privateSourceField.setAccessible(true);
        return (CommandSource) privateSourceField.get(sourceStack);
    }

    @OnlyIn(Dist.CLIENT)
    static LocalPlayer getLocalPlayerOrException(CommandSourceStack sourceStack) throws CommandSyntaxException {
        var entity = sourceStack.getEntity();
        if (entity instanceof LocalPlayer) {
            return (LocalPlayer) entity;
        }
        throw CommandSourceStack.ERROR_NOT_PLAYER.create();
    }

    public static class CommandFeedbackHelper {

        private final Function<String, String> i18n;

        public CommandFeedbackHelper(Function<String, String> i18n) {
            this.i18n = i18n;
        }

        public TranslatableComponent successMessage(String key) {
            return I18nText(this.i18n.apply("command." + key + ".success"));
        }

        public TranslatableComponent successMessage(String key, Object... args) {
            return I18nText(this.i18n.apply("command." + key + ".success"), args);
        }

        public TranslatableComponent failureMessage(String key) {
            return I18nText(this.i18n.apply("command." + key + ".failure"));
        }

        public TranslatableComponent failureMessage(String key, Object... args) {
            return I18nText(this.i18n.apply("command." + key + ".failure"), args);
        }

        public int success(CommandContext<CommandSourceStack> ctx, String key) {
            ctx.getSource().sendSuccess(this.successMessage(key), false);
            return Command.SINGLE_SUCCESS;
        }

        public int success(CommandContext<CommandSourceStack> ctx, String key, Object... args) {
            ctx.getSource().sendSuccess(this.successMessage(key, args), false);
            return Command.SINGLE_SUCCESS;
        }

        public int failure(CommandContext<CommandSourceStack> ctx, String key) {
            ctx.getSource().sendFailure(this.failureMessage(key));
            return Command.SINGLE_SUCCESS;
        }

        public int failure(CommandContext<CommandSourceStack> ctx, String key, Object... args) {
            ctx.getSource().sendFailure(this.failureMessage(key, args));
            return Command.SINGLE_SUCCESS;
        }

    }

}
