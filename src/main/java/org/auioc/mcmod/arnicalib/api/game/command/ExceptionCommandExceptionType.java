package org.auioc.mcmod.arnicalib.api.game.command;

import java.util.function.Function;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;


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
