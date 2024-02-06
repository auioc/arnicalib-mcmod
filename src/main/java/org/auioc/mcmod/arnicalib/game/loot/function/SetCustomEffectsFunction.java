package org.auioc.mcmod.arnicalib.game.loot.function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.auioc.mcmod.arnicalib.game.effect.MobEffectInstanceSerializer;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemFunctions;

import java.util.List;

public class SetCustomEffectsFunction extends LootItemConditionalFunction {

    private final List<MobEffectInstance> effects;

    protected SetCustomEffectsFunction(List<LootItemCondition> conditions, List<MobEffectInstance> effects) {
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

    // ============================================================================================================== //

    public static final Codec<SetCustomEffectsFunction> CODEC =
        RecordCodecBuilder.create(
            instance -> commonFields(instance)
                .and(MobEffectInstanceSerializer.CODEC.listOf().fieldOf("").forGetter(o -> o.effects))
                .apply(instance, SetCustomEffectsFunction::new)
        );

}
