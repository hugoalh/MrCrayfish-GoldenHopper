package com.mrcrayfish.goldenhopper.inventory;

import com.mrcrayfish.goldenhopper.core.ModContainers;
import com.mrcrayfish.goldenhopper.inventory.slot.FilterSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * Author: MrCrayfish
 */
public class GoldenHopperMenu extends AbstractContainerMenu
{
    private final Container container;

    public GoldenHopperMenu(int windowId, Inventory playerInventory)
    {
        this(windowId, playerInventory, new SimpleContainer(6));
    }

    public GoldenHopperMenu(int windowId, Inventory playerInventory, Container container)
    {
        super(ModContainers.GOLDEN_HOPPER.get(), windowId);
        this.container = container;
        checkContainerSize(container, 6);
        container.startOpen(playerInventory.player);

        this.addSlot(new FilterSlot(container, 0, 26, 20));

        for(int i = 0; i < 5; i++)
        {
            this.addSlot(new Slot(container, i + 1, 62 + i * 18, 20));
        }

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, i * 18 + 51));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 109));
        }

    }

    @Override
    public boolean stillValid(Player player)
    {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot.hasItem())
        {
            ItemStack slotStack = slot.getItem();
            result = slotStack.copy();
            if(index < this.container.getContainerSize())
            {
                if(!this.moveItemStackTo(slotStack, this.container.getContainerSize(), this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(!this.moveItemStackTo(slotStack, 1, this.container.getContainerSize(), false))
            {
                return ItemStack.EMPTY;
            }

            if(slotStack.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }

        return result;
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        this.container.stopOpen(player);
    }
}
