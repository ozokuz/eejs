package ozokuz.eejs.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;
import ozokuz.eejs.EEJS;
import ozokuz.eejs.emendatus.EEJSEmendatusPlugin;
import ozokuz.eejs.kubejs.events.EmendatusEvents;
import ozokuz.eejs.kubejs.events.PartRegistryHandlerEvent;

public class KubeJSEmendatusPlugin extends KubeJSPlugin {
    @Override
    public void registerEvents() {
        EmendatusEvents.GROUP.register();
    }

    @Override
    public void initStartup() {
        EmendatusEvents.PART_REGISTRY.post(ScriptType.STARTUP, new PartRegistryHandlerEvent());
        EEJS.PARTS_REGISTERED = true;
        synchronized (EEJSEmendatusPlugin.INSTANCE) {
            EEJSEmendatusPlugin.INSTANCE.notify();
        }
    }
}
