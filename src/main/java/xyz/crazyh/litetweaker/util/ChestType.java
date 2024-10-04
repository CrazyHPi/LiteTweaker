package xyz.crazyh.litetweaker.util;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum ChestType implements IStringSerializable {
    LEFT("left"),
    RIGHT("right"),
    SINGLE("single");

    public static final PropertyEnum<ChestType> TYPE = PropertyEnum.create("type", ChestType.class);
    private final String name;

    ChestType(String name) {
        this.name = name;
    }

    public static ChestType getType(final EnumFacing chestFace, final EnumFacing connection) {
        if (chestFace.getAxis().isVertical() || connection.getAxis().isVertical()) {
            return SINGLE;
        }

        if ((chestFace.getHorizontalIndex() + 1) % 4 == connection.getHorizontalIndex()) {
            return RIGHT;
        }

        if ((chestFace.getHorizontalIndex() + 3) % 4 == connection.getHorizontalIndex()) {
            return LEFT;
        }

        return SINGLE;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
