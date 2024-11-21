package com.example.codingmall.Item;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    @EntityGraph(attributePaths = {"category"})
    Optional<Item> findByProductName( String productName);
    // 상품 이름으로 검색
    default Item findItemByProductName(String productName){
        return findByProductName(productName).orElseThrow(()-> new IllegalStateException("그러한 제품 이름이 없습니다." + productName));
    }
    @EntityGraph(attributePaths = {"category"})
    default Item findItemById(Long itemId){
        return findById(itemId).orElseThrow(()->new IllegalStateException("이러한 제품 아이디가 없습니다 : " +itemId));
    }
    @EntityGraph(attributePaths = {"category"})
    List<Item> findAll();
    List<Item> findAllByStatus(String status);
    List<Item> findByCategoryId(Long categoryId); // 카테고리 별로 제품 조회
    List<Item>findAllByOrderByLikesDesc(); // 찜하기 수가 높은 순으로 상품 조회

}
