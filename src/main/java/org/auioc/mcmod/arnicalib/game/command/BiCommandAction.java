package org.auioc.mcmod.arnicalib.game.command;

import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.function.FailableToIntBiFunction;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class BiCommandAction {


    public static interface SingleSuccess<T, U> extends FailableBiConsumer<T, U, CommandSyntaxException> {
    }

    public static interface MultiSuccess<T, U> extends FailableToIntBiFunction<T, U, CommandSyntaxException> {
    }

}
