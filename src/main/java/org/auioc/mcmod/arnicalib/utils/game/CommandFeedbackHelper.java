package org.auioc.mcmod.arnicalib.utils.game;

import static org.auioc.mcmod.arnicalib.utils.game.TextUtils.EmptyText;
import static org.auioc.mcmod.arnicalib.utils.game.TextUtils.I18nText;
import java.util.function.Function;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.MutableComponent;

public class CommandFeedbackHelper {

    private final MutableComponent prefix;
    private final Function<String, String> i18n;

    public CommandFeedbackHelper(MutableComponent prefix, Function<String, String> i18n) {
        this.prefix = prefix;
        this.i18n = i18n;
    }

    public CommandFeedbackHelper(Function<String, String> i18n) {
        this(EmptyText(), i18n);
    }

    public MutableComponent createMessage(String key) {
        return EmptyText().append(this.prefix).append(I18nText(this.i18n.apply(key)));
    }

    public MutableComponent createMessage(String key, Object... args) {
        return EmptyText().append(this.prefix).append(I18nText(this.i18n.apply(key), args));
    }

    public MutableComponent successMessage(String key) {
        return this.createMessage("command." + key + ".success");
    }

    public MutableComponent successMessage(String key, Object... args) {
        return this.createMessage("command." + key + ".success", args);
    }

    public MutableComponent failureMessage(String key) {
        return this.createMessage("command." + key + ".failure");
    }

    public MutableComponent failureMessage(String key, Object... args) {
        return this.createMessage("command." + key + ".failure", args);
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
