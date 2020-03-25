package com.larry.dog_service.controller;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.druid.sql.visitor.functions.If;
// import com.alibaba.fastjson.JSON;
// import com.alibaba.fastjson.JSONArray;
// import com.alibaba.fastjson.JSONObject;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/JSchema")
public class JsonValidateController {

    Logger _logger = Logger.getLogger(JsonValidateController.class);
    
   	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("初始化产品信息")
	@RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity ValidateJson(
    @RequestBody @ApiParam(name = "", required = true) String inputJson) 
    {
    
        Dictionary responseContext= new Hashtable();
        responseContext.put("input",inputJson);
        final String schemaFilePath = "CoreProThingModelSchema.json";
        try{
            InputStream inputStream = getClass().getResourceAsStream(schemaFilePath);
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(new JSONObject(inputJson)); 
        }
        catch (ValidationException e) {
            System.out.println(e.getMessage());
            e.getCausingExceptions().stream()
                .map(ValidationException::getMessage)
                .forEach(System.out::println);
            return new ResponseEntity(responseContext,HttpStatus.BAD_REQUEST);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
			responseContext.put("message",ex.getMessage());
			return new ResponseEntity(responseContext,HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity( responseContext, HttpStatus.OK);
    }
}