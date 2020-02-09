package sci.travel_app.WalkTheBear;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sci.travel_app.Model.Entities.AppUser;
import sci.travel_app.Model.Misc.AppUserRole;
import sci.travel_app.Repository.AppUserRepository;

@SpringBootApplication
public class WalkTheBearApplication {


	public static void main(String[] args) {
		SpringApplication.run(WalkTheBearApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(AppUserRepository repository) {
//		return (args) -> {
//			repository.save(new AppUser("Eugene", "mypassword", "someemail@someemailprovider.com", AppUserRole.TRAVELER ));
//		};
//	}
}
