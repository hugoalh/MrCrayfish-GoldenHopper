package com.mrcrayfish.goldenhopper.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/**
 * Author: MrCrayfish
 */
public class FabricBlockTagGen extends FabricTagProvider.BlockTagProvider
{
    public FabricBlockTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg)
    {
        CommonBlockTagsProvider.accept(key -> new PlatformTagBuilder<>(this.getOrCreateTagBuilder(key)));
    }
}
