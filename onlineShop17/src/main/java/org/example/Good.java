package org.example;

import java.util.HashMap;
import java.util.Map;

public class Good {

    private final HashMap<String, String> nameGood;

    public Good() {
        nameGood = new HashMap<>();
        nameGood.put("Book", "5.5");
        nameGood.put("pencil", "1.85");
        nameGood.put("ruler", "0.34");
    }

    public HashMap<String, String> getNameGood() {
        return nameGood;
    }
}
