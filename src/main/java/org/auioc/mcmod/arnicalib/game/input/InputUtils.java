package org.auioc.mcmod.arnicalib.game.input;

import net.minecraft.client.player.Input;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InputUtils {

    public static float calculateImpulse(boolean a, boolean b, boolean isMovingSlowly) {
        return (a == b) ? 0.0F : ((a ? 1.0F : -1.0F) * (isMovingSlowly ? 0.3F : 1.0F));
    }

    public static void calculateImpulse(Input input, boolean isMovingSlowly) {
        input.forwardImpulse = calculateImpulse(input.up, input.down, isMovingSlowly);
        input.leftImpulse = calculateImpulse(input.left, input.right, isMovingSlowly);
    }

}
