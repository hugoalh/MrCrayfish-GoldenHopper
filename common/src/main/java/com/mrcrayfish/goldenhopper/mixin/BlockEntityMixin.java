package com.mrcrayfish.goldenhopper.mixin;

import com.mrcrayfish.goldenhopper.blockentity.BlockEntityTypeSetter;
import com.mrcrayfish.goldenhopper.blockentity.GoldenHopperBlockEntity;
import com.mrcrayfish.goldenhopper.core.ModBlockEntities;
import com.mrcrayfish.goldenhopper.core.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    /*
     * Patches the validation for the block state. This validation happens before block entity
     * type can be injected. To fix this issue, we just patch the method and do validation ourselves
     * to avoid throwing an exception.
     */
    @SuppressWarnings("DataFlowIssue")
    @Inject(method = "validateBlockState", at = @At(value = "HEAD"), cancellable = true)
    private void goldenHopper$validateFix(BlockState state, CallbackInfo ci)
    {
        BlockEntity entity = (BlockEntity) (Object) this;
        // getType() is overridden in GoldenHopperBlockEntity, so we can perform the correct check
        if(entity instanceof GoldenHopperBlockEntity && state.is(ModBlocks.GOLDEN_HOPPER.get()) && entity.getType().isValid(state))
        {
            ci.cancel();
        }
    }
}
