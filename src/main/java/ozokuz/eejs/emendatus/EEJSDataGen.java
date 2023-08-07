package ozokuz.eejs.emendatus;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.*;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;
import ozokuz.eejs.EEJS;
import ozokuz.eejs.common.Registration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static ozokuz.eejs.EEJS.res;

public class EEJSDataGen {
    public static class ItemModels extends EEItemModelProvider {
        private final EmendatusDataRegistry registry;

        public ItemModels(DataGenerator gen, EmendatusDataRegistry registry) {
            super(gen);
            this.registry = registry;
        }

        @Override
        protected void buildItemModels(Consumer<IFinishedGenericJSON> consumer) {
            for (MaterialModel material : registry.getMaterials()) {
                List<String> processedType = material.getProcessedTypes();

                for (var partEntry : Registration.parts.entrySet()) {
                    var type = partEntry.getKey();
                    var part = partEntry.getValue();
                    if (!processedType.contains(type)) continue;

                    var partBuilder = new ItemModelBuilder("minecraft:item/generated");
                    for (int i = 0; i < part.getTextureLayers(); i++) {
                        partBuilder.texture(String.format("layer%d", i), res(String.format("items/templates/%s/%d", type, i)).toString());
                    }
                    partBuilder.save(consumer, res(part.getId(material)));
                }
            }
        }

        @Override
        public String getName() {
            return "EEJS Item Models";
        }
    }

    public static class ItemTags extends EETagProvider {
        private final EmendatusDataRegistry registry;

        public ItemTags(DataGenerator gen, EmendatusDataRegistry registry) {
            super(gen);
            this.registry = registry;
        }

        private final Map<String, List<String>> parts = new HashMap<>();

        @Override
        protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
            for (MaterialModel material : registry.getMaterials()) {
                List<String> processedType = material.getProcessedTypes();

                for (var partEntry : Registration.parts.entrySet()) {
                    var type = partEntry.getKey();
                    var part = partEntry.getValue();
                    if (!processedType.contains(type)) continue;

                    var partItem = Registration.itemMap.get(type).get(material.getId()).getId();
                    getOrCreateList(part.getTagName()).add(partItem.toString());
                }
            }

            for (var partEntry : parts.entrySet()) {
                var tag = partEntry.getKey();
                var list = partEntry.getValue();

                if (!list.isEmpty()) new TagBuilder().tags(list).save(consumer, res("items/" + tag));
            }
        }

        @Override
        public String getName() {
            return "EEJS Item Tags";
        }

        private List<String> getOrCreateList(String tag) {
            return parts.computeIfAbsent(tag, t -> new ArrayList<>());
        }
    }

    public static class Lang extends EELangProvider {
        private final EmendatusDataRegistry registry;

        public Lang(DataGenerator gen, EmendatusDataRegistry registry) {
            super(gen, EEJS.MOD_ID, "en_us");
            this.registry = registry;
        }

        @Override
        protected void addTranslations() {
            for (MaterialModel material : registry.getMaterials()) {
                List<String> processedType = material.getProcessedTypes();

                for (var partEntry : Registration.parts.entrySet()) {
                    var type = partEntry.getKey();
                    var part = partEntry.getValue();
                    if (!processedType.contains(type)) continue;

                    var partItem = Registration.itemMap.get(type).get(material.getId()).get();
                    add(partItem, part.getLocalizedName(material));
                }
            }
        }

        @Override
        public @NotNull String getName() {
            return "EEJS Language en_us";
        }
    }
}
