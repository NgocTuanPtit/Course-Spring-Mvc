package com.tony_funny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tony_funny.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long>{}
