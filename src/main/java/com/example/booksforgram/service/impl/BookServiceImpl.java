package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.binding.PictureBindingModel;
import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Picture;
import com.example.booksforgram.model.entity.Role;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.UserRoleEnum;
import com.example.booksforgram.model.service.BookServiceModel;
import com.example.booksforgram.model.service.BookUpdateServiceModel;
import com.example.booksforgram.model.view.BookViewModel;
import com.example.booksforgram.repository.*;
import com.example.booksforgram.service.*;
import com.example.booksforgram.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ConditionRepository conditionRepository;
    private final CategoryService categoryService;
    private final ConditionService conditionService;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, ConditionRepository conditionRepository, CategoryService categoryService, ConditionService conditionService, CloudinaryService cloudinaryService, UserRepository userRepository, PictureRepository pictureRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;

        this.categoryRepository = categoryRepository;
        this.conditionRepository = conditionRepository;
        this.categoryService = categoryService;
        this.conditionService = conditionService;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }



    @Override
    public BookServiceModel addBook(BookServiceModel bookServiceModel,String owner) throws IOException {
//        MultipartFile img = bookServiceModel.getImage();


        Book book=modelMapper.map(bookServiceModel,Book.class);
        book.setOwner(userRepository.findByUsername(owner).orElse(null));
        book.setCategory(categoryService.findByCategoryEnum(bookServiceModel.getCategory()));
        book.setQuantity(1);
//        book.setPictures(createPictureEntity(img));
        book.setCondition(conditionService.findByConditionEnum(bookServiceModel.getCondition()));
        Book savedBook=bookRepository.save(book);

        return modelMapper.map(savedBook,BookServiceModel.class);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book,Book.class))
                .collect(Collectors.toList());
    }

    @Override
    public void showDetails(Long id) {
        bookRepository.findById(id);
    }


    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book getBookById(Long id) {

        return bookRepository.findById(id).get();
    }
    @Override
    public Book getProductById(Long id) {
        return null;
    }

    @Override
    public Book findByName(String pSearchTerm) {

        return bookRepository.findByName(pSearchTerm);
    }

    @Override
    public void initBooks() {
        if(bookRepository.count()!=0){
            return;
        }
        Book book=new Book();
        book.setName("Ад");
        book.setAuthor("Дан Браун");
        book.setCategory(categoryRepository.getById(Long.valueOf(6)));
        book.setCondition(conditionRepository.getById(Long.valueOf(2)));
        book.setDescription("\"Търсете и ще намерите.\"\n" +
                "\n" +
                "Тези думи отекват в съзнанието на видния харвардски професор по религиозна символика Робърт Лангдън, когато се свестява в болница, без спомен къде се намира и как се е озовал там. Не може да обясни и произхода на страховития предмет, открит сред вещите му.\n" +
                "\n" +
                "Заплаха за живота ще запрати Лангдън и младата лекарка Сиена Брукс на главоломна гонитба из Флоренция. Единствено познаването на тайни проходи и древни загадки, спотайващи се зад историческата фасада, може да ги спаси от неизвестните им преследвачи.\n" +
                "\n" +
                "Водени само от няколко стиха от мрачния шедьовър на Данте, Лангдън и Сиена трябва да разгадаят серия шифри, скрити дълбоко в някои от най-прочутите творби на Ренесанса – скулптури, картини, сгради, – за да открият отговора на загадка, която може би ще им помогне да спасят света от ужасяваща заплаха...\n" +
                "\n" +
                "Разиграващ се на невероятен фон, вдъхновен от една от най-зловещите литературни класики в историята, „Ад” е най-завладяващият и предизвикващ размисъл роман на Дан Браун досега - задъхана надпревара с времето, която ще ви грабне още от първата страница и няма да ви остави до последната.\n");
        book.setPrice(14);
        book.setOwner(userRepository.getById(Long.valueOf(1)));
        book.setImageUrl("https://i4.helikon.bg/products/4316/17/174316/174316_b.jpg?t=1635858886");

        Book book2=new Book();
        book2.setName("Шифърът на Леонардо");
        book2.setAuthor("Дан Браун");
        book2.setQuantity(1);
        book2.setCategory(categoryRepository.getById(Long.valueOf(6)));
        book2.setCondition(conditionRepository.getById(Long.valueOf(1)));
        book2.setDescription("Неразгадаем шифър, скрит в произведенията на Леонардо да Винчи.\n" +
                "Поразителна истина, скрита от векове...\n" +
                "\n" +
                "Докато е в командировка в Париж, харвардският професор по история на символите Робърт Лангдън е събуден посред нощ: един възрастен уредник в Лувъра е убит в музея. Край трупа му полицията намира озадачаващ шифър. Докато се опитва да го разгадае, Лангдън с удивление открива, че той води до пътека от кодове, скрити в творбите на Леонардо - кодове, изложени пред очите на всички, и все пак находчиво скрити от художника.\n" +
                "Лангдън обединява силите си с талантливата френска криптографка Софи Нево и установява, че покойният уредник е бил брат от Ордена на Сион - тайно общество, към което са принадлежали сър Исак Нютон, Ботичели, Виктор Юго, Леонардо и други прочути личности.\n" +
                "В шеметна надпревара из Париж и Лондон Лангдън и Нево се състезават с безлика влиятелна фигура, която сякаш предугажда всеки техен ход. Ако двамата навреме не успеят да разшифроват загадката, древната тайна на Ордена на Сион, сензационна историческа истина, завинаги ще бъде изгубена.\n" +
                "\n" +
                "Романът, четвърти за автора след Шестото клеймо, Метеоритът и Цифрова крепост, утвърждава появата на новия интелигентен трилър... абсолютно непредсказуем до поразителната си развръзка.");
        book2.setPrice(12);
        book2.setOwner(userRepository.getById(Long.valueOf(1)));
        book2.setImageUrl("https://i5.helikon.bg/products/5658/06/65658/65658_b.jpg?t=1635862246");

        Book book3=new Book();
        book3.setName("Цифрова крепост");
        book3.setAuthor("Дан Браун");
        book3.setCategory(categoryRepository.getById(Long.valueOf(1)));
        book3.setCondition(conditionRepository.getById(Long.valueOf(3)));
        book3.setDescription("От автора на “Шифърът на Леонардо”\n" +
                "Мистериозно кодирано съобщение разтърсва коридорите на властта...\n" +
                "\n" +
                "Едно тихо “Благодаря” на двамата анонимни служители на АНС за техния безценен принос!\n" +
                "\n" +
                "Съобщението\n" +
                "\n" +
                "Когато непобедимият компютър на Агенцията за Национална Сигурност за разбиване на шифри се сблъсква с мистериозно кодирано съобщение, с което не може да се справи, Агенцията се обръща към старшия си криптолог Сюзан Флечър - гениално умна и красива математичка. И тя разкрива нещо, което разтърсва коридорите на властта. АНС е в опасност, но не от оръдия или бомби, а заради сложен код, който ако стане публично достояние, ще постави под заплаха устоите на американското разузнаване.\n" +
                "\n" +
                "Кой ще пази пазачите?\n" +
                "\n" +
                "Ако ние сме пазачите на обществото, тогава кой ще наблюдава нас и ще се грижи да не станем на свой ред опасни?!\n" +
                "\n" +
                "“Цифрова крепост” е най-добрият и най-реалистичен технотрилър от години. Способността на Дан Браун да обрисува в естествени краски сивата зона, разделяща демократичните свободи от националната сигурност... е впечатляваща!\n" +
                "Пъблишърс Уикли");
        book3.setPrice(11);
        book3.setOwner(userRepository.getById(Long.valueOf(1)));
        book3.setImageUrl("https://i2.helikon.bg/products/1264/11/111264/111264_b.jpg?t=1635862246");

        bookRepository.save(book);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }

    @Override
    public List<Book> findAllByOwner(User owner) {
       return bookRepository.findByOwner(owner).stream()
                .map(book -> modelMapper.map(book,Book.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByNameOrAuthor(String pSearchTerm) {

        return bookRepository.searchBook(pSearchTerm);
    }




    @Override
    public List<Book> findByCategory(Long id) {
        return bookRepository.findByCategory(id);
    }

    @Override
    public BookViewModel findById2(Long id, String userIdentifier) {
        BookViewModel bookViewModel = this.bookRepository.
                findById(id).
                map(o -> mapDetailsView(userIdentifier, o))
                .get();
        return bookViewModel;
    }

    @Override
    public void updateBook(BookUpdateServiceModel bookModel) {
        Book book =
                bookRepository.findById(bookModel.getId()).orElseThrow(() ->
                        new ObjectNotFoundException("Book not found!"));
        book.setPrice(book.getPrice());
             book.setDescription(book.getDescription());
                book.setName(bookModel.getName());
                book.setDescription(bookModel.getDescription());
                book.setPrice(bookModel.getPrice());
                book.setAuthor(bookModel.getAuthor());
                book.setImageUrl(bookModel.getImageUrl());
        book.setCategory(categoryService.findByCategoryEnum(bookModel.getCategory()));
        book.setCondition(conditionService.findByConditionEnum(bookModel.getCondition()));
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    @Override
    public List<Book> findBookById(Long id) {

        return bookRepository.findAllByOwner_Id(id);
    }



    public boolean isOwner(String userName, Long id) {
        Optional<Book> bookOptional = bookRepository.
                findById(id);
        Optional<User> caller = userRepository.
                findByUsername(userName);

        if (bookOptional.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            Book book = bookOptional.get();

            return isAdmin(caller.get()) ||
                    book.getOwner().getUsername().equals(userName);
        }
    }

    private boolean isAdmin(User user) {
        return user.
                getRoles().
                stream().
                map(Role::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }


    private BookViewModel mapDetailsView(String currentUser, Book book) {
        BookViewModel bookViewModel = this.modelMapper.map(book, BookViewModel.class);
//        bookViewModel.setCanDelete(isOwner(currentUser, offer.getId()));
        bookViewModel.setName(book.getName());
        bookViewModel.setPrice(book.getPrice());
        bookViewModel.setImageUrl(book.getImageUrl());
        bookViewModel.setDescription(book.getDescription());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setCondition(book.getCondition().getName());
        bookViewModel.setCategory(book.getCategory().getName());
//        bookViewModel.setPicture(book.getPictures());
        return bookViewModel;
    }
    private Picture createPictureEntity(MultipartFile file) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);
        Picture picture=new Picture();
        picture.setPublicId(uploaded.getPublicId());
        picture.setUrl(uploaded.getUrl());
        return pictureRepository.save(picture);
//        return picture;
    }
    //    private Picture getPictureEntity(MultipartFile img) throws IOException {
//        if (!"".equals(img.getOriginalFilename())) {
//            final CloudinaryImage uploaded = cloudinaryService.upload(img);
//            return pictureRepository.save(new Picture()
//                    .setUrl(uploaded.getUrl())
//                    .setPublicId(uploaded.getPublicId()));
//        } else {
//            return pictureRepository.save(new Picture(DEFAULT_BOOK_IMAGE_URL));
//        }
//    }

}
