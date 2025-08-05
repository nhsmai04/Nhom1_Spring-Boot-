package org.example.spring_jpa.service.impl;

import org.example.spring_jpa.model.Publisher;
import org.example.spring_jpa.repository.PublisherRepository;
import org.example.spring_jpa.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Iterable<Publisher> findAll()
    {
        return publisherRepository.findAll();
    }
}
