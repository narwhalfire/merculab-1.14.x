package narwhalfire.merculab.init;

import narwhalfire.merculab.block.chemical.ChemicalBlock;
import narwhalfire.merculab.block.labware.*;
import narwhalfire.merculab.chemical.Chemical;
import narwhalfire.merculab.item.chemical.ChemicalItem;
import narwhalfire.merculab.tileentity.labware.BeakerTileEntity;
import narwhalfire.merculab.tileentity.labware.FlaskTileEntity;
import narwhalfire.merculab.tileentity.labware.JarTileEntity;
import narwhalfire.merculab.tileentity.labware.VialTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod(MercuLab.MODID)
@SuppressWarnings("unused")
public final class MercuLab {

    public static final String MODID = "merculab";
    private static final Logger LOGGER = LogManager.getLogger();

    public MercuLab() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        MinecraftForge.EVENT_BUS.register(this);
        init();
    }

    private void setup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {

    }

    private void enqueueIMC(InterModEnqueueEvent event) {

    }

    private void processIMC(InterModProcessEvent event) {

    }

    /**
     * Initializing these before the registry events are fired allows the registering of blocks and items
     * depending on what chemicals are being registered.
     */
    private void init() {
        priorityInit();
        TileEntityTypes.init();
    }

    /**
     * Chemicals must be initialized first, then blocks, then items, then whatever
     */
    private void priorityInit() {
        Chemicals.init();
        Blocks.init();
        Items.init();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        // Game Objects
        @SubscribeEvent
        public static void onRegistries(final RegistryEvent.NewRegistry event) {
            Registry.CHEMICALS = new RegistryBuilder<Chemical>().setName(new ResourceLocation(MODID, "chemicals"))
                                                                .setDefaultKey(new ResourceLocation(MODID, "nil"))
                                                                .setType(Chemical.class)
                                                                .create();
        }

        @SubscribeEvent
        public static void onBlocks(final RegistryEvent.Register<Block> event) {
            Blocks.registerFor(event.getRegistry());
        }

        @SubscribeEvent
        public static void onFluids(final RegistryEvent.Register<Fluid> event) {

        }

        @SubscribeEvent
        public static void onItems(final RegistryEvent.Register<Item> event) {
            Items.registerFor(event.getRegistry());
        }

        @SubscribeEvent
        public static void onEffects(final RegistryEvent.Register<Effect> event) {

        }

        @SubscribeEvent
        public static void onBiomes(final RegistryEvent.Register<Biome> event) {

        }

        @SubscribeEvent
        public static void onSounds(final RegistryEvent.Register<SoundEvent> event) {

        }

        @SubscribeEvent
        public static void onPotions(final RegistryEvent.Register<Potion> event) {

        }

        @SubscribeEvent
        public static void onEnchantments(final RegistryEvent.Register<Enchantment> event) {

        }

        @SubscribeEvent
        public static void onEntities(final RegistryEvent.Register<EntityType<?>> event) {

        }

        @SubscribeEvent
        public static void onTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
            TileEntityTypes.registerFor(event.getRegistry());
        }

        @SubscribeEvent
        public static void onParticles(final RegistryEvent.Register<ParticleType<?>> event) {

        }

        @SubscribeEvent
        public static void onContainers(final RegistryEvent.Register<ContainerType<?>> event) {

        }

        @SubscribeEvent
        public static void onPaintings(final RegistryEvent.Register<PaintingType> event) {

        }

        @SubscribeEvent
        public static void onRecipes(final RegistryEvent.Register<IRecipeSerializer<?>> event) {

        }

        @SubscribeEvent
        public static void onStats(final RegistryEvent.Register<StatType<?>> event) {

        }

        // Village
        @SubscribeEvent
        public static void onProfessions(final RegistryEvent.Register<VillagerProfession> event) {

        }

        @SubscribeEvent
        public static void onPOIs(final RegistryEvent.Register<PointOfInterestType> event) {

        }

        @SubscribeEvent
        public static void onMemoryModules(final RegistryEvent.Register<MemoryModuleType<?>> event) {

        }

        @SubscribeEvent
        public static void onSensors(final RegistryEvent.Register<SensorType<?>> event) {

        }

        @SubscribeEvent
        public static void onSchedules(final RegistryEvent.Register<Schedule> event) {

        }

        @SubscribeEvent
        public static void onActivities(final RegistryEvent.Register<Activity> event) {

        }

        // WorldGen
        @SubscribeEvent
        public static void onWorldCarvers(final RegistryEvent.Register<WorldCarver<?>> event) {

        }

        @SubscribeEvent
        public static void onSurfaceBuilders(final RegistryEvent.Register<SurfaceBuilder<?>> event) {

        }

        @SubscribeEvent
        public static void onFeatures(final RegistryEvent.Register<Feature<?>> event) {

        }

        @SubscribeEvent
        public static void onDecorators(final RegistryEvent.Register<Placement<?>> event) {

        }

        @SubscribeEvent
        public static void onBiomeProviders(final RegistryEvent.Register<BiomeProviderType<?, ?>> event) {

        }

        @SubscribeEvent
        public static void onChunkGenerators(final RegistryEvent.Register<ChunkGeneratorType<?, ?>> event) {

        }

        @SubscribeEvent
        public static void onChunkStatus(final RegistryEvent.Register<ChunkStatus> event) {

        }

        // Forge
        @SubscribeEvent
        public static void onDims(final RegistryEvent.Register<ModDimension> event) {

        }

        @SubscribeEvent
        public static void onDataSerializers(final RegistryEvent.Register<DataSerializerEntry> event) {

        }

        // MercuLab
        @SubscribeEvent
        public static void onChemicals(final RegistryEvent.Register<Chemical> event) {
            Chemicals.registerFor(event.getRegistry());
        }

    }

    public static final class Registry {

        public static IForgeRegistry<Chemical> CHEMICALS = null;

    }

    @ObjectHolder(MODID)
    public static final class Blocks {

        public static final Block LAB_BENCH = null;

        public static final Block BEAKER = null;
        public static final Block FLASK = null;
        public static final Block JAR = null;
        public static final Block VIAL = null;

        private static List<Block> insts = new ArrayList<>();
        private static List<Block> special = new ArrayList<>();

        static void registerFor(IForgeRegistry<Block> registry) {
            insts.forEach(registry::register);
        }

        static void init() {
            make(new BeakerBlock(Block.Properties.create(Material.GLASS)), "beaker", insts);
            make(new FlaskBlock(Block.Properties.create(Material.GLASS)), "flask", insts);
            make(new JarBlock(Block.Properties.create(Material.GLASS)), "jar", insts);
            make(new VialBlock(Block.Properties.create(Material.GLASS)), "vial", insts);
            make(new LabBenchBlock(Block.Properties.create(Material.ROCK)), "lab_bench", insts);
            Chemicals.blockInsts.forEach(chemical -> make(new ChemicalBlock(chemical, Block.Properties.create(Material.ROCK)),
                                                          chemical.getRegistryName().getPath() + "_block", insts));
        }

        private static void make(Block block, String id, List<Block> list) {
            list.add(block.setRegistryName(MODID, id));
        }
    }

    @ObjectHolder(MODID)
    public static final class Items {

        private static List<Item> insts = new ArrayList<>();

        static void registerFor(IForgeRegistry<Item> registry) {
            insts.forEach(registry::register);
        }

        static void init() {
            Blocks.insts.forEach(block -> make(new BlockItem(block, new Item.Properties()), block.getRegistryName()
                                                                                                 .getPath(), insts));
            Chemicals.itemInsts.forEach(chemical -> make(new ChemicalItem(chemical, new Item.Properties()), chemical.getRegistryName()
                                                                                                                    .getPath(), insts));
            Chemicals.blockInsts.forEach(chemical -> make(new ChemicalItem(chemical, new Item.Properties()), chemical.getRegistryName()
                                                                                                                     .getPath(), insts));
        }

        private static void make(Item item, String id, List<Item> list) {
            list.add(item.setRegistryName(MODID, id));
        }
    }

    @ObjectHolder(MODID)
    public static final class Potions {

    }

    @ObjectHolder(MODID)
    public static final class Biomes {

    }

    @ObjectHolder(MODID)
    public static final class SoundEvents {

    }

    @ObjectHolder(MODID)
    public static final class PotionTypes {

    }

    @ObjectHolder(MODID)
    public static final class Enchantments {

    }

    @ObjectHolder(MODID)
    public static final class VillagerProfessions {

    }

    @ObjectHolder(MODID)
    public static final class EntityTypes {

    }

    @ObjectHolder(MODID)
    public static final class TileEntityTypes {

        public static final TileEntityType BEAKER = null;
        public static final TileEntityType FLASK = null;
        public static final TileEntityType JAR = null;
        public static final TileEntityType VIAL = null;

        private static List<TileEntityType<?>> insts = new ArrayList<>();

        static void registerFor(IForgeRegistry<TileEntityType<?>> registry) {
            insts.forEach(registry::register);
        }

        static void init() {
            make(BeakerTileEntity::new, "beaker", insts);
            make(FlaskTileEntity::new, "flask", insts);
            make(JarTileEntity::new, "jar", insts);
            make(VialTileEntity::new, "vial", insts);
        }

        private static void make(Supplier<? extends TileEntity> s, String id, List<TileEntityType<?>> list) {
            list.add(TileEntityType.Builder.create(s).build(null).setRegistryName(MODID, id));
        }
    }

    @ObjectHolder(MODID)
    public static final class ModDimensions {

    }

    @ObjectHolder(MODID)
    public static final class DataSerializers {

    }

    @ObjectHolder(MODID)
    public static final class Chemicals {

        public static final Chemical SODIUM_CHLORIDE = null;
        public static final Chemical WATER = null;
        public static final Chemical METHANE = null;
        public static final Chemical FERRIC_OXIDE = null;
        public static final Chemical TUNGSTEN_CARBIDE = null;

        private static List<Chemical> normInsts = new ArrayList<>();
        private static List<Chemical> blockInsts = new ArrayList<>();
        private static List<Chemical> fluidInsts = new ArrayList<>();
        private static List<Chemical> itemInsts = new ArrayList<>();

        static void registerFor(IForgeRegistry<Chemical> registry) {
            normInsts.forEach(registry::register);
            blockInsts.forEach(registry::register);
            fluidInsts.forEach(registry::register);
            itemInsts.forEach(registry::register);
        }

        static void init() {
            make(new Chemical(Chemical.Properties.SODIUM_CHLORIDE), "sodium_chloride", itemInsts);
            make(new Chemical(Chemical.Properties.WATER), "water", normInsts);
            make(new Chemical(Chemical.Properties.METHANE), "methane", fluidInsts);
            make(new Chemical(Chemical.Properties.FERRIC_OXIDE), "ferric_oxide", itemInsts);
            make(new Chemical(Chemical.Properties.TUNGSTEN_CARBIDE), "tungsten_carbide", blockInsts);
        }

        private static void make(Chemical chemical, String id, List<Chemical> list) {
            list.add(chemical.setRegistryName(MODID, id));
        }

    }

}
