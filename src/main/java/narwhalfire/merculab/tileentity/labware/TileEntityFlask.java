package narwhalfire.merculab.tileentity.labware;

import narwhalfire.merculab.init.MercuLab;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityFlask extends TileEntity {


    public TileEntityFlask() {
        this(MercuLab.TileEntityTypes.FLASK);
    }

    public TileEntityFlask(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
