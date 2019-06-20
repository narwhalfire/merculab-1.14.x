package narwhalfire.merculab.tileentity.labware;

import narwhalfire.merculab.init.MercuLab;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class BeakerTileEntity extends TileEntity {


    public BeakerTileEntity() {
        this(MercuLab.TileEntityTypes.BEAKER);
    }

    public BeakerTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
