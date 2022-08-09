package org.auioc.mcmod.arnicalib.utils.game;

import static org.auioc.mcmod.arnicalib.utils.game.TextUtils.EmptyText;
import static org.auioc.mcmod.arnicalib.utils.game.TextUtils.I18nText;
import java.util.function.Function;
import javax.annotation.Nullable;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.MutableComponent;

public class CommandFeedbackHelper {

    public static enum MessageType {
        SUCCESS, SUCCESS_BROADCAST, FAILURE
    }

    public static final Object[] NO_ARGS = new Object[0];
    public static final ChatFormatting[] NO_STYLE = new ChatFormatting[0];

    private final MutableComponent prefix;
    private final Function<String, String> i18n;

    public CommandFeedbackHelper(MutableComponent prefix, Function<String, String> i18n) {
        this.prefix = prefix;
        this.i18n = i18n;
    }

    public CommandFeedbackHelper(Function<String, String> i18n) {
        this(EmptyText(), i18n);
    }


    private MutableComponent createMessage(MessageType type, String key, @Nullable String subKey, Object... args) {
        var sb = new StringBuilder(5);
        {
            sb.append("command.");
            sb.append(key);
            sb.append((type == MessageType.FAILURE) ? ".failure" : ".success");
            if (subKey != null) {
                sb.append(".");
                sb.append(subKey);
            }
        }
        return EmptyText().append(this.prefix).append(I18nText(this.i18n.apply(sb.toString()), args));
    }

    public int sendMessage(CommandContext<CommandSourceStack> ctx, MessageType type, String key, @Nullable String subKey, @Nullable Object[] args, @Nullable ChatFormatting[] styles) {
        if (args == null) args = NO_ARGS;
        if (styles == null) styles = NO_STYLE;

        var message = createMessage(type, key, subKey, args).withStyle(styles);

        var source = ctx.getSource();
        switch (type) {
            case SUCCESS: {
                source.sendSuccess(message, false);
                break;
            }
            case SUCCESS_BROADCAST: {
                source.sendSuccess(message, true);
                break;
            }
            case FAILURE: {
                source.sendFailure(message);
                break;
            }
        }

        return Command.SINGLE_SUCCESS;
    }


    public int sendSuccess(CommandContext<CommandSourceStack> ctx, String key) {
        return sendMessage(ctx, MessageType.SUCCESS, key, null, NO_ARGS, NO_STYLE);
    }

    public int sendSuccess(CommandContext<CommandSourceStack> ctx, String key, Object... args) {
        return sendMessage(ctx, MessageType.SUCCESS, key, null, args, NO_STYLE);
    }

    public int sendSuccessWithSubkey(CommandContext<CommandSourceStack> ctx, String key, String subKey) {
        return sendMessage(ctx, MessageType.SUCCESS, key, subKey, NO_ARGS, NO_STYLE);
    }

    public int sendSuccessWithSubkey(CommandContext<CommandSourceStack> ctx, String key, String subKey, Object... args) {
        return sendMessage(ctx, MessageType.SUCCESS, key, subKey, args, NO_STYLE);
    }

    public int sendSuccessAndBoardcast(CommandContext<CommandSourceStack> ctx, String key) {
        return sendMessage(ctx, MessageType.SUCCESS_BROADCAST, key, null, NO_ARGS, NO_STYLE);
    }

    public int sendSuccessAndBoardcast(CommandContext<CommandSourceStack> ctx, String key, Object... args) {
        return sendMessage(ctx, MessageType.SUCCESS_BROADCAST, key, null, args, NO_STYLE);
    }

    public int sendFailure(CommandContext<CommandSourceStack> ctx, String key) {
        return sendMessage(ctx, MessageType.FAILURE, key, null, NO_ARGS, NO_STYLE);
    }

    public int sendFailure(CommandContext<CommandSourceStack> ctx, String key, Object... args) {
        return sendMessage(ctx, MessageType.FAILURE, key, null, args, NO_STYLE);
    }

    public int sendFailureWithSubkey(CommandContext<CommandSourceStack> ctx, String key, String subKey) {
        return sendMessage(ctx, MessageType.FAILURE, key, subKey, NO_ARGS, NO_STYLE);
    }

    public int sendFailureWithSubkey(CommandContext<CommandSourceStack> ctx, String key, String subKey, Object... args) {
        return sendMessage(ctx, MessageType.FAILURE, key, subKey, args, NO_STYLE);
    }

    /* ============================================================================================================== */
    // #region Deprecated

    @Deprecated(since = "5.3.6")
    public int success(CommandContext<CommandSourceStack> ctx, String key) {
        return sendSuccess(ctx, key);
    }

    @Deprecated(since = "5.3.6")
    public int success(CommandContext<CommandSourceStack> ctx, String key, Object... args) {
        return sendSuccess(ctx, key, args);
    }

    @Deprecated(since = "5.3.6")
    public int failure(CommandContext<CommandSourceStack> ctx, String key) {
        return sendFailure(ctx, key);
    }

    @Deprecated(since = "5.3.6")
    public int failure(CommandContext<CommandSourceStack> ctx, String key, Object... args) {
        return sendFailure(ctx, key, args);
    }

    // #endregion Deprecated

}
