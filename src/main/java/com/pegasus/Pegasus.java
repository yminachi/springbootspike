package com.pegasus;

import org.springframework.data.annotation.Id;

public class Pegasus {
    @Id
    public String id;

    public String name;
    public String title;

    public Pegasus() {
    }

    public Pegasus(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String toString() {
        return name + " " + title;
    }
}
