package com.mrcrayfish.goldenhopper.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/**
 * Author: MrCrayfish
 */
public class FabricLootTableGen extends FabricBlockLootTableProvider
{
    protected FabricLootTableGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(dataOutput, completableFuture);
    }

    @Override
    public void generate()
    {
        CommonLootTableProvider.Block.accept(new PlatformLootBuilder.Block(this));
    }
}
