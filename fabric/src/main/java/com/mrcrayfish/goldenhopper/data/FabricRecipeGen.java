package com.mrcrayfish.goldenhopper.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

/**
 * Author: MrCrayfish
 */
public class FabricRecipeGen extends FabricRecipeProvider
{
    public FabricRecipeGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput output)
    {
        new CommonRecipeProvider(output, RecipeProvider::has, RecipeProvider::has).run();
    }
}
