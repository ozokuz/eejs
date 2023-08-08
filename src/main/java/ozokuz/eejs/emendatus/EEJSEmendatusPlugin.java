package ozokuz.eejs.emendatus;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import net.minecraft.data.DataGenerator;
import ozokuz.eejs.EEJS;
import ozokuz.eejs.common.Registration;

import java.util.List;

@EmendatusPluginReference(modid = EEJS.MOD_ID, name = "config")
public class EEJSEmendatusPlugin implements IEmendatusPlugin {
    public static EEJSEmendatusPlugin INSTANCE;
    public EEJSEmendatusPlugin() {
        INSTANCE = this;
    }

    @Override
    public void load(EmendatusDataRegistry registry) {
        while (!EEJS.PARTS_REGISTERED) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels) {
        Registration.registerParts(materialModels);
    }

    @Override
    public void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry) {
        generator.addProvider(true, new EEJSDataGen.ItemModels(generator, registry));
        generator.addProvider(true, new EEJSDataGen.Lang(generator, registry));
        generator.addProvider(true, new EEJSDataGen.ItemTags(generator, registry));
    }

    @Override
    public void finish(EmendatusDataRegistry registry) {

    }
}
