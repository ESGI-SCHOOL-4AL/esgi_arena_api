package com.example.demo.algorithm;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {
    @Value("${custom.http.algotithm.resolver.url.labyrinth}") 
    String urlLabyrinth;

    @Value("${custom.http.algotithm.resolver.url.chinese_rings}") 
    String urlChineseRings;

    @Value("${custom.http.algotithm.resolver.url.escape_way}")
    String escapeWays;


    @PostMapping(path = "labyrinth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlgorithmPoint> labyrinthResolver(@RequestBody List<List<Integer>> inputMatrix) {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity inputEntity = new HttpEntity<List<List<Integer>>>(inputMatrix, headers);

        ResponseEntity<List<AlgorithmPoint>> response = template.postForEntity(this.urlLabyrinth, inputEntity, (Class<List<AlgorithmPoint>>)(Class<?>)List.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    @PostMapping(path = "escape-ways", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<List<AlgorithmPoint>> escapeWaysResolver(@RequestBody List<List<Integer>> inputMatrix) {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity inputEntity = new HttpEntity<List<List<Integer>>>(inputMatrix, headers);

        ResponseEntity<List<List<AlgorithmPoint>>> response = template.postForEntity(this.escapeWays, inputEntity, (Class<List<List<AlgorithmPoint>>>)(Class<?>)List.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }


    @PostMapping(path = "chinese-rings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<List<Boolean>> chinesesRingsResolver(@RequestBody Integer size) {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity inputEntity = new HttpEntity<Integer>(size, headers);

        ResponseEntity<List<List<Boolean>>> response = template.postForEntity(this.urlChineseRings, inputEntity, (Class<List<List<Boolean>>>)(Class<?>)List.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
    
}