package com.example.demo.translator.repository;

import com.example.demo.translator.model.PairTextsModel;
import org.springframework.data.repository.CrudRepository;

public interface PairTextsRepository extends CrudRepository<PairTextsModel, Integer> {
}
