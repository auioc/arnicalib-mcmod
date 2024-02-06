package org.auioc.mcmod.arnicalib.game.loot.predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.fml.ModList;
import org.auioc.mcmod.arnicalib.mod.server.loot.AHLootItemConditions;

public class ModLoadedCondition implements LootItemCondition {

    private final String modId;

    public ModLoadedCondition(String modId) {
        this.modId = modId;
    }

    @Override
    public LootItemConditionType getType() {
        return AHLootItemConditions.MOD_LOADED.get();
    }

    @Override
    public boolean test(LootContext ctx) {
        return ModList.get().isLoaded(this.modId);
    }

    // ============================================================================================================== //

    public static final Codec<ModLoadedCondition> CODEC =
        RecordCodecBuilder.create(
            instance -> instance
                .group(
                    Codec.STRING.fieldOf("mod").forGetter(o -> o.modId)
                )
                .apply(instance, ModLoadedCondition::new)
        );

}
