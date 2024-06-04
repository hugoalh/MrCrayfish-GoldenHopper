package com.mrcrayfish.goldenhopper.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public class NeoForgeRecipeGen extends RecipeProvider
{
    public NeoForgeRecipeGen(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        new CommonRecipeProvider(output, RecipeProvider::has, RecipeProvider::has).run();
    }
}
