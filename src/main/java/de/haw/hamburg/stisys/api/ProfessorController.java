package de.haw.hamburg.stisys.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.haw.hamburg.stisys.core.AccessControlProxy;
import de.haw.hamburg.stisys.core.ControlledObject;

@RestController
public class ProfessorController {

    private final AccessControlProxy<ControlledObject> controlledDatabase;

    @Autowired
    public ProfessorController(AccessControlProxy<ControlledObject> controlledDatabase) {
        this.controlledDatabase = controlledDatabase;
    }

    @PostMapping("/api/createProfessor")
    public int createProfessor(@RequestBody Map<String, Object> request) {
        String name = request.get("name").toString();
        String password = request.get("password").toString();

        // Create a controlled professor object
        AccessControlProxy<ControlledObject> controlledProfessor = controlledDatabase.createProfessor(name, password);
        // Save the controlled professor in the database and retrieve the professor ID
        controlledProfessor.setUserId(controlledDatabase.saveProfessor(controlledProfessor));

        // Return the professor ID as the response
        return controlledProfessor.getId();
    }
}


