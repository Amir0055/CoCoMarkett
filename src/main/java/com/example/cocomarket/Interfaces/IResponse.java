package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Response;

import java.util.List;
import java.util.Optional;

public interface IResponse {
    public List<Response> getAllResponses();
    public Optional<Response> getResponseById(Integer id);
    public Response addResponse(Response response);
    public void deleteResponse(Integer id);

}
