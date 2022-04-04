package org.auioc.mods.arnicalib.server.loot.function;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.auioc.mods.arnicalib.server.loot.AHLootItemFunctions;
import org.auioc.mods.arnicalib.utils.game.EffectUtils;
import org.auioc.mods.arnicalib.utils.java.Validate;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class SetCustomEffectsFunction extends LootItemConditionalFunction {

    private final List<MobEffectInstance> effects;

    protected SetCustomEffectsFunction(LootItemCondition[] conditions, List<MobEffectInstance> effects) {
        super(conditions);
        this.effects = effects;
    }

    @Override
    public LootItemFunctionType getType() {
        return AHLootItemFunctions.SET_CUSTOM_EFFECTS.get();
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext ctx) {
        return PotionUtils.setCustomEffects(stack, this.effects);
    }


    public static class SerializerX extends Serializer<SetCustomEffectsFunction> {

        public void serialize(JsonObject json, SetCustomEffectsFunction instance, JsonSerializationContext ctx) {}

        public SetCustomEffectsFunction deserialize(JsonObject json, JsonDeserializationContext ctx, LootItemCondition[] conditions) {
            List<MobEffectInstance> effects = new ArrayList<MobEffectInstance>();

            JsonArray effectsJson = GsonHelper.getAsJsonArray(json, "effects");
            Validate.isTrue(!effectsJson.isEmpty(), "The mob effect instance list must be not empty");
            for (JsonElement element : effectsJson) {
                effects.add(EffectUtils.createInstance(GsonHelper.convertToJsonObject(element, "mobEffectInstance")));
            }

            return new SetCustomEffectsFunction(conditions, effects);
        }

    }

}
