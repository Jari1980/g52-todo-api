package se.lexicon.g52todoapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import se.lexicon.g52todoapi.domain.dto.RoleDTOForm;
import se.lexicon.g52todoapi.domain.dto.RoleDTOView;
import se.lexicon.g52todoapi.domain.dto.UserDTOForm;
import se.lexicon.g52todoapi.domain.entity.User;
import se.lexicon.g52todoapi.service.RoleService;
import se.lexicon.g52todoapi.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@SpringBootApplication
public class G52TodoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(G52TodoApiApplication.class, args);
    }


    @Profile("dev")
    @Bean
    public CommandLineRunner runner(RoleService roleService, UserService userService) {
        return (args) -> {

/*
           List<RoleDTOView> all = roleService.getAll();
//↖️Add breakpoint 🔴
            // Add Breakpoint then run Debugger to see how the program executes your code.
            // (Be aware of long routes. If you decide to go into Java/Spring Source code.
            //  Remember to "Step out" if you get to deep.)

            all.forEach(System.out::println);
*/

/*
            //Using Constructors
            new User("Simon@lexicon.se", null, false, new TreeSet<>());
            new User("Simon@lexicon.se", "123456");
            User user = new User("Simon@lexicon.se");
            user.setPassword("123456");
            user.setExpired(true);
            user.setRoles(null);

            //Using builder pattern implementer by Lombok.
            User usingBuilder = User.builder()
                    .email("Simon@lexicon.se")
                    .password("123456")
                    .build();
*/

            UserDTOForm simon = UserDTOForm.builder()
                    .email("simon@lexicon.se")
                    .password("123456")
                    .roles(Set.of(RoleDTOForm.builder()
                            .id(1L)
                            .name("ADMIN").build()))
                    .build();

            userService.register(simon);

        };
    }

}
