package com.testApi.springBootTestApi.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testApi.springBootTestApi.Entities.Command;
import com.testApi.springBootTestApi.Entities.Parameters;
import com.testApi.springBootTestApi.Tests.GenericTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestApiController {
    @RequestMapping("/")
    public Parameters GetHelloWorld(){
        Parameters parameters= new Parameters();
        parameters.setBrowser("Chrome");
        Command command= new Command();
        command.setCommand("Text");
        command.setTarget("TextBox1");
        command.setValue("Deneme");
        command.setTargetType("Id");
        List<Command> commandList=new ArrayList<>();
        commandList.add(command);
        parameters.setCommands(commandList);


        return parameters;
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> saveData(@RequestBody Parameters parameters){
        GenericTest genericTest = new GenericTest();
        Parameters incorectParams= genericTest.Testing(parameters);

        if(!incorectParams.getCommands().isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonresult = "";
            try {
                jsonresult = mapper.writeValueAsString(incorectParams);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }finally {
                return new ResponseEntity<String>(jsonresult,
                        HttpStatus.CONFLICT);
            }

        }else{
            return new ResponseEntity<String>("",
                    HttpStatus.OK);
        }
    }

}
