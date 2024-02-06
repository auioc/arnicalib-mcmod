/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.command;

import com.mojang.brigadier.exceptions.BuiltInExceptionProvider;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.network.chat.Component;
import org.auioc.mcmod.arnicalib.ArnicaLib;

public class CommandExceptions {

    public static final BuiltInExceptionProvider BUILT_IN_EXCEPTIONS = CommandSyntaxException.BUILT_IN_EXCEPTIONS;

    public static final SimpleCommandExceptionType INTERNAL_ERROR = simple("command.failure.internal");
    public static final SimpleCommandExceptionType LOGGABLE_INTERNAL_ERROR = simple("command.failure.internal.loggable");
    public static final SimpleCommandExceptionType NOT_SERVER_ERROR = simple("command.failure.not_server");
    public static final SimpleCommandExceptionType NOT_DEDICATED_SERVER_ERROR = simple("command.failure.not_dedicated_server");

    private static SimpleCommandExceptionType simple(String key) {
        return new SimpleCommandExceptionType(Component.translatable(ArnicaLib.i18n("command.failure.internal")));
    }

}
