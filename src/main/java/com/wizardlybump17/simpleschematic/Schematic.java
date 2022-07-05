package com.wizardlybump17.simpleschematic;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;

import java.util.Arrays;
import java.util.Objects;

/**
 * The main class. It contains information about the schematic.<br>
 * The schematic does not store data like items inside chests or inside furnaces.<br>
 * It only stores the {@link BlockData} of the blocks.
 *
 * @param name   the name of the schematic
 * @param xSize  the x size
 * @param ySize  the y size
 * @param zSize  the z size
 * @param blocks the blocks in the schematic
 */
public record Schematic(String name, int xSize, int ySize, int zSize, BlockData[][][] blocks) {

    /**
     * Pastes the schematic at the given location
     *
     * @param location the location to paste the schematic at
     */
    public void paste(Location location) {
        World world = location.getWorld();
        Objects.requireNonNull(world, "world");

        for (int x = 0; x < xSize; x++)
            for (int y = 0; y < ySize; y++)
                for (int z = 0; z < zSize; z++)
                    world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z).setBlockData(blocks[x][y][z]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schematic schematic = (Schematic) o;
        return xSize == schematic.xSize && ySize == schematic.ySize && zSize == schematic.zSize && Objects.equals(name, schematic.name) && Arrays.deepEquals(blocks, schematic.blocks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, xSize, ySize, zSize);
        result = 31 * result + Arrays.deepHashCode(blocks);
        return result;
    }

    @Override
    public String toString() {
        return "Schematic{" +
                "name='" + name + '\'' +
                ", xSize=" + xSize +
                ", ySize=" + ySize +
                ", zSize=" + zSize +
                ", blocks=" + Arrays.toString(blocks) +
                '}';
    }
}
