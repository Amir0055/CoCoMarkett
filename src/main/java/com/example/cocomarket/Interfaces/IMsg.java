package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.MSG;
import com.example.cocomarket.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IMsg {
    public List<MSG> getAllMsgs();
    public Optional<MSG> getMsgById(Integer id);
    public MSG addMsg(MSG msg);
    public void deleteMsg(Integer id);


}
