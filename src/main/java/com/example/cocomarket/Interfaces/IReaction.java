package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Publication;
import com.example.cocomarket.Entity.Reaction;
import com.example.cocomarket.Entity.ReactionType;
import com.example.cocomarket.Entity.User;

import java.util.Set;

public interface IReaction {


    void ajouterReaction(Integer userId, Integer publicationId, ReactionType reactionType);

}
