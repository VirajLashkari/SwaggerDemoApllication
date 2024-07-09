package com.viraj.org.serviceImpl;

import com.viraj.org.entities.Singer;
import com.viraj.org.repositories.SingerRepository;
import com.viraj.org.service.SingerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public Long saveSingers(Singer singer) {
        return singerRepository.save(singer).getId();
    }

    @Override
    public void updateSingers(Singer singers) {
        singerRepository.save(singers);
    }

    @Override
    public void deleteSingers(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public Singer getSingerById(Long id) {
        return singerRepository.findById(id).orElse(null);
    }

    @Override
    public boolean isAvailable(Long id) {
        return singerRepository.existsById(id);
    }

    @Override
    public List<Singer> getAllSingers() {
        return singerRepository.findAll();
    }
}
