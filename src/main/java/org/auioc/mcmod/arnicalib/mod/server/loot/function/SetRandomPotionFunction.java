package org.auioc.mcmod.arnicalib.mod.server.loot.function;

import java.util.ArrayList;
import java.util.List;
import org.auioc.mcmod.arnicalib.base.random.RandomUtils;
import org.auioc.mcmod.arnicalib.base.validate.Validate;
import org.auioc.mcmod.arnicalib.game.registry.OrderedForgeRegistries;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemFunctions;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
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

    private final List<Potion> potions;
    private final boolean isBlacklist;

    protected SetRandomPotionFunction(LootItemCondition[] conditions, List<Potion> potions, boolean isBlacklist) {
        super(conditions);
        this.potions = potions;
        this.isBlacklist = isBlacklist;
    }

    @Override
    public LootItemFunctionType getType() {
        return AHLootItemFunctions.SET_RANDOM_POTION.get();
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext ctx) {
        if (this.potions.isEmpty()) {
            return PotionUtils.setPotion(stack, getRandomPotion());
        } else {
            if (this.isBlacklist) {
                Potion potion;
                while (true) {
                    potion = getRandomPotion();
                    if (!this.potions.contains(potion)) {
                        break;
                    }
                }
                return PotionUtils.setPotion(stack, potion);
            }
            return PotionUtils.setPotion(stack, RandomUtils.pickOneFromList(this.potions));
        }
    }

    private static Potion getRandomPotion() {
        return RandomUtils.pickOneFromList(OrderedForgeRegistries.POTIONS.get()).getValue();
    }


    public static class Serializer extends LootItemConditionalFunction.Serializer<SetRandomPotionFunction> {

        public void serialize(JsonObject json, SetRandomPotionFunction instance, JsonSerializationContext ctx) {}

        public SetRandomPotionFunction deserialize(JsonObject json, JsonDeserializationContext ctx, LootItemCondition[] conditions) {
            List<Potion> potions = new ArrayList<Potion>();
            boolean isBlacklist = false;

            if (json.has("potions")) {
                JsonArray potionsJson = GsonHelper.getAsJsonArray(json, "potions");
                if (!potionsJson.isEmpty()) {
                    for (JsonElement element : potionsJson) {
                        ResourceLocation id = new ResourceLocation(GsonHelper.convertToString(element, "potionId"));
                        Validate.isTrue(ForgeRegistries.POTIONS.containsKey(id), "Unknown potion '" + id + "'");

                        Potion potion = ForgeRegistries.POTIONS.getValue(id);
                        potions.add(potion);
                    }

                    isBlacklist = GsonHelper.getAsBoolean(json, "blacklist", false);
                }
            }

            return new SetRandomPotionFunction(conditions, potions, isBlacklist);
        }

    }

}
