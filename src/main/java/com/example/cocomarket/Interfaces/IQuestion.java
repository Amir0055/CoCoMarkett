package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Question;

import java.util.List;
import java.util.Optional;

public interface IQuestion {
    public List<Question> getAllQuestions();
    public Optional<Question> getQuestionById(Integer id);
    public Question addQuestion(Question question);
    public void deleteQuestion(Integer id);
}
