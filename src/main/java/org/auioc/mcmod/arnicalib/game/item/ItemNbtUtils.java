package org.auioc.mcmod.arnicalib.game.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SuspiciousStewItem;

public class ItemNbtUtils {

    public static Optional<Axolotl.Variant> getAxolotlVariant(ItemStack itemStack) {
        if (itemStack.is(Items.AXOLOTL_BUCKET) && itemStack.hasTag()) {
            var nbt = itemStack.getTag();
            if (nbt.contains("Variant", 99)) {
                int i = nbt.getInt("Variant");
                if (i >= 0 && i < Axolotl.Variant.BY_ID.length) {
                    return Optional.of(Axolotl.Variant.BY_ID[i]);
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<Component[]> getSignTexts(ItemStack itemStack) {
        if (itemStack.getItem() instanceof SignItem && itemStack.hasTag()) {
            var nbt = BlockItem.getBlockEntityData(itemStack);
            if (nbt != null) {
                final var texts = new Component[4];
                for (int i = 0; i < 4; ++i) {
                    var key = "Text" + (i + 1);
                    if (nbt.contains(key, 8)) {
                        texts[i] = Component.Serializer.fromJson(nbt.getString(key));
                    }
                }
                return Optional.of(texts);
            }
        }
        return Optional.empty();
    }

    public static Optional<List<Pair<MobEffect, Integer>>> getSuspiciousStewEffects(ItemStack itemStack) {
        if (itemStack.getItem() instanceof SuspiciousStewItem && itemStack.hasTag()) {
            var nbt = itemStack.getTag();
            if (nbt != null && nbt.contains("Effects", 9)) {
                var eNbtList = nbt.getList("Effects", 10);
                if (!eNbtList.isEmpty()) {
                    int l = eNbtList.size();
                    final var list = new ArrayList<Pair<MobEffect, Integer>>(l);
                    for (int i = 0; i < l; ++i) {
                        var eNbt = eNbtList.getCompound(i);
                        int duration = 160;
                        if (eNbt.contains("EffectDuration", 3)) duration = eNbt.getInt("EffectDuration");
                        var effect = MobEffect.byId(eNbt.getByte("EffectId"));
                        effect = net.minecraftforge.common.ForgeHooks.loadMobEffect(eNbt, "forge:effect_id", effect);
                        list.add(Pair.of(effect, duration));
                    }
                    return Optional.of(list);
                }
            }
        }
        return Optional.empty();
    }

}
