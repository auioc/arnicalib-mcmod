package org.auioc.mcmod.arnicalib.game.loot.modifier;

import java.util.Map;
import java.util.function.Supplier;
import org.auioc.mcmod.arnicalib.base.reflection.ReflectionUtils;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class LootTableInjector extends LootModifier {

    private final Map<ResourceLocation, ResourceLocation> injectors; // targetTableId, sourceTableId
    private final boolean strictParameter;

    protected LootTableInjector(LootItemCondition[] conditions, Map<ResourceLocation, ResourceLocation> injectors, boolean strictParameter) {
        super(conditions);
        this.injectors = injectors; // TODO multi inject
        this.strictParameter = strictParameter; // TODO Re-impl strictParameter
    }

    private ObjectArrayList<ItemStack> getItemStacks(LootContext ctx, ResourceLocation targetId) {
        ResourceLocation sourceId = this.injectors.get(targetId);
        LootTable sourceTable = ctx.getResolver().getLootTable(sourceId);
        try { // TODO AT or MixinAccessor
            var param = ReflectionUtils.getFieldValue(ctx, LootContext.class.getDeclaredField("params"), LootParams.class).get();
            return sourceTable.getRandomItems(param);
        } catch (Exception e) {
            e.printStackTrace();
            return ObjectArrayList.of();
        }
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext ctx) {
        // TODO Entity drodps missing? Collects drops in LivingEntity#dropFromLootTable using mixin
        ResourceLocation id = ctx.getQueriedLootTableId();

        if (!this.injectors.containsKey(id)) {
            return generatedLoot;
        }

        generatedLoot.addAll(getItemStacks(ctx, id));
        return generatedLoot;
    }


    public static final Supplier<Codec<LootTableInjector>> CODEC = Suppliers.memoize(
        () -> RecordCodecBuilder.create(
            inst -> codecStart(inst)
                .and(
                    inst.group(
                        Codec.unboundedMap(ResourceLocation.CODEC, ResourceLocation.CODEC).optionalFieldOf("injectors", Map.of()).forGetter(m -> m.injectors),
                        Codec.BOOL.optionalFieldOf("strict_parameter", false).forGetter((m) -> m.strictParameter)
                    )
                )
                .apply(inst, LootTableInjector::new)
        )
    );

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

    // public static class Serializer extends GlobalLootModifierSerializer<LootTableInjector> {

    //     @Override
    //     public LootTableInjector read(ResourceLocation location, JsonObject json, LootItemCondition[] conditions) {
    //         HashMap<ResourceLocation, ResourceLocation> injectors = new HashMap<>();
    //         boolean strictParameter;

    //         JsonArray injectorsJson = GsonHelper.getAsJsonArray(json, "injectors");
    //         for (JsonElement element : injectorsJson) {
    //             JsonObject injectorJson = GsonHelper.convertToJsonObject(element, "injector");
    //             injectors.put(
    //                 new ResourceLocation(GsonHelper.getAsString(injectorJson, "target")),
    //                 new ResourceLocation(GsonHelper.getAsString(injectorJson, "source"))
    //             );
    //         }

    //         strictParameter = GsonHelper.getAsBoolean(json, "strict_parameter", true);

    //         return new LootTableInjector(conditions, injectors, strictParameter);
    //     }

    //     @Override
    //     public JsonObject write(LootTableInjector instance) {
    //         final var json = makeConditions(instance.conditions);
    //         json.addProperty("strict_parameter", instance.strictParameter);
    //         var injectors = new JsonArray(instance.injectors.size());
    //         instance.injectors
    //             .entrySet().stream()
    //             .map((entry) -> {
    //                 var injector = new JsonObject();
    //                 injector.addProperty("target", entry.getKey().toString());
    //                 injector.addProperty("source", entry.getValue().toString());
    //                 return injector;
    //             })
    //             .forEach(injectors::add);
    //         json.add("injectors", injectors);
    //         return json;
    //     }

    // }

}
