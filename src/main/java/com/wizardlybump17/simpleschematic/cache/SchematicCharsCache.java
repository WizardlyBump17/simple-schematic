package com.wizardlybump17.simpleschematic.cache;

import com.wizardlybump17.simpleschematic.Schematic;
import com.wizardlybump17.wlib.object.Cache;
import com.wizardlybump17.wlib.object.Pair;
import org.jetbrains.annotations.NotNull;

public class SchematicCharsCache extends Cache<Character, Schematic, Pair<Character, Schematic>> {

    @Override
    public @NotNull Pair<Character, Schematic> apply(Pair<Character, Schematic> pair) {
        return pair;
    }
}
