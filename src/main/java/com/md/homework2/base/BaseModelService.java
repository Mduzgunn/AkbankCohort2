package com.md.homework2.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public abstract class BaseModelService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

    private final R repository;

    public E save(E model) {
        BaseAdditionalFields baseAdditionalFields = model.getBaseAdditionalFields();
        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
        }

        if (model.getId() == null) {
            baseAdditionalFields.setCreateDate(LocalDateTime.now());
        }

        baseAdditionalFields.setUpdateDate(LocalDateTime.now());

        model.setBaseAdditionalFields(baseAdditionalFields);
        model =repository.save(model);

        return model;
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public void delete(E entity){
        repository.delete(entity);
    }

    public Optional<E> findById(Long id){
        return repository.findById(id);
    }

    public E findByIdWithControl(Long id){
        return repository.findById(id).orElseThrow();
    }

    public boolean isExist(Long id){
        return repository.existsById(id);
    }

}
