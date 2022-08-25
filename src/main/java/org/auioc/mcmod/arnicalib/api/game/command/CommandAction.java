package org.auioc.mcmod.arnicalib.api.game.command;

import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableToIntFunction;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class CommandAction {


    public static interface SingleSuccess<T> extends FailableConsumer<T, CommandSyntaxException> {
    }

    public static interface MultiSuccess<T> extends FailableToIntFunction<T, CommandSyntaxException> {
    }

}
