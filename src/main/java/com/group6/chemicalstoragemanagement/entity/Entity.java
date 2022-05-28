package com.group6.chemicalstoragemanagement.entity;




import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Entity {
    static Long count = 0L;
    public Long ID;
//    public Date createAt;
    public Entity() {
        Entity.count++;
        this.ID = Entity.count;
//        this.createAt = new Date();
    }

    public void setID(Long ID) {
        this.ID = ID;
        Entity.count = ID;
    }

    public Long getID() {
        return ID;
    }

//    public Date getCreateAt(){
//        return createAt;
//    }
}
