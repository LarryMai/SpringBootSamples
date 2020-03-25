package com.larry.dog_service.entity;

public class Dog
{
    private String _name;
    
    private String _color;

    private int _age;

    private int _id;

    public Dog(String name, String color, int age, int id)
    {
        _name= name;
        _color = color;
        _age = age;
        _id = id;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public String getColor()
    {
        return _color;
    }

    public void setColor(String color){
        _color = color;
    }

    public int getAge()
    {
        return _age;
    }

    public void setAge(int age){
        _age = age;
    }

    public int getID()
    {
        return _id;
    }

    public void setID(int id){
        _id = id;
    }


    
}