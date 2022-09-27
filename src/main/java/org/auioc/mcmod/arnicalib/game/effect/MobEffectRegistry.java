package org.auioc.mcmod.arnicalib.game.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.base.random.RandomUtils;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;
import org.auioc.mcmod.arnicalib.utils.game.OrderedForgeRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.ForgeRegistries;

public class MobEffectRegistry {

    @Nonnull
    @Deprecated
    public static Optional<MobEffect> get(int id) {
        return Optional.ofNullable(MobEffect.byId(id));
    }

    @Nonnull
    public static Optional<MobEffect> get(ResourceLocation id) {
        return Optional.ofNullable(ForgeRegistries.MOB_EFFECTS.containsKey(id) ? ForgeRegistries.MOB_EFFECTS.getValue(id) : null);
    }

    @Nonnull
    public static Optional<MobEffect> get(String id) {
        return get(new ResourceLocation(id));
    }

    @Nonnull
    public static MobEffect getOrElseThrow(ResourceLocation id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_MOB_EFFECT.create(id.toString()));
    }

    @Nonnull
    public static MobEffect getOrElseThrow(String id) {
        return get(id).orElseThrow(RegistryEntryException.UNKNOWN_MOB_EFFECT.create(id));
    }

    public static List<MobEffect> getAll(@Nullable MobEffectCategory type) {
        var effectsList = new ArrayList<MobEffect>();
        for (var effect : ForgeRegistries.MOB_EFFECTS.getValues()) {
            if (type == null || effect.getCategory() == type) {
                effectsList.add(effect);
            }
        }
        return effectsList;
    }

    public static List<MobEffect> getHarmfulEffects() {
        return getAll(MobEffectCategory.HARMFUL);
    }

    public static List<MobEffect> getBeneficialEffects() {
        return getAll(MobEffectCategory.BENEFICIAL);
    }

    public static List<MobEffect> getNeutralEffects() {
        return getAll(MobEffectCategory.NEUTRAL);
    }

    public static List<MobEffect> getAll() {
        return getAll(null);
    }

    public static MobEffect getRandom(boolean useOrderedRegestry) {
        if (useOrderedRegestry) {
            return RandomUtils.pickOneFromList(OrderedForgeRegistries.MOB_EFFECTS.get()).getValue();
        }
        return RandomUtils.pickOneFromCollection(ForgeRegistries.MOB_EFFECTS.getValues());
    }

    public static MobEffect getRandom() {
        return getRandom(true);
    }

}
