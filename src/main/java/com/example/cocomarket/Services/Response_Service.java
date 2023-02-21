package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Response;
import com.example.cocomarket.Interfaces.IResponse;
import com.example.cocomarket.Repository.Response_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Response_Service implements IResponse {
    @Autowired
    Response_Repository responseRepository;

    @Override
    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    @Override
    public Optional<Response> getResponseById(Integer id) {
        return responseRepository.findById(id);
    }

    @Override
    public Response addResponse(Response response) {
        return responseRepository.save(response);
    }

    @Override
    public void deleteResponse(Integer id) {
        responseRepository.deleteById(id);
    }
}
