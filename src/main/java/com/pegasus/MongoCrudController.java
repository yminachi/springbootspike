package com.pegasus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongo")
public class MongoCrudController {

    @Autowired
    private PegasusRepository pegasusRepository;

    @RequestMapping(path = "/pegasus", method = RequestMethod.PUT)
    public String save(@RequestBody Pegasus pegasus) {
        pegasusRepository.save(pegasus);
        return pegasus.id;
    }

    @RequestMapping(path = "/pegasus/{id}/**", method = RequestMethod.GET)
    public String get(@PathVariable String id) {
        return pegasusRepository.findOne(id).toString();
    }
}