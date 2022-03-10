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

public class ModLoadedCondition implements LootItemCondition {

    private final String modId;

    public ModLoadedCondition(String modId) {
        this.modId = modId;
    }

    @Override
    public boolean test(LootContext ctx) {
        return ModList.get().isLoaded(this.modId);
    }

    @Override
    public LootItemConditionType getType() {
        return AHLootItemConditions.MOD_LOADED;
    }


    public static class SerializerX implements Serializer<ModLoadedCondition> {

        @Override
        public void serialize(JsonObject json, ModLoadedCondition instance, JsonSerializationContext ctx) {}

        @Override
        public ModLoadedCondition deserialize(JsonObject json, JsonDeserializationContext ctx) {
            return new ModLoadedCondition(GsonHelper.getAsString(json, "mod"));
        }

    }

}
