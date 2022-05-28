package com.group6.chemicalstoragemanagement.entity;




import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Entity {
    static Long count = 0L;
    public Long ID;
    public LocalDate createAt;
    public Entity() {
        Entity.count++;
        this.ID = Entity.count;
        this.createAt = LocalDate.now();
    }

    public void setID(Long ID) {
        this.ID = ID;
        Entity.count = ID;
    }

    public Long getID() {
        return ID;
    }

    public LocalDate getCreateAt(){
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
}
