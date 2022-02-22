package org.auioc.mods.arnicalib.server.loot.predicate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.auioc.mods.arnicalib.server.loot.AHLootItemConditions;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.fml.ModList;

public class LoadedModCondition implements LootItemCondition {

    private final String modId;

    public LoadedModCondition(String modId) {
        this.modId = modId;
    }

    @Override
    public boolean test(LootContext ctx) {
        return ModList.get().isLoaded(this.modId);
    }

    @Override
    public LootItemConditionType getType() {
        return AHLootItemConditions.LOADED_MOD;
    }


    public static class SerializerX implements Serializer<LoadedModCondition> {

        @Override
        public void serialize(JsonObject json, LoadedModCondition instance, JsonSerializationContext ctx) {}

        @Override
        public LoadedModCondition deserialize(JsonObject json, JsonDeserializationContext ctx) {
            return new LoadedModCondition(GsonHelper.getAsString(json, "loaded_mod"));
        }

    }

}
