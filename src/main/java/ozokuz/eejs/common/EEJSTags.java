package ozokuz.eejs.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;
import ozokuz.eejs.EEJS;

import java.util.function.BiFunction;

public class EEJSTags {
    public static final BiFunction<String, String, TagKey<Item>> PART = (part, material) -> getItemTag(new ResourceLocation(EEJS.MOD_ID, part + "/" + material));

    public static TagKey<Item> getItemTag(ResourceLocation resourceLocation) {
        return ForgeRegistries.ITEMS.tags().stream().filter(items -> items.getKey().location().equals(resourceLocation)).map(ITag::getKey).findFirst().orElse(ForgeRegistries.ITEMS.tags().createTagKey(resourceLocation));
    }
}
