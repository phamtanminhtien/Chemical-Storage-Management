package com.group6.chemicalstoragemanagement.entity;




import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Entity {
    static Long count = 0L;
    public Long ID;

    public Entity() {
        Entity.count++;
        this.ID = Entity.count;
    }

    public Long getID() {
        return ID;
    }
}
