package com.slymask3.instantblocks.config.entry;

import java.util.Arrays;
import java.util.List;

public class ColorSet {
    public String name;
    public List<String> colors;

    public ColorSet() {
        this.name = "Unnamed";
        this.colors = List.of("white");
    }

    public ColorSet(String name, String... colors) {
        this.name = name;
        this.colors = Arrays.asList(colors);
    }
}