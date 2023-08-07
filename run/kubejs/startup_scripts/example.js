// priority: 0

// Visit the wiki for more info - https://kubejs.com/

console.info('Hello, World! (Loaded startup scripts)')

EmendatusEvents.partRegistry(event => {
  // create(processedType: string, partIdMaker: (material: string) => string, tagName: string, partLocalizedNameMaker: (material: string) => string, textureLayers: number)
  event.create('crushed_ore', material => `crushed_${material}_ore`, 'crushed_ores', material => `Crushed ${material} Ore`, 5);
})
