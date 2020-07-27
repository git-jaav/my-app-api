package com.jaav.sys.myappapi.service.impl;

import com.jaav.sys.myappapi.model.entities.MeEncuestaTema;
import com.jaav.sys.myappapi.repository.MeEncuestaTemaRepository;
import com.jaav.sys.myappapi.service.MeEncuestaTemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MeEncuestaTemaServiceImpl implements MeEncuestaTemaService {

    @Autowired
    MeEncuestaTemaRepository encuestaTemaRepository;

    @Override
    public Optional<List<MeEncuestaTema>> getAllElements() {
        return Optional.ofNullable(encuestaTemaRepository.findAll()).map(e -> {
            return StreamSupport.stream(encuestaTemaRepository.findAll()
                    .spliterator(), false)
                    .collect(Collectors.toList());
            }
        );
    }
}
