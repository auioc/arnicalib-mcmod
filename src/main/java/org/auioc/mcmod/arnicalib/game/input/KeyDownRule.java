package org.auioc.mcmod.arnicalib.game.input;

import org.auioc.mcmod.arnicalib.base.function.VoidPredicate;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum KeyDownRule implements VoidPredicate {

    ALWAYS(() -> true), NEVER(() -> false), //
    ON_SHIFT_KEY_DOWN(KeyboardUtils::isShiftKeyDown), //
    ON_CTRL_KEY_DOWN(KeyboardUtils::isCtrlKeyDown), //
    ON_ALT_KEY_DOWN(KeyboardUtils::isAltKeyDown), //
    ON_SPACE_KEY_DOWN(KeyboardUtils::isSpaceKeyDown);

    private final VoidPredicate predicate;

    private KeyDownRule(VoidPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test() {
        return predicate.test();
    }

}
