package org.auioc.mcmod.arnicalib.game.item;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;

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
                var texts = new Component[4];
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

}
