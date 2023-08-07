# EEJS

Create custom processed types for Emendatus Enigmatica with KubeJS!

## How to use

First you need textures for your parts that can be placed in `kubejs/assets/eejs/textures/items/templates/<processedtype>/<n>.png`.

These textures can have multiple layers like every other Emendatus Enigmatica processed type.

Then in a KubeJS startup script you can use the new `EmendatusEvents.partRegistry` event to create a new processed type like below:

```js
EmendatusEvents.partRegistry(event => {
  // create(processedType: string, partIdProvider: (material: string) => string, tagName: string, partLocalizedNameProvider: (material: string) => string, textureLayers: number)
  event.create('crushed_ore', material => `crushed_${material}_ore`, 'crushed_ores', material => `Crushed ${material} Ore`, 5);
})
```

After you've created the processed type you can just add it to your emendatus enigmatica materials' processed types list.
