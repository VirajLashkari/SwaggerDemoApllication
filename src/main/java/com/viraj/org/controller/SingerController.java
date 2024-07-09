package com.viraj.org.controller;

import com.viraj.org.entities.Singer;
import com.viraj.org.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v-1/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /*Get All Singers*/
    @GetMapping("/singers")
    public ResponseEntity getAllSingers() {
        ResponseEntity responseEntity = null;
        List<Singer> allSingers = singerService.getAllSingers();
        if (allSingers == null || allSingers.isEmpty()) {
            String message = "Singer not found";
            responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Singer>>(allSingers, HttpStatus.OK);
        }
        return responseEntity;
    }

    /* Retrieve Singer By id */
    @GetMapping("/singer")
    public ResponseEntity getSingerById(@RequestParam Long id) {
        ResponseEntity responseEntity = null;
        if (singerService.isAvailable(id)) {
            Singer singerById = singerService.getSingerById(id);
            responseEntity = new ResponseEntity<Singer>(singerById, HttpStatus.OK);
        } else {
            return new ResponseEntity("Not available", HttpStatus.OK);
        }
        return responseEntity;
    }

    /* Create */
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Singer singer) {
        ResponseEntity<String> responseEntity = null;
        try {
            Long loong = singerService.saveSingers(singer);
            responseEntity = new ResponseEntity<String>("Singer '" + loong + "' Created", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    /* Update */
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Singer singer) {
        ResponseEntity<String> responseEntity = null;
        boolean available = singerService.isAvailable(singer.getId());
        if (available) {
            singerService.updateSingers(singer);
            responseEntity = new ResponseEntity<String>("Singer '" + singer.getId() + "' Updated", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<String>("Singer '" + singer.getId() + "' Not Available", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    /* Delete */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        ResponseEntity<String> responseEntity = null;
        boolean available = singerService.isAvailable(id);
        if (available) {
            singerService.deleteSingers(id);
            responseEntity = new ResponseEntity<String>("Singer '" + id + "' Deleted", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<String>("Singer '" + id + "' Not Available", HttpStatus.OK);
        }
        return responseEntity;
    }

}
