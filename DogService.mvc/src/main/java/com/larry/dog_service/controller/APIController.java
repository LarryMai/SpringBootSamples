package com.larry.dog_service.controller;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.larry.dog_service.entity.Dog;
import com.larry.dog_service.service.DogService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dog")
public class APIController {

      Logger _logger = Logger.getLogger(APIController.class);

      @Autowired
      DogService _dogService;

      @GetMapping
      @ResponseBody
      public ResponseEntity<Object> getAll() throws JsonProcessingException {
            _logger.info("enter APIController::getAll");
            List<Dog> dogList = _dogService.allGog();
            ObjectMapper mapper = new ObjectMapper();
            String value = mapper.writeValueAsString(dogList);
            return new ResponseEntity<Object>(value, HttpStatus.OK);
      }

      @GetMapping(path = "/{id}"
      , produces = "application/json"
      )
      @ResponseBody
      public ResponseEntity<Object> get(@PathVariable  int id) throws JsonProcessingException {
            
            _logger.info("enter APIController::get with id : " + id );
            
            Dog dog = _dogService.getDogByID(id);
           
            if(dog == null)
            {
                  Dictionary response = new Hashtable();
                  response.put("statusCode",HttpStatus.NOT_FOUND.value());
                  // not existed
                  response.put("msg","dog id : " + id + " doesn't exist");

                  ObjectMapper mapper = new ObjectMapper();
                  String value = mapper.writeValueAsString(response);
                  return new ResponseEntity<Object>(value ,HttpStatus.NOT_FOUND);
            }
            else
            {
                  ObjectMapper mapper = new ObjectMapper();
                  String value = mapper.writeValueAsString(dog);
                  return new ResponseEntity<Object>(value ,HttpStatus.OK);
            }

      }


      @PutMapping(path = ""
      , consumes = "application/json"
      , produces = "application/json")
      @ResponseBody
      public ResponseEntity<Dictionary> update(@RequestBody String dogJson)
                  throws JsonParseException, JsonMappingException, IOException
      {
            _logger.info("enter APIController::update with body : " + dogJson );
            ObjectMapper m = new ObjectMapper();
            Map map = m.readValue(dogJson, Map.class);
            
            //System.out.println("mapper begins");
            String name = !map.containsKey("name")?"": (String)map.get("name");
            String color = !map.containsKey("color")?"": (String)map.get("color"); 
            int age = !map.containsKey("age")?-1:(int)map.get("age");
            int id = !map.containsKey("id")?-1:(int)map.get("id");

            Dictionary response = new Hashtable();
            response.put("statusCode",HttpStatus.RESET_CONTENT);
            if(name == "" || color == "" || age == -1 || id == -1)
            {
                   response.put("statusCode",HttpStatus.BAD_REQUEST);
                   response.put("msg", "bad input data");
                   return  new ResponseEntity<Dictionary>(response ,HttpStatus.BAD_REQUEST);
            }

            boolean success = _dogService.update(id, name, color, age);
            if(!success)
            {
                  response.put("statusCode",HttpStatus.INTERNAL_SERVER_ERROR);
                  response.put("msg", "Failure occurs.");
                  return  new ResponseEntity<Dictionary>(response ,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else
            {
                  response.put("msg", "success");
                  return  new ResponseEntity<Dictionary>(response ,HttpStatus.RESET_CONTENT);
            }
      }

      @PostMapping(path = ""
      , consumes = "application/json"
      , produces = "application/json")
      @ResponseBody
      public ResponseEntity<Dictionary> Add(@RequestBody String dogJson)
                  throws JsonParseException, JsonMappingException, IOException
     {
            ObjectMapper m = new ObjectMapper();
            Map map = m.readValue(dogJson, Map.class);
            
            //System.out.println("mapper begins");
            String name = !map.containsKey("name")?"": (String)map.get("name");
            String color = !map.containsKey("color")?"": (String)map.get("color"); 
            int age = !map.containsKey("age")?-1:(int)map.get("age");

            //System.out.println("mapper ends");
            Dictionary response = new Hashtable();
            response.put("statusCode",HttpStatus.CREATED);
           if(name == "" || color == "" || age == -1)
           {
                  response.put("statusCode",HttpStatus.BAD_REQUEST);
                  response.put("msg", "bad input data");
                  return  new ResponseEntity<Dictionary>(response ,HttpStatus.BAD_REQUEST);
           }
           
           int id = _dogService.add(name, color, age);
           if(id == -1)
           {
                  
                  response.put("msg", "The dog has existed");
                  return  new ResponseEntity<Dictionary>(response, HttpStatus.CREATED);
           }
           else
           {
                  response.put("msg", "success");
                  return  new ResponseEntity<Dictionary>(response ,HttpStatus.CREATED);
           }
      }

     @DeleteMapping(path = "/{id}"
     , produces = "application/json")
     @ResponseBody
     public ResponseEntity<Dictionary> delete( @PathVariable int id) throws JsonParseException, JsonMappingException, IOException
     {
           
            Dog dog = _dogService.getDogByID(id);
            Dictionary response = new Hashtable();
            response.put("statusCode",HttpStatus.OK.value());
            if(dog == null)
            {
                  // not existed
                  response.put("msg","dog id : " + id + "doesn't exist");
                  return new ResponseEntity<Dictionary>(response ,HttpStatus.NOT_FOUND);
            }
            else
            {
                  _dogService.delete(id);
                  response.put("msg","success");
                  return new ResponseEntity<Dictionary>(response ,HttpStatus.OK);
            }
     }

}

