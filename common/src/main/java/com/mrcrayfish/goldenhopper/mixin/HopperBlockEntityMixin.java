package com.mrcrayfish.goldenhopper.mixin;

import com.mrcrayfish.goldenhopper.blockentity.GoldenHopperBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

/**
 * Author: MrCrayfish
 */
@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin
{
    @Inject(method = "tryMoveItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/HopperBlockEntity;isEmpty()Z"))
    private static void goldenHopper$beforeEject(Level level, BlockPos pos, BlockState state, HopperBlockEntity hopper, BooleanSupplier $$4, CallbackInfoReturnable<Boolean> cir)
    {
        if(hopper instanceof GoldenHopperBlockEntity)
        {
            GoldenHopperBlockEntity.ejecting = true;
        }
    }

    @Inject(method = "tryMoveItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/HopperBlockEntity;inventoryFull()Z"))
    private static void goldenHopper$afterEject(Level level, BlockPos pos, BlockState state, HopperBlockEntity hopper, BooleanSupplier $$4, CallbackInfoReturnable<Boolean> cir)
    {
        GoldenHopperBlockEntity.ejecting = false;
    }
}
