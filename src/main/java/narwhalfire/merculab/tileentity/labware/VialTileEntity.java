package narwhalfire.merculab.tileentity.labware;

import narwhalfire.merculab.init.MercuLab;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class VialTileEntity extends TileEntity {


    public VialTileEntity() {
        this(MercuLab.TileEntityTypes.VIAL);
    }

    public VialTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

}
