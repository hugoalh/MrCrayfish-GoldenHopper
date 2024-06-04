package com.mrcrayfish.goldenhopper.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

/**
 * Author: MrCrayfish
 */
public class FabricLootTableGen extends FabricBlockLootTableProvider
{
    protected FabricLootTableGen(FabricDataOutput dataOutput)
    {
        super(dataOutput);
    }

    @Override
    public void generate()
    {
        CommonLootTableProvider.Block.accept(new PlatformLootBuilder.Block(this));
    }
}
