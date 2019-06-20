package narwhalfire.merculab.state.properties;

import net.minecraft.util.IStringSerializable;

public enum LabBenchSide implements IStringSerializable {

    NULL(0, "null"),
    BENCH(1, "bench"),
    FULL(2, "full");

    private final int meta;
    private final String name;

    LabBenchSide(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public int getMeta() {
        return meta;
    }

    public String getName() {
        return name;
    }

}
