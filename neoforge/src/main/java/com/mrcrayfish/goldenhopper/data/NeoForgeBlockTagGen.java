package com.mrcrayfish.goldenhopper.data;

import com.mrcrayfish.goldenhopper.Constants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Author: MrCrayfish
 */
public class NeoForgeBlockTagGen extends BlockTagsProvider
{
    public NeoForgeBlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        CommonBlockTagsProvider.accept(key -> new PlatformTagBuilder<>(this.tag(key)));
    }
}
