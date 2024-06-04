package com.mrcrayfish.goldenhopper.block;

import com.mojang.serialization.MapCodec;
import com.mrcrayfish.goldenhopper.blockentity.GoldenHopperBlockEntity;
import com.mrcrayfish.goldenhopper.core.ModBlockEntities;
import com.mrcrayfish.goldenhopper.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

/**
 * Author: MrCrayfish
 */
public class GoldenHopperBlock extends HopperBlock
{
    public static final MapCodec<HopperBlock> CODEC = simpleCodec(GoldenHopperBlock::new);

    public GoldenHopperBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public MapCodec<HopperBlock> codec()
    {
        return CODEC;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result)
    {
        if(!level.isClientSide())
        {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof GoldenHopperBlockEntity hopper)
            {
                player.openMenu(hopper);
                player.awardStat(Stats.INSPECT_HOPPER);
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new GoldenHopperBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return !level.isClientSide() ? createTickerHelper(type, ModBlockEntities.GOLDEN_HOPPER.get(), HopperBlockEntity::pushItemsTick) : null;
    }
}
