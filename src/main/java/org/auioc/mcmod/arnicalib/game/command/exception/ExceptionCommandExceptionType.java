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

package org.auioc.mcmod.arnicalib.game.command.exception;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

import java.util.function.Function;


public class ExceptionCommandExceptionType implements CommandExceptionType {

    private final Function<Exception, Message> messageBuilder;
    private final Logger logger;
    private final Marker marker;

    public ExceptionCommandExceptionType(Function<Exception, Message> messageBuilder, Logger logger, Marker marker) {
        this.messageBuilder = messageBuilder;
        this.logger = logger;
        this.marker = marker;
    }

    public ExceptionCommandExceptionType(Function<Exception, Message> messageBuilder) {
        this(messageBuilder, null, null);
    }

    public CommandSyntaxException create(Exception e, String logMessage) {
        if (this.logger != null) {
            if (this.marker != null) {
                this.logger.error(this.marker, logMessage, e);
            } else {
                this.logger.error(logMessage, e);
            }
        }
        return new CommandSyntaxException(this, messageBuilder.apply(e));
    }

    public CommandSyntaxException create(Exception e) {
        return create(e, "");
    }

}
