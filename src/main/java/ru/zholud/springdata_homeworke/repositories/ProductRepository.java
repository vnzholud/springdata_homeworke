package ru.zholud.springdata_homeworke.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zholud.springdata_homeworke.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetween(Integer min, Integer max);


    Optional<Product> findByTitle(String name);

    @Query("select p from Product p where p.price < 20")
    List<Product> findLowRatingProducts();

    @Query("select p.price from Product p where p.title = ?1")
    Integer hqlGetScoreByName(String name);

    @Query(value = "select price from products where title = :title", nativeQuery = true)
    Integer nativeSqlGetPriceByTitle(String title);


}
