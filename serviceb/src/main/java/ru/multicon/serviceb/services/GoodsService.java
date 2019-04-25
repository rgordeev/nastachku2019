package ru.multicon.serviceb.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.multicon.serviceb.entities.Good;
import ru.multicon.serviceb.repositories.GoodsRepository;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class GoodsService {

    @Autowired
    private GoodsRepository goodsService;

    public Good save(Good good) {
        return goodsService.save(good);
    }

    public List<Good> list() {
        return goodsService.findAll();
    }
}
