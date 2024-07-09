package com.viraj.org.service;

import com.viraj.org.entities.Singer;

import java.util.List;

public interface SingerService {

    public Long saveSingers(Singer singer);

    public void updateSingers(Singer singers);

    public void deleteSingers(Long id);

    public Singer getSingerById(Long id);

    public boolean isAvailable(Long id);

    public List<Singer> getAllSingers();
}
