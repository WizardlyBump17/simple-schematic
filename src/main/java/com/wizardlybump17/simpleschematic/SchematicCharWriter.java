package com.wizardlybump17.simpleschematic;

import com.wizardlybump17.simpleschematic.cache.SchematicCharsCache;
import com.wizardlybump17.simpleschematic.iterator.BlockIterator;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.Optional;

@UtilityClass
public class SchematicCharWriter {

    /**
     * Writes text in the given location.
     * @param start the location to start writing at
     * @param end the location to end writing at
     * @param distance the distance between each schematic
     * @param x if the schematic should be written in the x direction
     * @param text the text to write
     * @param cache the schematic cache
     */
    public static void write(@NonNull Location start, @NonNull Location end, @NonNull Vector distance, boolean x, @NonNull String text, @NonNull SchematicCharsCache cache) {
        new BlockIterator(start, end).forEachRemaining(location -> location.setType(Material.AIR));

        Location location = start.clone();
        char[] chars = text.toCharArray();
        for (char c : chars) {
            Optional<Schematic> optional = cache.get(c);
            if (optional.isEmpty())
                continue;

            Schematic schematic = optional.get();
            schematic.paste(location);
            location.add(x ? schematic.xSize() : 0, 0, x ? 0 : schematic.zSize()).add(distance);
        }
    }
}
