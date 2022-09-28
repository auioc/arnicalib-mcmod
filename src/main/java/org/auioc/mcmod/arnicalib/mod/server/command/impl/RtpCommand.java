package org.auioc.mcmod.arnicalib.mod.server.command.impl;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import java.util.List;
import java.util.Optional;
import org.auioc.mcmod.arnicalib.game.command.CommandSourceUtils;
import org.auioc.mcmod.arnicalib.game.entity.EntityFunctions;
import org.auioc.mcmod.arnicalib.game.world.position.RandomTeleporter;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class RtpCommand {

    /*
    *                                                                    ┌ ->
    *                                                                    │
    *                                 ┌ safe <int:radius> <int:maxTries> ┼ <boolean:surface> ->
    *                                 │                                  │                   ┌ ->
    * /...                            │                                  └ <BlockPos:center> ┤
    *   rtp <EntitySelector:entities> ┤                                                      └ <boolean:surface> ->
    *                                 │                        ┌ ->
    *                                 └ unsafe <double:radius> ┤
    *                                                          └ <Vec3:center> ->
    */
    public static final CommandNode<CommandSourceStack> NODE = literal("rtp")
        .requires(CommandSourceUtils.PERMISSION_LEVEL_2)
        .then(
            argument("entities", EntityArgument.entities())
                .then(
                    literal("safe")
                        .then(
                            argument("radius", IntegerArgumentType.integer(0))
                                .then(
                                    argument("maxTries", IntegerArgumentType.integer(1))
                                        .executes((ctx) -> safe(ctx, Optional.empty(), false))
                                        .then(
                                            argument("surface", BoolArgumentType.bool())
                                                .executes((ctx) -> safe(ctx, Optional.empty(), BoolArgumentType.getBool(ctx, "surface")))
                                        )
                                        .then(
                                            argument("center", BlockPosArgument.blockPos())
                                                .executes((ctx) -> safe(ctx, Optional.of(BlockPosArgument.getLoadedBlockPos(ctx, "center")), false))
                                                .then(
                                                    argument("surface", BoolArgumentType.bool())
                                                        .executes((ctx) -> safe(ctx, Optional.of(BlockPosArgument.getLoadedBlockPos(ctx, "center")), BoolArgumentType.getBool(ctx, "surface")))
                                                )
                                        )
                                )
                        )
                )
                .then(
                    literal("unsafe")
                        .then(
                            argument("radius", DoubleArgumentType.doubleArg(0.0D))
                                .executes((ctx) -> unsafe(ctx, Optional.empty()))
                                .then(
                                    argument("center", Vec3Argument.vec3())
                                        .executes((ctx) -> unsafe(ctx, Optional.of(Vec3Argument.getVec3(ctx, "center"))))
                                )
                        )
                )
        )
        .build();

    private static int safe(CommandContext<CommandSourceStack> ctx, Optional<BlockPos> center, boolean surface) throws CommandSyntaxException {
        int radius = IntegerArgumentType.getInteger(ctx, "radius");
        int maxTries = IntegerArgumentType.getInteger(ctx, "maxTries");
        for (var living : getLivingEntities(ctx)) {
            RandomTeleporter.teleport(living, center.orElse(living.blockPosition()), radius, surface, maxTries);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int unsafe(CommandContext<CommandSourceStack> ctx, Optional<Vec3> center) throws CommandSyntaxException {
        double radius = DoubleArgumentType.getDouble(ctx, "radius");
        for (var living : getLivingEntities(ctx)) {
            RandomTeleporter.teleportUnsafe(living, center.orElse(living.position()), radius);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static List<? extends LivingEntity> getLivingEntities(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        return EntityArgument.getEntities(ctx, "entities").stream().filter(EntityFunctions.IS_LIVING).map(EntityFunctions.CAST_TO_LIVING).toList();
    }

}
