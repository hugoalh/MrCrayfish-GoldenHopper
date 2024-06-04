package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.framework.Registration;
import com.mrcrayfish.goldenhopper.Constants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class NeoForgeLootTableGen extends LootTableProvider
{
    public NeoForgeLootTableGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(output, Set.of(), List.of(new SubProviderEntry(Block::new, LootContextParamSets.BLOCK)), lookupProvider);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {}

    public static class Block extends BlockLootSubProvider
    {
        protected Block()
        {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate()
        {
            CommonLootTableProvider.Block.accept(new PlatformLootBuilder.Block(this::dropSelf, (block, builder) -> {
                this.add(block, LootTable.lootTable().withPool(this.applyExplosionCondition(block, builder)));
            }));
        }

        @Override
        protected Iterable<net.minecraft.world.level.block.Block> getKnownBlocks()
        {
            return Registration.get(Registries.BLOCK).stream().filter(entry -> entry.getId().getNamespace().equals(Constants.MOD_ID)).map(entry -> (net.minecraft.world.level.block.Block) entry.get()).collect(Collectors.toList());
        }
    }
}
