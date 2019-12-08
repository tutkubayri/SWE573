package com.swe.comin.services;

import com.swe.comin.exception.ResourceNotFoundException;
import com.swe.comin.models.FormArea;
import com.swe.comin.repositories.FormAreaRepository;
import com.swe.comin.repositories.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormAreaService {

    private PostTypeRepository postTypeRepository;
    private FormAreaRepository formAreaRepository;

    public FormAreaService(PostTypeRepository postTypeRepository, FormAreaRepository formAreaRepository) {
        this.postTypeRepository = postTypeRepository;
        this.formAreaRepository = formAreaRepository;
    }

    public FormArea saveFormArea(Long postTypeId, FormArea formArea){
        return postTypeRepository.findById(postTypeId)
                .map(postType -> {
                    formArea.setPostType(postType);
                    return formAreaRepository.save(formArea);
                }).orElseThrow(() -> new ResourceNotFoundException("PostType not found with id " + postTypeId));
    }

    public List<FormArea> getFormAreaByPostTypeId(Long id){
        return formAreaRepository.findByPostTypeId(id);
    }
}
