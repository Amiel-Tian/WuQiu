package com.example.renwushu.utils;

import java.util.UUID;

public class IdHelp {
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
