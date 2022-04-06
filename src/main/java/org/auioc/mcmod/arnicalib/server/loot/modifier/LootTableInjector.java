package org.auioc.mcmod.arnicalib.server.loot.modifier;

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

public class LootTableInjector extends LootModifier {

    private HashMap<ResourceLocation, ResourceLocation> injectors = new HashMap<ResourceLocation, ResourceLocation>(); // targetTableId, sourceTableId
    private boolean strictParameter;

    protected LootTableInjector(LootItemCondition[] conditionsIn, HashMap<ResourceLocation, ResourceLocation> injectors, boolean strictParameter) {
        super(conditionsIn);
        this.injectors = injectors;
        this.strictParameter = strictParameter;
    }

    private List<ItemStack> getItemStacks(LootContext ctx, ResourceLocation targetId) {
        ResourceLocation sourceId = this.injectors.get(targetId);
        LootTable sourceTable = ctx.getLootTable(sourceId);

        LootContext newCtx = new LootContext.Builder(ctx).create((this.strictParameter) ? sourceTable.getParamSet() : LootContextParamSets.CHEST);
        newCtx.setQueriedLootTableId(sourceId); // mixin LootContext#setQueriedLootTableId

        return sourceTable.getRandomItems(newCtx);
    }

    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext ctx) {
        ResourceLocation id = ctx.getQueriedLootTableId();

        if (!this.injectors.containsKey(id)) {
            return generatedLoot;
        }

        generatedLoot.addAll(getItemStacks(ctx, id));
        return generatedLoot;
    }


    public static class Serializer extends GlobalLootModifierSerializer<LootTableInjector> {

        @Override
        public LootTableInjector read(ResourceLocation location, JsonObject json, LootItemCondition[] conditions) {
            HashMap<ResourceLocation, ResourceLocation> injectors = new HashMap<ResourceLocation, ResourceLocation>();
            boolean strictParameter;

            JsonArray injectorsJson = GsonHelper.getAsJsonArray(json, "injectors");
            for (JsonElement element : injectorsJson) {
                JsonObject injectorJson = GsonHelper.convertToJsonObject(element, "injector");
                injectors.put(
                    new ResourceLocation(GsonHelper.getAsString(injectorJson, "target")),
                    new ResourceLocation(GsonHelper.getAsString(injectorJson, "source"))
                );
            }

            strictParameter = GsonHelper.getAsBoolean(json, "strict_parameter", true);

            return new LootTableInjector(conditions, injectors, strictParameter);
        }

        @Override
        public JsonObject write(LootTableInjector instance) {
            return null;
        }

    }

}
