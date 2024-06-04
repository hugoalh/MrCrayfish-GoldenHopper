package com.mrcrayfish.goldenhopper.mixin;

import com.mrcrayfish.goldenhopper.blockentity.BlockEntityTypeSetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Author: MrCrayfish
 */
@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements BlockEntityTypeSetter
{
    @Shadow
    @Final
    @Mutable
    private BlockEntityType<?> type;

    @Override
    public void goldenHopper$SetType(BlockEntityType<?> type)
    {
        this.type = type;
    }
}
