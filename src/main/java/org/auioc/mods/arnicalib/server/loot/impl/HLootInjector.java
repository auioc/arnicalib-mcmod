package org.auioc.mods.arnicalib.server.loot.impl;

import java.util.HashMap;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class HLootInjector extends LootModifier {

    private HashMap<ResourceLocation, ResourceLocation> injectors = new HashMap<ResourceLocation, ResourceLocation>();
    private boolean strictParameter;

    protected HLootInjector(LootItemCondition[] conditionsIn, HashMap<ResourceLocation, ResourceLocation> injectors, boolean strictParameter) {
        super(conditionsIn);
        this.injectors = injectors;
        this.strictParameter = strictParameter;
    }

    private List<ItemStack> getItemStacks(LootContext context, ResourceLocation target) {
        ResourceLocation id = this.injectors.get(target);
        LootTable lootTable = context.getLootTable(id);
        LootContext context2 = new LootContext.Builder(context)
            .create((this.strictParameter) ? lootTable.getParamSet() : LootContextParamSets.CHEST);
        context2.setQueriedLootTableId(id); // coremod LootContext#setQueriedLootTableId
        List<ItemStack> list = lootTable.getRandomItems(context2);
        return list;
    }

    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation id = context.getQueriedLootTableId();

        if (!this.injectors.containsKey(id)) {
            return generatedLoot;
        }

        generatedLoot.addAll(getItemStacks(context, id));
        return generatedLoot;
    }


    public static class Serializer extends GlobalLootModifierSerializer<HLootInjector> {

        @Override
        public HLootInjector read(ResourceLocation location, JsonObject json, LootItemCondition[] conditions) {
            HashMap<ResourceLocation, ResourceLocation> injectors = new HashMap<ResourceLocation, ResourceLocation>();
            boolean strictParameter;

            JsonArray injectorsJson = GsonHelper.getAsJsonArray(json, "injectors");
            for (JsonElement injectorJsonElement : injectorsJson) {
                JsonArray injectorJson = injectorJsonElement.getAsJsonArray();
                injectors.put(new ResourceLocation(injectorJson.get(0).getAsString()), new ResourceLocation(injectorJson.get(1).getAsString()));
            }

            strictParameter = GsonHelper.getAsBoolean(json, "strict_parameter", true);

            return new HLootInjector(conditions, injectors, strictParameter);
        }

        @Override
        public JsonObject write(HLootInjector instance) {
            return null;
        }

    }

}
