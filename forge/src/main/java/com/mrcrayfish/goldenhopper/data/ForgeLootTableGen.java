package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.framework.Registration;
import com.mrcrayfish.goldenhopper.Constants;
import com.mrcrayfish.goldenhopper.core.ModEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForgeLootTableGen extends LootTableProvider
{
    public ForgeLootTableGen(PackOutput output)
    {
        super(output, Set.of(), List.of(new SubProviderEntry(Block::new, LootContextParamSets.BLOCK), new SubProviderEntry(Entity::new, LootContextParamSets.ENTITY)));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {}

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

    public static class Entity extends EntityLootSubProvider
    {
        protected Entity()
        {
            super(FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        public void generate()
        {
            CommonLootTableProvider.Entity.accept(new PlatformLootBuilder.Entity(this::add));
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes()
        {
            return Stream.of(ModEntities.GOLDEN_HOPPER_MINECART.get());
        }
    }
}
