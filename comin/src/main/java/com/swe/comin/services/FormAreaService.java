package com.swe.comin.services;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.EnumType;
import com.swe.comin.models.FormArea;
import com.swe.comin.repositories.EnumRepository;
import com.swe.comin.repositories.FormAreaRepository;
import com.swe.comin.repositories.PostTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormAreaService {

    private PostTypeRepository postTypeRepository;
    private FormAreaRepository formAreaRepository;
    private EnumRepository enumRepository;

    public FormAreaService(PostTypeRepository postTypeRepository, FormAreaRepository formAreaRepository, EnumRepository enumRepository) {
        this.postTypeRepository = postTypeRepository;
        this.formAreaRepository = formAreaRepository;
        this.enumRepository = enumRepository;
    }

    public FormArea saveFormArea(Long postTypeId, FormArea formArea){
        return postTypeRepository.findById(postTypeId)
                .map(postType -> {
                    formArea.setPostType(postType);
                    return formAreaRepository.save(formArea);
                }).orElseThrow(() -> new ResourceNotFoundException("PostType not found with id " + postTypeId));
    }

    public EnumType saveEnum(Long formAreaId, EnumType enumTypeOfForm){
        return formAreaRepository.findById(formAreaId)
                .map(formArea -> {
                    enumTypeOfForm.setFormArea(formArea);
                    return enumRepository.save(enumTypeOfForm);
                }).orElseThrow(() -> new ResourceNotFoundException("Form area not found with id " + formAreaId));
    }

    public List<FormArea> getFormAreaByPostTypeId(Long id){
        return formAreaRepository.findByPostTypeId(id);
    }

    public List<EnumType> getEnumByFormAreaId(Long id){
        return enumRepository.findByFormAreaId(id);
    }
}
