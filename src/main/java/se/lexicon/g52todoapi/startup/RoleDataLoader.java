package se.lexicon.g52todoapi.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import se.lexicon.g52todoapi.domain.entity.Role;
import se.lexicon.g52todoapi.repository.RoleRepository;

import java.util.Arrays;

@Profile({"test","dev"}) // TODO: Verify if it works
@Component
public class RoleDataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepository.saveAll(Arrays.asList(
                new Role("ADMIN"),
                new Role("USER"),
                new Role("GUEST")));


    }
}
