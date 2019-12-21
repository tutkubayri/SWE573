package com.swe.comin.controllers;

import com.swe.comin.models.EnumType;
import com.swe.comin.models.FormArea;
import com.swe.comin.services.FormAreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FormAreaController {

    private FormAreaService formAreaService;

    public FormAreaController(FormAreaService formAreaService) {

        this.formAreaService = formAreaService;
    }
    @GetMapping("formAreas/{postTypeId}")
    public ResponseEntity<List<FormArea>> getFormAreaByPostTypeId(@PathVariable (value="postTypeId") Long postTypeId){
        return ResponseEntity.ok(formAreaService.getFormAreaByPostTypeId(postTypeId));    }

    @PostMapping({"formAreas/add/{postTypeId}"})
    public ResponseEntity<FormArea> saveFormArea(@PathVariable (value="postTypeId") Long postTypeId, @RequestBody FormArea formArea) {
        return ResponseEntity.ok(formAreaService.saveFormArea(postTypeId, formArea));
    }

    @PostMapping({"formAreas/enum/{formAreaId}"})
    public ResponseEntity<EnumType> saveEnum(@PathVariable (value="formAreaId") Long formAreaId, @RequestBody EnumType enumTypeOfForm) {
        return ResponseEntity.ok(formAreaService.saveEnum(formAreaId, enumTypeOfForm));
    }

    @GetMapping("enums/{formAreaId}")
    public ResponseEntity<List<EnumType>> getEnumByFormAreaId(@PathVariable (value="formAreaId") Long formAreaId){
        return ResponseEntity.ok(formAreaService.getEnumByFormAreaId(formAreaId));    }
}
