package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.goldenhopper.core.ModBlocks;

/**
 * Author: MrCrayfish
 */
public class CommonLootTableProvider
{
    public static class Block
    {
        public static void accept(LootBuilder.Block builder)
        {
            builder.self(ModBlocks.GOLDEN_HOPPER.get());
        }
    }
}
