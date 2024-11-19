package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.goldenhopper.platform.Services;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Author: MrCrayfish
 */
public class CommonItemTagsProvider extends ItemTagsProvider
{
    public CommonItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, CompletableFuture<TagLookup<Block>> lookupCompletableFuture)
    {
        super(output, providerCompletableFuture, lookupCompletableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        this.tag(Services.PLATFORM.getGoldIngotsTag()).add(Items.GOLD_INGOT);
    }
}
