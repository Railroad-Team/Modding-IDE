package io.github.railroad.objects;

public enum JavaClassTypes {
    CLASS(1),
    ENUM(3),
    INTERFACE(2);

    public final int ID;

    JavaClassTypes(int id){
        ID = id;
    }

    JavaClassTypes(){
        ID = -1;
    }
}
