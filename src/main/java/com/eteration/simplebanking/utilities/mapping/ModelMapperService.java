package com.eteration.simplebanking.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

    ModelMapper forRequest();
    ModelMapper forDto();
}
