package org.auioc.mcmod.arnicalib.mod.server.loot;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.loot.modifier.LootTableInjector;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AHGlobalLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ArnicaLib.MOD_ID);

    public static final RegistryObject<Codec<LootTableInjector>> LOOT_TABLE_INJECTOR = GLOBAL_LOOT_MODIFIERS.register("loot_table_injector", LootTableInjector.CODEC);

}
