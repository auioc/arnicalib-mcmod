package org.auioc.mods.arnicalib.server.command.impl;

import static org.auioc.mods.arnicalib.utils.game.TextUtils.EmptyText;
import static org.auioc.mods.arnicalib.utils.game.TextUtils.StringText;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.MutableComponent;

public class VersionCommand {

    public static int getModVersion(CommandContext<CommandSourceStack> ctx, String mainVersion, String fullVersion, String modName) {
        MutableComponent message = EmptyText();
        message.append(StringText("[" + modName + "] ").withStyle(ChatFormatting.AQUA));

        if (mainVersion.equals("0") && fullVersion.equals("0")) {
            message.append(StringText("Could not read the mod version. ").withStyle(ChatFormatting.YELLOW));
            message.append(StringText("If this is a development environment you can ignore this message.").withStyle(ChatFormatting.GRAY));
        } else {
            message.append(StringText("Version: " + mainVersion + " (" + fullVersion + ")"));
        }

        ctx.getSource().sendSuccess(message, false);
        return Command.SINGLE_SUCCESS;
    }

}
