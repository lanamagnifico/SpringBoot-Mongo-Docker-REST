package p.vitaly.restexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import p.vitaly.restexample.dto.PhoneDto;
import p.vitaly.restexample.dto.ResponseDto;
import p.vitaly.restexample.service.Service;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("phones")
public final class PhoneController {

    @Autowired
    private Service<PhoneDto, Long> phoneService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PhoneDto> getAll(@RequestParam(required = false) Map<String, String> params){
        return phoneService.getAllWithParams(params);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PhoneDto get(@PathVariable long id) {
        return phoneService.getById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody PhoneDto phone) {
        ResponseDto response = phone.getId() == null ?
                new ResponseDto(HttpStatus.CREATED, "Entity created") :
                new ResponseDto(HttpStatus.OK, "Entity updated");
        phoneService.save(phone);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) {
        phoneService.delete(id);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "Entity deleted"));
    }
}
