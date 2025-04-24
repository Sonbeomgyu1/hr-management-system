package com.apple.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 증가
   public Long id;

   private String title;
   private Integer price;
   private Integer count;
   private String type;

}
