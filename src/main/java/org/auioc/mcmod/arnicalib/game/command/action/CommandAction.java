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

package org.auioc.mcmod.arnicalib.game.command.action;

import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableToIntFunction;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class CommandAction {

    public static interface SingleSuccess<T> extends FailableConsumer<T, CommandSyntaxException> {

    }


    public static interface MultiSuccess<T> extends FailableToIntFunction<T, CommandSyntaxException> {

    }

}
