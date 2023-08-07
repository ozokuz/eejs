package ozokuz.eejs.common;

import com.ridanisaurus.emendatusenigmatica.items.BasicBurnableItem;
import com.ridanisaurus.emendatusenigmatica.items.BasicItem;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ozokuz.eejs.EEJS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EEJS.MOD_ID);
    public static Map<String, Map<String, RegistryObject<Item>>> itemMap = new HashMap<>();
    public static Map<String, PartData> parts = new HashMap<>();

    public static void addPart(PartData part) {
        parts.put(part.getProcessedType(), part);
    }

    public static void registerParts(MaterialModel material) {
        for (var partEntry : parts.entrySet()) {
            var type = partEntry.getKey();
            var part = partEntry.getValue();
            List<String> processedType = material.getProcessedTypes();
            if (!processedType.contains(type)) continue;

            var partMap = getOrCreatePartMap(type);

            if (material.getProperties().isBurnable()) {
                partMap.put(material.getId(), ITEMS.register(part.getId(material), () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
            } else {
                partMap.put(material.getId(), ITEMS.register(part.getId(material), () -> new BasicItem(material)));
            }
        }
    }

    private static Map<String, RegistryObject<Item>> getOrCreatePartMap(String part) {
        if (!itemMap.containsKey(part)) {
            itemMap.put(part, new HashMap<>());
        }

        return itemMap.get(part);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
