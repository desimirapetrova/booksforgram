package com.example.booksforgram.service.impl;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Role;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.model.entity.enums.UserRoleEnum;
import com.example.booksforgram.model.service.UserEditServiceModel;
import com.example.booksforgram.model.service.UserServiceModel;
import com.example.booksforgram.repository.RoleRepository;
import com.example.booksforgram.repository.UserRepository;
import com.example.booksforgram.service.UserService;
import com.example.booksforgram.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final BooksforgramUserServiceImpl booksforgramUserService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, BooksforgramUserServiceImpl booksforgramUserService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.booksforgramUserService = booksforgramUserService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
         modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {

        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
//        currentUser.setId(id);
//        currentUser.setUsername(username);
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void registerAndLoginUser(UserServiceModel userServiceModel) {
        Role userRole = roleRepository.findByRole(UserRoleEnum.USER);

        User newUser = new User();

        newUser.setUsername(userServiceModel.getUsername());
        newUser .setFirst_name(userServiceModel.getFirst_name());
        newUser.setLast_name(userServiceModel.getLast_name());
        newUser.setActive(true);
        newUser.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        newUser.setRoles(Set.of(userRole));
        newUser.setAge(userServiceModel.getAge());
        newUser.setGender(userServiceModel.getGender());
        newUser.setEmail(userServiceModel.getEmail());

        newUser = userRepository.save(newUser);

        // this is the Spring representation of a user
        UserDetails principal = booksforgramUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }

//    @Override
//    public void activate(Principal principal) {
//       User user= userRepository.findByUsername(principal.getName()).orElse(null);
//        user.setActive(true);
//        userRepository.save(user);
//    }
//
//    @Override
//    public void deactivate(Principal principal) {
//        User user= userRepository.findByUsername(principal.getName()).orElse(null);
//        user.setActive(false);
//        userRepository.save(user);
//    }

    @Override
    public boolean isUserNameFree(String username) {
            return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean isEmailFree(String email) {

        return userRepository.findByEmailIgnoreCase(email).isEmpty();
    }

    @Override
    public void update(UserEditServiceModel serviceModel) {
//        Book book =
//                bookRepository.findById(bookModel.getId()).orElseThrow(() ->
//                        new ObjectNotFoundException("Book with id " + bookModel.getId() + " not found!", bookModel.getId()));
//        book.setPrice(book.getPrice());
//        book.setDescription(book.getDescription());
//        book.setName(bookModel.getName());
//        book.setDescription(bookModel.getDescription());
//        book.setPrice(bookModel.getPrice());
//        book.setAuthor(bookModel.getAuthor());
//        book.setImageUrl(bookModel.getImageUrl());
//        book.setCategory(categoryService.findByCategoryEnum(bookModel.getCategory()));
//        book.setCondition(conditionService.findByConditionEnum(bookModel.getCondition()));
//        bookRepository.save(book);
        User user=userRepository.findById(serviceModel.getId())
                .orElseThrow(() ->
                        new ObjectNotFoundException("User with id " + serviceModel.getId() + " not found!", serviceModel.getId()));
//        user.setUsername(user.getUsername());
//        user.setGender(userRepository.findByGender(serviceModel.getGender()));
        user.setAge(serviceModel.getAge());
        user.setFirst_name(serviceModel.getFirst_name());
        user.setLast_name(serviceModel.getLast_name());
//        user.setEmail(user.getEmail());
        user.setRoles(serviceModel.getRole());
        userRepository.save(user);

    }


    private void initializeUsers() {
        if (userRepository.count() == 0) {

            Role adminRole = roleRepository.findByRole(UserRoleEnum.ADMIN);
            Role userRole = roleRepository.findByRole(UserRoleEnum.USER);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setFirst_name("Admin");
            admin .setLast_name("Adminov");
            admin.setEmail("admin@abv.bg");
            admin.setAge(20);
            admin.setGender(GenderEnum.female);
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);

            User desimira = new User();
            desimira.setUsername("desimira");
            desimira.setPassword(passwordEncoder.encode("11111"));
            desimira.setFirst_name("Desimira");
            desimira.setLast_name("Petrova");
            desimira.setEmail("desi@abv.bg");
            desimira.setAge(22);
            desimira.setGender(GenderEnum.female);
            desimira.setRoles(Set.of(userRole));
            userRepository.save(desimira);

            User pesho = new User();
            pesho.setUsername("pesho12");
            pesho.setPassword(passwordEncoder.encode("22222"));
            pesho.setFirst_name("Petar");
            pesho.setLast_name("Petrov");
            pesho.setEmail("pesho@abv.bg");
            pesho.setAge(28);
            pesho.setGender(GenderEnum.male);
            pesho.setRoles(Set.of(userRole));
            userRepository.save(pesho);
        }
    }

    private void initializeRoles() {

        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRole(UserRoleEnum.ADMIN);

            Role userRole = new Role();
            userRole.setRole(UserRoleEnum.USER);

            roleRepository.saveAll(List.of(adminRole, userRole));
        }
    }
}
