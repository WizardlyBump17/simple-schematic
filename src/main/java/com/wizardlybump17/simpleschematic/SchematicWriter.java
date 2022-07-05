package com.wizardlybump17.simpleschematic;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@UtilityClass
public class SchematicWriter {

    /**
     * Writes a schematic to a file.
     * @param schematic the schematic to write
     * @param target the file to write to
     */
    public static void write(@NonNull Schematic schematic, @NonNull File target) {
        try {
            if (!target.exists()) {
                target.getParentFile().mkdirs();
                target.createNewFile();
            }

            try (FileWriter writer = new FileWriter(target)) {
                writer.write(schematic.name() + "\n\n");
                writer.write(schematic.xSize() + "," + schematic.ySize() + "," + schematic.zSize() + "\n\n");
                for (int x = 0; x < schematic.xSize(); x++) {
                    for (int y = 0; y < schematic.ySize(); y++) {
                        for (int z = 0; z < schematic.zSize(); z++) {
                            writer.write(schematic.blocks()[x][y][z].getAsString() + (z + 1 == schematic.zSize() ? "" : "|"));
                        }
                        writer.write("\n");
                    }
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
