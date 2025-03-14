package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //상세페이지 form 데이터 보내기
    public void saveItem(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setCount(10);
        itemRepository.save(item);
    }
    //리스트페이지에서 상품목록들 보여지게 하는 코드
    public void modelList(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
    }

    //수정폼에서 데이터 보내기
    public void formEdit(String title, Integer price, Long id ){
        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
}
