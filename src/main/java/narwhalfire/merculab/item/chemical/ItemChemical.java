package narwhalfire.merculab.item.chemical;

import narwhalfire.merculab.chemical.Chemical;
import net.minecraft.item.Item;

public class ItemChemical extends Item {

    private final Chemical chemical;

    public ItemChemical(Chemical chemical, Properties properties) {
        super(properties);
        this.chemical = chemical;
    }
}
