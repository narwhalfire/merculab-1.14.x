package narwhalfire.merculab.tileentity.labware;

import narwhalfire.merculab.init.MercuLab;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityVial extends TileEntity {


    public TileEntityVial() {
        this(MercuLab.TileEntityTypes.VIAL);
    }

    public TileEntityVial(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

}
