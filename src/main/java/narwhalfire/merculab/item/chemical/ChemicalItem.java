package narwhalfire.merculab.item.chemical;

import narwhalfire.merculab.chemical.Chemical;
import net.minecraft.item.Item;

public class ChemicalItem extends Item {

    private final Chemical chemical;

    public ChemicalItem(Chemical chemical, Properties properties) {
        super(properties);
        this.chemical = chemical;
    }
}
