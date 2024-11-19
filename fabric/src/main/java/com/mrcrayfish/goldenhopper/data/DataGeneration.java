package com.mrcrayfish.goldenhopper.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * Author: MrCrayfish
 */
public class DataGeneration implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(CommonLootTableProvider::new);
        pack.addProvider(CommonRecipeProvider.Runner::new);
        CommonBlockTagsProvider blockTagsProvider = pack.addProvider(CommonBlockTagsProvider::new);
        pack.addProvider((output, registriesFuture) -> new CommonItemTagsProvider(output, registriesFuture, blockTagsProvider.contentsGetter()));
    }
}
