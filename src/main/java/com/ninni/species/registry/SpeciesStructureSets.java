package com.ninni.species.registry;

import com.ninni.species.Species;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class SpeciesStructureSets {
    public static final ResourceKey<StructureSet> WRAPTOR_COOPS = register("wraptor_coops");
    public static final ResourceKey<StructureSet> PALEONTOLOGY_DIG_SITE = register("paleontology_dig_site");
    public static final ResourceKey<StructureSet> LIBRA = register("libra");
    public static final ResourceKey<StructureSet> SPECTRALIBUR_CHAMBER = register("spectralibur_chamber");

    public static void bootstrap(BootstapContext<StructureSet> bootstapContext) {
        HolderGetter<Structure> holderGetter = bootstapContext.lookup(Registries.STRUCTURE);
        bootstapContext.register(WRAPTOR_COOPS, new StructureSet(holderGetter.getOrThrow(SpeciesStructureKeys.WRAPTOR_COOP), new RandomSpreadStructurePlacement(64, 8, RandomSpreadType.LINEAR, 867700449)));
        bootstapContext.register(PALEONTOLOGY_DIG_SITE, new StructureSet(holderGetter.getOrThrow(SpeciesStructureKeys.PALEONTOLOGY_DIG_SITE), new RandomSpreadStructurePlacement(8, 5, RandomSpreadType.LINEAR, 867700449)));
        bootstapContext.register(LIBRA, new StructureSet(holderGetter.getOrThrow(SpeciesStructureKeys.LIBRA), new RandomSpreadStructurePlacement(6, 5, RandomSpreadType.LINEAR, 867700449)));
        bootstapContext.register(SPECTRALIBUR_CHAMBER, new StructureSet(holderGetter.getOrThrow(SpeciesStructureKeys.SPECTRALIBUR_CHAMBER), new RandomSpreadStructurePlacement(6, 5, RandomSpreadType.LINEAR, 867700449)));
    }

    private static ResourceKey<StructureSet> register(String string) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(Species.MOD_ID, string));
    }
}
