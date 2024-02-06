package org.auioc.mcmod.arnicalib.game.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SuspiciousStewItem;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemNbtUtils {

    public static Optional<Axolotl.Variant> getAxolotlVariant(ItemStack itemStack) {
        if (itemStack.is(Items.AXOLOTL_BUCKET) && itemStack.hasTag()) {
            var nbt = itemStack.getTag();
            if (nbt != null && nbt.contains("Variant", 99)) {
                int i = nbt.getInt("Variant");
                if (i >= 0) {
                    return Optional.of(Axolotl.Variant.byId(i));
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
        if (itemStack.getItem() instanceof SuspiciousStewItem) {
            final var list = new ArrayList<Pair<MobEffect, Integer>>();
            SuspiciousStewItem.listPotionEffects(itemStack, (e) -> list.add(Pair.of(e.effect(), e.duration())));
            return Optional.of(list);
        }
        return Optional.empty();
    }

}
