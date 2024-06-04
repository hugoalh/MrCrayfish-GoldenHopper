package com.mrcrayfish.goldenhopper.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class NeoForgeRecipeGen extends RecipeProvider
{
    public NeoForgeRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        new CommonRecipeProvider(output, RecipeProvider::has, RecipeProvider::has).run();
    }
}
