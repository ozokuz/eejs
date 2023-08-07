package ozokuz.eejs.kubejs.events;

import dev.latvian.mods.kubejs.event.EventJS;
import ozokuz.eejs.common.Registration;
import ozokuz.eejs.common.PartData;

public class PartRegistryHandlerEvent extends EventJS {
    public void create(String processedType, PartData.PartIdProvider partIdProvider, String tagName, PartData.PartLocalizedNameProvider localizedNameProvider, int textureLayers) {
        Registration.addPart(new PartData(processedType, partIdProvider, tagName, localizedNameProvider, textureLayers));
    }
}
