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

package org.auioc.mcmod.arnicalib.game.loot.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class LootTableInjector extends LootModifier {

    private final Map<ResourceLocation, List<ResourceLocation>> injectors; // targetTableId, sourceTableId[]
    private final boolean strictParameter;

    protected LootTableInjector(LootItemCondition[] conditions, Map<ResourceLocation, List<ResourceLocation>> injectors, boolean strictParameter) {
        super(conditions);
        this.injectors = injectors;
        this.strictParameter = strictParameter; // TODO Re-impl? strictParameter
    }

    private ObjectArrayList<ItemStack> getLoot(LootContext ctx, ResourceLocation targetId) {
        var loot = new ObjectArrayList<ItemStack>();
        for (var sourceId : this.injectors.get(targetId)) {
            var sourceTable = ctx.getResolver().getLootTable(sourceId);
            var items = sourceTable.getRandomItems(ctx.params); //~ AccessTransformer L-1
            loot.addAll(items);
        }
        return loot;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext ctx) {
        var id = ctx.getQueriedLootTableId();
        if (!this.injectors.containsKey(id)) {
            return generatedLoot;
        }
        generatedLoot.addAll(getLoot(ctx, id));
        return generatedLoot;
    }

    // ============================================================================================================== //

    public static final Supplier<Codec<LootTableInjector>> CODEC = Suppliers.memoize(
        () -> RecordCodecBuilder.create(
            instance -> codecStart(instance)
                .and(
                    instance.group(
                        Codec.unboundedMap(ResourceLocation.CODEC, Codec.list(ResourceLocation.CODEC))
                            .optionalFieldOf("injectors", Map.of())
                            .forGetter((o) -> o.injectors),
                        Codec.BOOL
                            .optionalFieldOf("strict_parameter", false)
                            .forGetter((o) -> o.strictParameter)
                    )
                )
                .apply(instance, LootTableInjector::new)
        )
    );

    @Override
    public Codec<? extends IGlobalLootModifier> codec() { return CODEC.get(); }

}
