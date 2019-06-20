package narwhalfire.merculab.tileentity.labware;

import narwhalfire.merculab.init.MercuLab;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityJar extends TileEntity {


    public TileEntityJar() {
        this(MercuLab.TileEntityTypes.JAR);
    }

    public TileEntityJar(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
