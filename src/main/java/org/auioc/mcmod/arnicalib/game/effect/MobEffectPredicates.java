package org.auioc.mcmod.arnicalib.game.effect;

import java.util.function.Predicate;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

public class MobEffectPredicates {

    public static final Predicate<MobEffectInstance> IS_BENEFICIAL = (e) -> e.getEffect().isBeneficial();
    public static final Predicate<MobEffectInstance> IS_NOT_BENEFICIAL = (e) -> !e.getEffect().isBeneficial();
    public static final Predicate<MobEffectInstance> IS_HARMFUL = (e) -> e.getEffect().getCategory() == MobEffectCategory.HARMFUL;
    public static final Predicate<MobEffectInstance> IS_NEUTRAL = (e) -> e.getEffect().getCategory() == MobEffectCategory.NEUTRAL;

}
