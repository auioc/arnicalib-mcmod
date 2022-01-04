package org.auioc.mods.arnicalib.server.command.impl;

import static org.auioc.mods.arnicalib.utils.game.TextUtils.emptyText;
import static org.auioc.mods.arnicalib.utils.game.TextUtils.getStringText;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.MutableComponent;

public class VersionCommand {

    public static int getModVersion(CommandContext<CommandSourceStack> ctx, String mainVersion, String fullVersion, String modName) {
        MutableComponent message = emptyText();
        message.append(getStringText("[" + modName + "] ").withStyle(ChatFormatting.AQUA));

        if (mainVersion.equals("0") && fullVersion.equals("0")) {
            message.append(getStringText("Could not read the mod version. ").withStyle(ChatFormatting.YELLOW));
            message.append(getStringText("If this is a development environment you can ignore this message.").withStyle(ChatFormatting.GRAY));
        } else {
            message.append(getStringText("Version: " + mainVersion + " (" + fullVersion + ")"));
        }

        ctx.getSource().sendSuccess(message, false);
        return Command.SINGLE_SUCCESS;
    }

}
