package org.auioc.mcmod.arnicalib.game.input;

import java.util.function.BooleanSupplier;

import org.auioc.mcmod.arnicalib.base.function.VoidPredicate;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum KeyDownRule implements VoidPredicate, BooleanSupplier {

    ALWAYS(() -> true), NEVER(() -> false), //
    ON_SHIFT_KEY_DOWN(KeyboardUtils::isShiftKeyDown), //
    ON_CTRL_KEY_DOWN(KeyboardUtils::isCtrlKeyDown), //
    ON_ALT_KEY_DOWN(KeyboardUtils::isAltKeyDown), //
    ON_SPACE_KEY_DOWN(KeyboardUtils::isSpaceKeyDown);

    private final BooleanSupplier delegate;

    private KeyDownRule(BooleanSupplier predicate) {
        this.delegate = predicate;
    }

    @Override
    public boolean getAsBoolean() {
        return delegate.getAsBoolean();
    }

    @Override
    public boolean test() {
        return delegate.getAsBoolean();
    }

}
