package com.softlond.store.repositoriesCrudJpa;

import com.softlond.store.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepositoryCrudJpa extends JpaRepository<Categories, Long> {
    List<Categories> findByName(String name);
}