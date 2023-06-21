package org.auioc.mcmod.arnicalib.mod.mixin.client;

import java.util.List;
import java.util.Map;
import org.auioc.mcmod.arnicalib.mod.client.event.AHClientEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import net.minecraft.client.resources.language.ClientLanguage;
import net.minecraft.server.packs.resources.ResourceManager;

@Mixin(value = ClientLanguage.class)
public class MixinClientLanguage {

    @Inject(
        method = "Lnet/minecraft/client/resources/language/ClientLanguage;loadFrom(Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/List;Z)Lnet/minecraft/client/resources/language/ClientLanguage;",
        at = @At(
            value = "NEW",
            target = "(Ljava/util/Map;Z)Lnet/minecraft/client/resources/language/ClientLanguage;"
        ),
        locals = LocalCapture.CAPTURE_FAILHARD,
        require = 1,
        allow = 1
    )
    private static void loadFrom(
        ResourceManager p_265765_, List<String> p_265743_, boolean p_265470_,
        CallbackInfoReturnable<ClientLanguage> cir,
        Map<String, String> map
    ) {
        AHClientEventFactory.onClientLanguageLoad(p_265765_, p_265743_, map, p_265470_);
    }

}
