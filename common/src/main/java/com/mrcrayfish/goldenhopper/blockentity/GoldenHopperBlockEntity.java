package com.mrcrayfish.goldenhopper.blockentity;

import com.mrcrayfish.goldenhopper.core.ModBlockEntities;
import com.mrcrayfish.goldenhopper.inventory.GoldenHopperMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

/**
 * Author: MrCrayfish
 */
public class GoldenHopperBlockEntity extends HopperBlockEntity implements WorldlyContainer
{
    public static final int CONTAINER_SIZE = 6;
    public static final int FILTER_SLOT_INDEX = 0;
    public static final int[] TRANSFERABLE_SLOTS = IntStream.range(1, CONTAINER_SIZE).toArray();
    public static boolean ejecting = false;

    public GoldenHopperBlockEntity(BlockPos pos, BlockState state)
    {
        super(pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
        ((BlockEntityTypeSetter) this).goldenHopper$SetType(ModBlockEntities.GOLDEN_HOPPER.get());
    }

    @Override
    public BlockEntityType<?> getType()
    {
        return ModBlockEntities.GOLDEN_HOPPER.get();
    }

    @Override
    protected Component getDefaultName()
    {
        return Component.translatable("container.goldenhopper.golden_hopper");
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Inventory playerInventory)
    {
        return new GoldenHopperMenu(windowId, playerInventory, this);
    }

    @Override
    public ItemStack getItem(int slot)
    {
        if(ejecting && slot == FILTER_SLOT_INDEX)
        {
            return ItemStack.EMPTY;
        }
        return super.getItem(slot);
    }

    @Override
    public int[] getSlotsForFace(Direction side)
    {
        return TRANSFERABLE_SLOTS;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack)
    {
        return index != FILTER_SLOT_INDEX && (this.getItems().get(FILTER_SLOT_INDEX).isEmpty() || stack.getItem() == this.getItems().get(FILTER_SLOT_INDEX).getItem());
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction)
    {
        return this.getItems().get(FILTER_SLOT_INDEX).isEmpty() || stack.getItem() == this.getItems().get(FILTER_SLOT_INDEX).getItem();
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction)
    {
        return index != FILTER_SLOT_INDEX;
    }

    @Override
    public boolean canTakeItem(Container container, int index, ItemStack stack)
    {
        return index != FILTER_SLOT_INDEX;
    }
}
