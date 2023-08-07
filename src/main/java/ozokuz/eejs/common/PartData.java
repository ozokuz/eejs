package ozokuz.eejs.common;

import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;

public class PartData {
    @FunctionalInterface
    public interface PartIdProvider {
        String getId(String material);
    }

    @FunctionalInterface
    public interface PartLocalizedNameProvider {
        String getName(String material);
    }

    private final String processedType;
    private final PartIdProvider partIdProvider;
    private final String tagName;
    private final PartLocalizedNameProvider localizedNameProvider;
    private final int textureLayers;

    public PartData(String processedType, PartIdProvider partIdProvider, String tagName, PartLocalizedNameProvider localizedNameProvider, int textureLayers) {
        this.processedType = processedType;
        this.partIdProvider = partIdProvider;
        this.tagName = tagName;
        this.localizedNameProvider = localizedNameProvider;
        this.textureLayers = textureLayers;
    }

    public String getProcessedType() {
        return processedType;
    }

    public String getId(MaterialModel material) {
        return partIdProvider.getId(material.getId());
    }

    public String getTagName() {
        return tagName;
    }

    public String getLocalizedName(MaterialModel material) {
        return localizedNameProvider.getName(material.getLocalizedName());
    }

    public int getTextureLayers() {
        return textureLayers;
    }
}
