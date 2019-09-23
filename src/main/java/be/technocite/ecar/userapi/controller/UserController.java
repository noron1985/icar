package be.technocite.ecar.userapi.controller;

import be.technocite.ecar.carapi.dto.CarDtoBuyer;
import be.technocite.ecar.user.service.UserService;
import be.technocite.ecar.userapi.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "${spring.data.rest.base-path}/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        UserDto user = userService.findById(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        }else {
            return ResponseEntity.noContent().build();
        }
    }
}
