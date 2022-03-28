package org.auioc.mods.arnicalib.common.command;

import com.mojang.brigadier.arguments.ArgumentType;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.common.command.argument.CreativeModeTabArgument;
import org.auioc.mods.arnicalib.common.command.argument.DamageSourceArgument;
import org.auioc.mods.arnicalib.common.command.argument.EntityDamageSourceArgument;
import org.auioc.mods.arnicalib.common.command.argument.IndirectEntityDamageSourceArgument;
import net.minecraft.commands.synchronization.ArgumentSerializer;
import net.minecraft.commands.synchronization.ArgumentTypes;
import net.minecraft.commands.synchronization.EmptyArgumentSerializer;

public final class AHCommandArguments {

    private static <T extends ArgumentType<?>> void register(String id, Class<T> clazz, ArgumentSerializer<T> serializer) {
        ArgumentTypes.register(ArnicaLib.id(id).toString(), clazz, serializer);
    }

    public static void init() {
        register("damage_source", DamageSourceArgument.class, new EmptyArgumentSerializer<>(DamageSourceArgument::damageSource));
        register("entity_damage_source", EntityDamageSourceArgument.class, new EmptyArgumentSerializer<>(EntityDamageSourceArgument::damageSource));
        register("indirect_entity_damage_source", IndirectEntityDamageSourceArgument.class, new EmptyArgumentSerializer<>(IndirectEntityDamageSourceArgument::damageSource));
        register("creative_mode_tab", CreativeModeTabArgument.class, new EmptyArgumentSerializer<>(CreativeModeTabArgument::creativeModeTab));
    }
}
