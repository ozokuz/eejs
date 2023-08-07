package ozokuz.eejs.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import ozokuz.eejs.kubejs.events.EmendatusEvents;

public class KubeJSEmendatusPlugin extends KubeJSPlugin {
    @Override
    public void registerEvents() {
        EmendatusEvents.GROUP.register();
    }
}
