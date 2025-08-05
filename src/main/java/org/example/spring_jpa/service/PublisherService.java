package org.example.spring_jpa.service;


import org.example.spring_jpa.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService  {
    Iterable<Publisher> findAll();

}
