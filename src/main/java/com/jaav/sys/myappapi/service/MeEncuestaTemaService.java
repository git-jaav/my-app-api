package com.jaav.sys.myappapi.service;

import com.jaav.sys.myappapi.model.entities.MeEncuestaTema;

import java.util.List;
import java.util.Optional;

public interface MeEncuestaTemaService {

    Optional<List<MeEncuestaTema>> getAllElements();

}
