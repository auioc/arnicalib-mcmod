package org.auioc.mcmod.arnicalib.mod.client.resource;

import static org.auioc.mcmod.arnicalib.ArnicaLib.LOGGER;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Marker;
import org.auioc.mcmod.arnicalib.base.log.LogUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.ForgeModelBakery;

/**
 * This is a FAKE resource reload listener, do NOT register it!
 *
 * @see org.auioc.mcmod.arnicalib.mod.client.event.AHClientModEventHandler#onModelRegister
 */
@OnlyIn(Dist.CLIENT)
public class CustomModelLoader extends SimpleJsonResourceReloadListener {

    private static final Marker MARKER = LogUtil.getMarker(CustomModelLoader.class);
    private static final String MODELS_PATH = "models/";
    private static final Gson GSON = new GsonBuilder().create();

    private final ResourceManager resourceManager;
    private final String path;

    public CustomModelLoader(ResourceManager resourceManager, String path) {
        super(GSON, MODELS_PATH + path);
        this.resourceManager = resourceManager;
        this.path = path.endsWith("/") ? path : path + "/";
    }

    public List<ResourceLocation> list() {
        return super.prepare(this.resourceManager, null)
            .keySet()
            .stream()
            .map((id) -> new ResourceLocation(id.getNamespace(), this.path + id.getPath()))
            .peek((id) -> LOGGER.info(MARKER, "Found custom model '{}'", id.toString()))
            .toList();
    }

    public void load() {
        list().forEach(ForgeModelBakery::addSpecialModel);
    }

    public static void load(ResourceManager resourceManager, String path) {
        new CustomModelLoader(resourceManager, path).load();
    }

    // ====================================================================== //

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profiler) {}

}
