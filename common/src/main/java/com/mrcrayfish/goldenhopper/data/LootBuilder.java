package com.mrcrayfish.goldenhopper.data;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * Author: MrCrayfish
 */
public class LootBuilder
{
    public interface Block
    {
        void self(net.minecraft.world.level.block.Block block);

        void custom(net.minecraft.world.level.block.Block block, LootPool.Builder builder);
    }

    public interface Entity
    {
        void add(EntityType<?> type, LootTable.Builder builder);
    }
}
