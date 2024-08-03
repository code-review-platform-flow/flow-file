package com.flow.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flow.file.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
