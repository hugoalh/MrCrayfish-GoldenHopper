package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.goldenhopper.core.ModBlocks;
import com.mrcrayfish.goldenhopper.core.ModEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.Consumer;

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

    public static class Entity
    {
        public static void accept(LootBuilder.Entity builder)
        {
            builder.add(ModEntities.GOLDEN_HOPPER_MINECART.get(), LootTable.lootTable());
        }
    }
}
