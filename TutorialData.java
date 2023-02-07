package com.example.demo.controller;


import com.example.demo.model.BioData;
import com.example.demo.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialData {

    @Autowired
    DataRepository dataRepo;
    @RequestMapping(method = RequestMethod.GET, value = "/data")
    public ResponseEntity<List<BioData>> getAllData(@RequestParam(required = false) String name) {
        try {
            List<BioData> names = new ArrayList<BioData>();
            if (name == null) dataRepo.findAll().forEach(names::add);


            if (names.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(names, HttpStatus.OK);

        } catch (
                Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<BioData> getTutorialById(@PathVariable("id") long id) {
        Optional<BioData> tutorialData = dataRepo.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/data")
    public ResponseEntity<BioData> createData(@RequestBody BioData data) {
        try {
            List<BioData> d_name= dataRepo.findUser(data);


            if(d_name.size()==0){
                BioData _data = dataRepo
                        .save(new BioData(data.getName() ,  data.getPhone()));

                return new ResponseEntity<>(_data, HttpStatus.CREATED);
            }
           else{
                return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
            }
        }
           catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<HttpStatus> deleteData(@PathVariable("id") Long id)
    {
        try{
            dataRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
    }

    @PutMapping("/data/{id}")
    public ResponseEntity<BioData> updateTutorial(@PathVariable("id") long id, @RequestBody BioData data) {
        Optional<BioData> tutorialData = dataRepo.findById(id);

        if (tutorialData.isPresent()) {
            List<BioData> d_name= dataRepo.findUser(data);
            if (d_name.size()<=0) {
                BioData _data = tutorialData.get();
                _data.setName(data.getName());
                _data.setPhone(data.getPhone());

                return new ResponseEntity<>(dataRepo.save(_data), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
        
