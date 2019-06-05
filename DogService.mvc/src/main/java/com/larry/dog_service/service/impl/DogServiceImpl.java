package com.larry.dog_service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.larry.dog_service.entity.Dog;
import com.larry.dog_service.service.DogService;

@Service
public class DogServiceImpl implements DogService {

        public DogServiceImpl()
        {
            System.out.println("Enter DogServiceImpl"); 
            _dogList = new ArrayList<Dog>();
            _dogList.add(new Dog("Lucky", "red",123,1));
            _dogList.add( new Dog("Aslan","white",25,2));
        }

        //#region Field
        private List<Dog> _dogList;

        //#endregion



        public List<Dog> allGog()
        {
            return _dogList;
        }

        public int add(String name, String color, int age)
        {
            int id = -1;

            for(int i=0;i<_dogList.size();++i)
            {
                if(_dogList.get(i).getName() == name)
                {
                    return id;
                }
            }

            id = _dogList.size()+1;
            _dogList.add( new Dog(name, color,age ,id));

            return id;
        }



        public Dog getDogByID(int id)
        {
            Dog result = null;

            for(int i=0;i<_dogList.size();++i)
            {
                Dog dog = _dogList.get(i);
                if(dog.getID() == id)
                {
                    result = dog;
                    break;
                }
            }

            return result;
        }

        public boolean update(int id, String name, String color, int age)
        {
            boolean success =false;
            for(int i=0;i<_dogList.size();++i)
            {
                if(_dogList.get(i).getID()== id)
                {
                    _dogList.get(i).setAge(age);
                    _dogList.get(i).setColor(color);
                    _dogList.get(i).setName(name);
                    success=true;
                }
            }
            return success;
        }

        public boolean delete(int id)
        {
            boolean success =false;
            for(int i=0;i<_dogList.size();++i)
            {
                if(_dogList.get(i).getID() == id)
                {
                    _dogList.remove(i);
                    break;
                }
            }
            return success;
        }

}