package org.auioc.mods.arnicalib.server.command.impl;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.MutableComponent;

public class VersionCommand {

    public static int getModVersion(CommandContext<CommandSourceStack> ctx, String mainVersion, String fullVersion, String modName) {
        MutableComponent message = TextUtils.EmptyText();

        message.append(TextUtils.StringText("[" + modName + "] ").withStyle(ChatFormatting.AQUA));

        if (mainVersion.equals("0") && fullVersion.equals("0")) {
            message.append(TextUtils.I18nText(ArnicaLib.MOD_ID + ".command.version.failure"));
        } else {
            message.append(TextUtils.I18nText(ArnicaLib.MOD_ID + ".command.version.success", mainVersion, fullVersion));
        }

        ctx.getSource().sendSuccess(message, false);

        return Command.SINGLE_SUCCESS;
    }

}
