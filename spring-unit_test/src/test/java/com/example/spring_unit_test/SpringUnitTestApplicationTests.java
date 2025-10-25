package com.example.spring_unit_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring_unit_test.models.User;
import com.example.spring_unit_test.repositories.UserRepository;
import com.example.spring_unit_test.services.UserService;

class SpringUnitTestApplicationTests {

	@Test
	void getUserById_Success() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserService userService = new UserService(userRepository);

		User user = new User();
		user.setUsername("pangpons");
		user.setPassword("1234");
		user.setRole(User.Role.USER);
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		User result = userService.getUserById(1L);

		assertNotNull(result);
		assertEquals("pangpons", result.getUsername());
	}

	@Test
	void getUserById_NotFound() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserById(2L));
	}

}
