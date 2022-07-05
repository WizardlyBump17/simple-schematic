package com.wizardlybump17.simpleschematic.iterator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BlockIterator implements Iterator<Block>, Iterable<Block> {

    private final World world;
    private final Vector min;
    private final Vector max;
    private final Vector current;

    public BlockIterator(World world, Vector v1, Vector v2) {
        this.world = world;
        this.min = Vector.getMinimum(v1, v2);
        this.max = Vector.getMaximum(v1, v2);
        this.current = min.clone().setX(min.getX() - 1);
    }

    public BlockIterator(Location loc1, Location loc2) {
        if (loc1.getWorld() != loc2.getWorld())
            throw new IllegalArgumentException("loc1 and loc2 must be in the same world");

        this.world = loc1.getWorld();
        this.min = Vector.getMinimum(loc1.toVector(), loc2.toVector());
        this.max = Vector.getMaximum(loc1.toVector(), loc2.toVector());
        this.current = min.clone().setX(min.getX() - 1);
    }

    @Override
    public boolean hasNext() {
        return current.getY() <= max.getY();
    }

    @Override
    public Block next() {
        if (!hasNext())
            throw new NoSuchElementException("end of iterator");

        skip();
        if (current.getY() > max.getY())
            return world.getBlockAt(max.getBlockX(), max.getBlockY(), max.getBlockZ());

        return world.getBlockAt(current.getBlockX(), current.getBlockY(), current.getBlockZ());
    }

    private void skip() {
        current.setX(current.getX() + 1);
        if (current.getX() > max.getX()) {
            current.setX(min.getX());
            current.setZ(current.getZ() + 1);
        }

        if (current.getZ() > max.getZ()) {
            current.setZ(min.getZ());
            current.setY(current.getY() + 1);
        }
    }

    @NotNull
    @Override
    public Iterator<Block> iterator() {
        return new BlockIterator(world, min, max);
    }
}
