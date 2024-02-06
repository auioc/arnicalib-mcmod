package org.auioc.mcmod.arnicalib.mod.server.loot;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.loot.modifier.LootTableInjector;


public final class AHGlobalLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ArnicaLib.MOD_ID);

    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<LootTableInjector>> LOOT_TABLE_INJECTOR = GLOBAL_LOOT_MODIFIERS.register("loot_table_injector", LootTableInjector.CODEC);

}
