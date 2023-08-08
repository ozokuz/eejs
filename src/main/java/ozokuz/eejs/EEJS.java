package ozokuz.eejs;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import ozokuz.eejs.common.Registration;

@Mod(EEJS.MOD_ID)
public class EEJS {
    public static final String MOD_ID = "eejs";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static boolean PARTS_REGISTERED = false;

    public static ResourceLocation res(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public EEJS() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Registration.register(modEventBus);
    }
}
