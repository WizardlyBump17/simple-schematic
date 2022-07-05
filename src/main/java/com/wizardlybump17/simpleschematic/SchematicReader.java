package com.wizardlybump17.simpleschematic;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SchematicReader {

    /**
     * @param file the file to read
     * @return the schematic from the file
     */
    @Nullable
    public static Schematic read(@NonNull File file) {
        if (!file.exists())
            return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = getLines(reader);

            String name = lines.get(0);

            String[] size = lines.get(1).split(",");
            int xSize = Integer.parseInt(size[0]);
            int ySize = Integer.parseInt(size[1]);
            int zSize = Integer.parseInt(size[2]);

            BlockData[][][] blocks = new BlockData[xSize][ySize][zSize];
            int index = 2;
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    for (int z = 0; z < zSize; z++) {
                        blocks[x][y][z] = Bukkit.createBlockData(lines.get(index).split("\\|")[z]);
                    }
                    index++;
                }
            }

            return new Schematic(name, xSize, ySize, zSize, blocks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> getLines(BufferedReader reader) {
        List<String> list = new ArrayList<>();

        try {
            String line;
            while ((line = reader.readLine()) != null)
                list.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.removeIf(s -> s.trim().isBlank() || s.trim().isEmpty());

        return list;
    }
}
