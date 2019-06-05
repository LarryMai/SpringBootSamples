package com.larry.dog_service.service;

import java.util.List;
import com.larry.dog_service.entity.Dog;

public interface DogService {
	public List<Dog> allGog();
    public Dog getDogByID(int id);
    public int add(String name, String color, int age);
    public boolean update(int id, String name, String color, int age);
    public boolean delete(int id);
}
