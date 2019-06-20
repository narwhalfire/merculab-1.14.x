package narwhalfire.merculab.tileentity.labware;

import narwhalfire.merculab.init.MercuLab;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityBeaker extends TileEntity {


    public TileEntityBeaker() {
        this(MercuLab.TileEntityTypes.BEAKER);
    }

    public TileEntityBeaker(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
