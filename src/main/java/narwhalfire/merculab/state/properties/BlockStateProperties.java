package narwhalfire.merculab.state.properties;

import net.minecraft.state.EnumProperty;

public class BlockStateProperties {

    public static final EnumProperty<LabBenchSide> NORTH_BENCH = EnumProperty.create("north", LabBenchSide.class);
    public static final EnumProperty<LabBenchSide> SOUTH_BENCH = EnumProperty.create("south", LabBenchSide.class);
    public static final EnumProperty<LabBenchSide> EAST_BENCH = EnumProperty.create("east", LabBenchSide.class);
    public static final EnumProperty<LabBenchSide> WEST_BENCH = EnumProperty.create("west", LabBenchSide.class);

}

