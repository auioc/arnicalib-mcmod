package org.auioc.mcmod.arnicalib.game.effect;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.auioc.mcmod.arnicalib.game.registry.RegistryEntryException;
import org.auioc.mcmod.arnicalib.game.registry.RegistryUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class MobEffectRegistry {

    @Nonnull
    public static Optional<MobEffect> get(ResourceLocation id) {
        return Optional.ofNullable(BuiltInRegistries.MOB_EFFECT.containsKey(id) ? BuiltInRegistries.MOB_EFFECT.get(id) : null);
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

    public static List<MobEffect> all(@Nullable MobEffectCategory type) {
        var s = BuiltInRegistries.MOB_EFFECT.stream();
        return type == null ? s.toList() : s.filter((e) -> e.getCategory() == type).toList();
    }

    public static List<MobEffect> getHarmfulEffects() {
        return all(MobEffectCategory.HARMFUL);
    }

    public static List<MobEffect> getBeneficialEffects() {
        return all(MobEffectCategory.BENEFICIAL);
    }

    public static List<MobEffect> getNeutralEffects() {
        return all(MobEffectCategory.NEUTRAL);
    }

    public static List<MobEffect> all() {
        return all(null);
    }

    public static MobEffect random(RandomSource random) {
        return RegistryUtils.random(BuiltInRegistries.MOB_EFFECT, random);
    }

}
