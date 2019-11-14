/*
package com.swe.comin.controllers;

import com.swe.comin.models.FormArea;
import com.swe.comin.models.PostType;
import com.swe.comin.services.FormAreaService;
import com.swe.comin.services.PostTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FormAreaController {

    private FormAreaService formAreaService;

    public FormAreaController(FormAreaService formAreaService) {

        this.formAreaService = formAreaService;
    }

    @GetMapping("communities/{communityId}/postTypes/{postTypeId}")
    public ResponseEntity<List<FormArea>> getFormAreaByPostTypeId(@PathVariable (value="postTypeId") Long postTypeId){
        return ResponseEntity.ok(formAreaService.getFormAreaByPostTypeId(postTypeId));    }

    @PostMapping({"/communities/{communityId}/postTypes/{postTypeId}"})
    public ResponseEntity<FormArea> savePostType(@PathVariable (value="postTypeId") Long postTypeId, @RequestBody FormArea formArea) {
        return ResponseEntity.ok(formAreaService.saveFormArea(postTypeId, formArea));
    }
}*/
