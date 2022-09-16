package com.dityudha.spring.data.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dityudha.spring.data.model.Tutorial;
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findBySold(boolean sold);
  List<Tutorial> findByBrandContaining(String brand);
}