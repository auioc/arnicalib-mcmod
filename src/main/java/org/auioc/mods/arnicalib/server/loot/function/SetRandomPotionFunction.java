package org.auioc.mods.arnicalib.server.loot.function;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import org.auioc.mods.arnicalib.server.loot.LootItemFunctionRegistry;
import org.auioc.mods.arnicalib.utils.java.RandomUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.ForgeRegistries;

public class SetRandomPotionFunction extends LootItemConditionalFunction {

    final List<Potion> potions;

    protected SetRandomPotionFunction(LootItemCondition[] conditions, List<Potion> potions) {
        super(conditions);
        this.potions = potions;
    }

    @Override
    public LootItemFunctionType getType() {
        return LootItemFunctionRegistry.SET_RANDOM_POTION;
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext ctx) {
        if (this.potions.isEmpty()) {
            return PotionUtils.setPotion(stack, RandomUtils.pickOneFromCollection(ForgeRegistries.POTIONS.getValues()));
        } else {
            return PotionUtils.setPotion(stack, RandomUtils.pickOneFromList(this.potions));
        }
    }


    public static class SerializerX extends Serializer<SetRandomPotionFunction> {

        public void serialize(JsonObject json, SetRandomPotionFunction function, JsonSerializationContext ctx) {}

        public SetRandomPotionFunction deserialize(JsonObject json, JsonDeserializationContext ctx, LootItemCondition[] conditions) {
            List<Potion> potions = new ArrayList<Potion>();

            if (json.has("potions")) {
                JsonArray potionsJson = GsonHelper.getAsJsonArray(json, "potions");
                for (JsonElement element : potionsJson) {
                    String potionId = GsonHelper.convertToString(element, "potionId");
                    Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(potionId));
                    if (potion == null) {
                        throw new JsonSyntaxException("Unknown potion '" + potionId + "'");
                    }
                    potions.add(potion);
                }
            }

            return new SetRandomPotionFunction(conditions, potions);
        }

    }

}
