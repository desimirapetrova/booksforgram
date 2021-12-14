package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.entity.PromoBook;
import com.example.booksforgram.repository.CategoryRepository;
import com.example.booksforgram.repository.ConditionRepository;
import com.example.booksforgram.repository.PromoBookRepository;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.PromoBookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromoBookServiceImpl implements PromoBookService {
    private final PromoBookRepository promoBookRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;

    public PromoBookServiceImpl(PromoBookRepository promoBookRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, ConditionRepository conditionRepository, UserRepository userRepository) {
        this.promoBookRepository = promoBookRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PromoBook> findAll() {
        return promoBookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, PromoBook.class))
                .collect(Collectors.toList());
    }

    @Override
    public void initPromoBooks() {
        PromoBook promoBook=new PromoBook();
        promoBook.setAuthor("Димитър Мавров");
        promoBook.setName("Под черните ми криле");
        promoBook.setPrice(6);
        promoBook.setCategory(categoryRepository.getById(Long.valueOf(2)));
        promoBook.setCondition(conditionRepository.getById(Long.valueOf(2)));
        promoBook.setOwner(userRepository.getById(Long.valueOf(1)));
        promoBook.setQuantity(1);
        promoBook.setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/p/o/87ef49300d2abda7fbb02d542aa2049c/pod-chernite-mi-krile-30.jpg");
        promoBook.setDescription("Димитър Мавров е сред новите имена в българската поезия. За себе си казва, че е циничен хлапак и старец воин, намиращ вдъхновение навсякъде. С течение на годините писането се превръща в начин да изразява стихията в душата си – онази сила, скрила се под черните му криле.\n" +
                "\n" +
                "А дали е благословия, или проклятие – отговорът е във вашите ръце.");
        promoBookRepository.save(promoBook);
    }

    @Override
    public void deletePromoBooks() {
        promoBookRepository.deleteAll();
    }

    @Override
    public void show() {
//       todo
    }

}
