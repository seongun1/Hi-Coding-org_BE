package com.example.codingmall.Item;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Tag(name = "item" ,description = "상품의 CRUD를 담당하는 api")
@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // 특정 상품 조회
    @Operation(summary = "특정 상품 아이디별로 조회",description = "특정 고유상품의 상품을 개별로 조회합니다.")
    @GetMapping("/public/item/searchId/{itemId}")
    public ResponseEntity<ItemDto>getItemById(@PathVariable(name = "itemId") Long itemId){
        ItemDto itemDto = itemService.findItemById(itemId);
        return ResponseEntity.ok(itemDto);
    }

    //특정 상품 상품명으로 조회
    @Operation(summary = "특정 상품 이름별로 조회", description = "특정 상품을 상품 이름별로 조회합니다.")
    @GetMapping("/public/item/searchName")
    public ResponseEntity<ItemDto> getItemByProductName(@RequestParam(name = "productName") String productName){
        ItemDto itemDto = itemService.findItemByProductName(productName);
        return ResponseEntity.ok(itemDto);
    }

    // 모든 상품 조회
    @GetMapping("/public/item/searchAll")
    @Operation(summary = "모든 상품 조회" , description = "현재 모든 상품을 조회합니다.")
    public ResponseEntity<List<ItemDto>> getAllItem(){
        List<ItemDto> items = itemService.findAllItems();
        return  ResponseEntity.ok(items);
    }

    @PostMapping("/admin/item/create")
    @Operation(summary = "새로운 상품 등록",description = "새로운 상품을 등록합니다.")
    // 새로운 상품 등록
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto){
        // 디버깅용
        System.out.println("Received ItemDto: " + itemDto);
        try {
            Item createdItem = itemService.saveItem(itemDto);
            return ResponseEntity.ok(createdItem);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // 기존 상품 수정.
    @Operation(summary = "기존 상품 수정",description = "기존 상품을 수정합니다.")
    @PutMapping("/admin/item/{itemId}")
    public ResponseEntity<Void> updateItem(@RequestBody ItemDto itemDto){
        itemService.updateItem(itemDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "상품 아이디로 상품 삭제", description = "기존의 상품을 아이디로 삭제합니다")
    //상품 삭제
    @DeleteMapping("/admin/item/delete/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable(name = "itemId") Long itemId){
        itemService.deleteItemById(itemId);
        return ResponseEntity.noContent().build(); // 삭제 후 응답없음.
    }

    @Operation(summary = "상품 좋아요 +1",description = "상품의 좋아요 수를 +1 합니다.")
    // 상품 좋아요 추가
    @PostMapping("/public/items/addLike/{itemId}")
    public ResponseEntity<String> addLikeToItem(@PathVariable(name = "itemId") Long itemId){
        itemService.addLikeToItem(itemId);
        return ResponseEntity.ok("Like added id : " + itemId);
    }
}
