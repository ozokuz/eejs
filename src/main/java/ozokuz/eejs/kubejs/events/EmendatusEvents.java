package ozokuz.eejs.kubejs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface EmendatusEvents {
    EventGroup GROUP = EventGroup.of("EmendatusEvents");

    EventHandler PART_REGISTRY = GROUP.startup("partRegistry", () -> PartRegistryHandlerEvent.class);
}
