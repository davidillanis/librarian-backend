package com.microservice.library.service.other;

import com.microservice.library.model.entity.*;
import com.microservice.library.model.repository.LibrarianRepository;
import com.microservice.library.model.repository.RoleRepository;
import com.microservice.library.model.repository.StudentRepository;
import com.microservice.library.model.repository.UserRepository;
import com.microservice.library.service.mapped.*;
import com.microservice.library.util.other.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class AddDataServiceImpl {
    @Autowired
    private BookService bookService;
    @Autowired
    private CopyBookService copyBookService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private RequestService requestService;
    @Autowired
    private LoanService loanService;

    public void addDataBase(){
        addUserRole();
        //addRole();
        addBook();
        addCopyBook();
        addRequest();
        addLoan();
        //bookService.getListPopularCopyBook().forEach(t-> System.out.println(t.getIdLibr()));
    }

    private void addBook(){
        List<BookEntity> bookEntities= Arrays.asList(
                BookEntity.builder().idLibr(0).tituLibr("To Kill a Mockingbird").isbnLibr("9780061120084").fechPublLibr(LocalDate.now()).editLibr("J.B. Lippincott & Co.").autoLibr("Harper Lee").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("1984").isbnLibr("9780451524935").fechPublLibr(LocalDate.now()).editLibr("Secker & Warburg").autoLibr("George Orwell").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("Pride and Prejudice").isbnLibr("9780679783268").fechPublLibr(LocalDate.of(1813, 1, 28)).editLibr("T. Egerton, Whitehall").autoLibr("Jane Austen").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("The Catcher in the Rye").isbnLibr("9780316769488").fechPublLibr(LocalDate.of(1951, 7, 16)).editLibr("Little, Brown and Company").autoLibr("J.D. Salinger").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("The Great Gatsby").isbnLibr("9780743273565").fechPublLibr(LocalDate.of(1925, 4, 10)).editLibr("Charles Scribner's Sons").autoLibr("F. Scott Fitzgerald").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("Moby-Dick").isbnLibr("9781503280786").fechPublLibr(LocalDate.of(1851, 10, 18)).editLibr("Harper & Brothers").autoLibr("Herman Melville").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("War and Peace").isbnLibr("9780199232765").fechPublLibr(LocalDate.of(1869, 1, 1)).editLibr("The Russian Messenger").autoLibr("Leo Tolstoy").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("The Hobbit").isbnLibr("9780547928227").fechPublLibr(LocalDate.of(1937, 9, 21)).editLibr("George Allen & Unwin").autoLibr("J.R.R. Tolkien").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("Fahrenheit 451").isbnLibr("9781451673319").fechPublLibr(LocalDate.of(1953, 10, 19)).editLibr("Ballantine Books").autoLibr("Ray Bradbury").copyBookEntities(null).build(),
                BookEntity.builder().idLibr(0).tituLibr("Brave New World").isbnLibr("9780060850524").fechPublLibr(LocalDate.of(1932, 1, 1)).editLibr("Chatto & Windus").autoLibr("Aldous Huxley").copyBookEntities(null).build()
        );
        bookEntities.stream().forEach(bookEntity -> bookService.createEntity(bookEntity));
    }

    private void addCopyBook(){
        List<CopyBookEntity> copyBookEntities=List.of(
                CopyBookEntity.builder().codiEjem("C001").locaEjem("LOCATION01-A").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(1)).build(),
                CopyBookEntity.builder().codiEjem("C002").locaEjem("LOCATION02-B").estaEjem("Regular").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(1)).build(),
                CopyBookEntity.builder().codiEjem("C003").locaEjem("LOCATION03-C").estaEjem("Malo").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(2)).build(),
                CopyBookEntity.builder().codiEjem("C004").locaEjem("LOCATION04-A").estaEjem("Malo").habiEjem(false).estaPresEjem(true).bookEntity(bookService.getEntity(2)).build(),
                CopyBookEntity.builder().codiEjem("C005").locaEjem("LOCATION05-B").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(3)).build(),
                CopyBookEntity.builder().codiEjem("C006").locaEjem("LOCATION02-B").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(3)).build(),
                CopyBookEntity.builder().codiEjem("C007").locaEjem("LOCATION01-C").estaEjem("Malo").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(4)).build(),
                CopyBookEntity.builder().codiEjem("C008").locaEjem("LOCATION04-C").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(4)).build(),
                CopyBookEntity.builder().codiEjem("C009").locaEjem("LOCATION03-B").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(5)).build(),
                CopyBookEntity.builder().codiEjem("C010").locaEjem("LOCATION02-B").estaEjem("Malo").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(5)).build(),
                CopyBookEntity.builder().codiEjem("C011").locaEjem("LOCATION03-B").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(6)).build(),
                CopyBookEntity.builder().codiEjem("C012").locaEjem("LOCATION01-A").estaEjem("Malo").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(6)).build(),
                CopyBookEntity.builder().codiEjem("C013").locaEjem("LOCATION02-A").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(7)).build(),
                CopyBookEntity.builder().codiEjem("C014").locaEjem("LOCATION03-B").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(7)).build(),
                CopyBookEntity.builder().codiEjem("C015").locaEjem("LOCATION01-C").estaEjem("Malo").habiEjem(true).estaPresEjem(false).bookEntity(bookService.getEntity(8)).build(),
                CopyBookEntity.builder().codiEjem("C016").locaEjem("LOCATION02-A").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(8)).build(),
                CopyBookEntity.builder().codiEjem("C017").locaEjem("LOCATION05-B").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(9)).build(),
                CopyBookEntity.builder().codiEjem("C018").locaEjem("LOCATION01-C").estaEjem("Malo").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(9)).build(),
                CopyBookEntity.builder().codiEjem("C019").locaEjem("LOCATION02-A").estaEjem("Bueno").habiEjem(true).estaPresEjem(false).bookEntity(bookService.getEntity(10)).build(),
                CopyBookEntity.builder().codiEjem("C020").locaEjem("LOCATION03-B").estaEjem("Bueno").habiEjem(true).estaPresEjem(true).bookEntity(bookService.getEntity(10)).build()
        );
        copyBookEntities.stream().forEach(t->copyBookService.createEntity(t));
    }

    private void addUserRole(){
        /* Create ROLES */
        RoleEntity roleStudent = RoleEntity.builder()
                .role(ERole.STUDENT)
                .build();

        RoleEntity roleLibrarian = RoleEntity.builder()
                .role(ERole.LIBRARIAN)
                .build();

        // CREATE USERS
        UserEntity userSantiago = UserEntity.builder().username("santiago")
                .password("$2a$10$7DGa1.WZarPw54o4EdwrfO7Okgio0/o66g2OjOFW7CTcXVxFo3tbq").isEnabled(true).roles(Set.of(roleStudent))
                .DNIUsua("DNI001").apelMaternoUsua("").apelPaternoUsua("").nombUsua("").teleUsua("").build();
        UserEntity userDavid = UserEntity.builder().username("david")
                .password("$2a$10$7DGa1.WZarPw54o4EdwrfO7Okgio0/o66g2OjOFW7CTcXVxFo3tbq").isEnabled(true).roles(Set.of(roleStudent))
                .DNIUsua("DNI002").apelMaternoUsua("").apelPaternoUsua("").nombUsua("").teleUsua("").build();
        UserEntity userRomel = UserEntity.builder().username("romel")
                .password("$2a$10$7DGa1.WZarPw54o4EdwrfO7Okgio0/o66g2OjOFW7CTcXVxFo3tbq").isEnabled(true).roles(Set.of(roleStudent))
                .DNIUsua("DNI003").apelMaternoUsua("Serna").apelPaternoUsua("Berrocal").nombUsua("Romel").teleUsua("").build();
        UserEntity userJose = UserEntity.builder().username("jose")
                .password("$2a$10$7DGa1.WZarPw54o4EdwrfO7Okgio0/o66g2OjOFW7CTcXVxFo3tbq").isEnabled(true).roles(Set.of(roleStudent))
                .DNIUsua("DNI004").apelMaternoUsua("Silvera").apelPaternoUsua("Romany").nombUsua("Jose Luis").teleUsua("").build();
        UserEntity userLuis = UserEntity.builder().username("luis")
                .password("$2a$10$7DGa1.WZarPw54o4EdwrfO7Okgio0/o66g2OjOFW7CTcXVxFo3tbq").isEnabled(true).roles(Set.of(roleStudent))
                .DNIUsua("DNI005").apelMaternoUsua("Quispe").apelPaternoUsua("Quispe").nombUsua("Luis").teleUsua("").build();
        UserEntity userDaniel = UserEntity.builder().username("daniel")
                .password("$2a$10$7DGa1.WZarPw54o4EdwrfO7Okgio0/o66g2OjOFW7CTcXVxFo3tbq").isEnabled(true).roles(Set.of(roleLibrarian))
                .DNIUsua("DNI006").apelMaternoUsua("").apelPaternoUsua("").nombUsua("").teleUsua("").build();
        userRepository.saveAll(List.of(userSantiago, userDavid, userRomel, userJose, userLuis, userDaniel));

        studentRepository.save(new StudentEntity(0, LocalDate.now(), userSantiago, null, null));
        studentRepository.save(new StudentEntity(0, LocalDate.now(), userDavid, null, null));
        studentRepository.save(new StudentEntity(0, LocalDate.now(), userRomel, null, null));
        studentRepository.save(new StudentEntity(0, LocalDate.now(), userJose, null, null));
        studentRepository.save(new StudentEntity(0, LocalDate.now(), userLuis, null, null));
        librarianRepository.save(new LibrarianEntity(0, LocalDate.now(), userDaniel, null));
    }

    private void addRole(){
        roleRepository.saveAll(List.of(
                RoleEntity.builder().role(ERole.STUDENT).build(),
                RoleEntity.builder().role(ERole.LIBRARIAN).build()
        ));
    }

    private void addRequest(){
        StudentEntity studentEntity = studentRepository.findById(1).orElse(null);
        CopyBookEntity copyBookEntity=copyBookService.getEntity("C001");
        RequestEntity requestEntity=new RequestEntity(0, LocalDate.now(), LocalDate.now(), studentEntity, copyBookEntity);
        //requestService.createEntity(requestEntity);

        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(1).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C001")).build());
        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(1).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C002")).build());
        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(1).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C003")).build());

        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(2).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C004")).build());
        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(2).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C001")).build());
        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(2).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C005")).build());

        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(3).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C001")).build());
        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(3).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C006")).build());

        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(4).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C001")).build());
        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(4).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C007")).build());

        requestService.createEntity(RequestEntity.builder().idSoli(0).fechPrestSoli(LocalDate.now()).fechRecoSoli(LocalDate.now())
                .studentEntity(studentRepository.findById(5).orElse(null))
                .copyBookEntity(copyBookService.getEntity("C001")).build());

    }

    private void addLoan(){
        //libro 1
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C001")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(1).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C002")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(1).orElse(null)).build());
        //libro2
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C003")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(2).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C004")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(2).orElse(null)).build());
        //libro3
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C005")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(3).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C006")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(3).orElse(null)).build());
        //libro4
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C007")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(4).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C008")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(4).orElse(null)).build());
        //libro5
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C009")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C010")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        //libro6
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C011")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C012")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        //libro7
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C013")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C014")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        //libro8
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C015")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C016")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        //libro9
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C017")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C018")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        //libro10
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C019")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C020")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(5).orElse(null)).build());
        loanService.createEntity(LoanEntity.builder().idPrest(0).fechPres(LocalDate.now()).fechDevoPres(LocalDate.now()).obsePres(null)
                .copyBookEntity(copyBookService.getEntity("C020")).librarianEntity(librarianRepository.findById(1).orElse(null))
                .studentEntity(studentRepository.findById(2).orElse(null)).build());
    }
}
