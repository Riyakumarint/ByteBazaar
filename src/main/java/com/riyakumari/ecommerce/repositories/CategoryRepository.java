package com.riyakumari.ecommerce.repositories;

import com.riyakumari.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
