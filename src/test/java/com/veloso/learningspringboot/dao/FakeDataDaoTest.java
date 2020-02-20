package com.veloso.learningspringboot.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.veloso.learningspringboot.model.User;
import com.veloso.learningspringboot.model.User.Gender;

public class FakeDataDaoTest {
	
	private FakeDataDao fakeDataDao;
	
	public FakeDataDaoTest() {
	}

	@Before
	public void setUp() throws Exception {
		fakeDataDao = new FakeDataDao();
	}

	@Test
	public void shouldSelectAllUsers() {
		List<User> users = fakeDataDao.selectAllUsers();
		assertThat(users).hasSize(1);
		User user = users.get(0);
		assertThat(user.getAge()).isEqualTo(22);
		assertThat(user.getFirstName()).isEqualTo("Joe");
		assertThat(user.getLastName()).isEqualTo("Jones");
		assertThat(user.getGender()).isEqualTo(Gender.MALE);
		assertThat(user.getEmail()).isEqualTo("joe.jones@gmail.com");
		assertThat(user.getUserUid()).isNotNull();
		
	}

	@Test
	public void shouldSelectUserByUserUid() {
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid,"anna","montana", Gender.FEMALE, 30, "anna@gmail.com");
		fakeDataDao.insertUser(annaUserUid, anna);
		assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
		
		Optional<User> annaOptional = fakeDataDao.selectUserByUserUid(annaUserUid);
		assertThat(annaOptional.isPresent()).isTrue();
		assertThat(annaOptional.get()).isEqualToComparingFieldByField(anna);
	}
	
	@Test
	public void shouldNotSelectUserByRandomUserUid() {
		Optional<User> user = fakeDataDao.selectUserByUserUid(UUID.randomUUID());
		assertThat(user.isPresent()).isFalse();
	}

	@Test
	public void testUpdateUser() {
		UUID joeUserUid =  fakeDataDao.selectAllUsers().get(0).getUserUid();
		User newJoe = new User(joeUserUid,"anna","montana", Gender.FEMALE, 30, "anna@gmail.com");
		fakeDataDao.updateUser(newJoe);
		Optional<User> user = fakeDataDao.selectUserByUserUid(joeUserUid);
		assertThat(user.isPresent()).isTrue();
		assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
		assertThat(user.get()).isEqualToComparingFieldByField(newJoe);
	}

	@Test
	public void testDeleteUserByUserUid() {
		UUID joeUserUid =  fakeDataDao.selectAllUsers().get(0).getUserUid();
		fakeDataDao.deleteUserByUserUid(joeUserUid);
		assertThat(fakeDataDao.selectUserByUserUid(joeUserUid).isPresent()).isFalse();
		assertThat(fakeDataDao.selectAllUsers()).isEmpty();;
	}

	@Test
	public void testInsertUser() {
		UUID annaUserUid = UUID.randomUUID();
		User anna = new User(annaUserUid,"anna","montana", Gender.FEMALE, 30, "anna@gmail.com");
		fakeDataDao.insertUser(annaUserUid, anna);
		assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
		Optional<User> annaOptional = fakeDataDao.selectUserByUserUid(annaUserUid);
		assertThat(annaOptional.isPresent()).isTrue();
		assertThat(annaOptional.get()).isEqualToComparingFieldByField(anna);
	}

}
