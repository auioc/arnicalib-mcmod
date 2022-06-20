package org.auioc.mcmod.arnicalib.api.game.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class UsableItem extends Item {

    protected final int useDuration;
    protected final UseAnim useAnim;

    public UsableItem(Properties properties, int useDuration, UseAnim useAnim) {
        super(properties);
        this.useDuration = useDuration;
        this.useAnim = useAnim;
    }

    public UsableItem(Properties properties, int useDuration) {
        this(properties, useDuration, UseAnim.NONE);
    }

    public UsableItem(Properties properties) {
        this(properties, 32);
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return this.useDuration;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return this.useAnim;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (canUse(level, player, hand, itemStack)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        }

        return InteractionResultHolder.pass(itemStack);
    }

    public boolean canUse(Level level, Player player, InteractionHand hand, ItemStack itemStack) {
        return true;
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity livingEntity, int remainingTicks) {}

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int remainingTicks) {}

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        return itemStack;
    }

}
