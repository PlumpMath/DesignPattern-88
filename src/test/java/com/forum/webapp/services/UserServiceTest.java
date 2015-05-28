package com.forum.webapp.services;

import static org.mockito.Mockito.*;
import static junit.framework.Assert.*;

import com.forum.webapp.dao.IUserDao;
import com.forum.webapp.entities.UserEntity;
import com.forum.webapp.services.impl.UserService;
import com.forum.webapp.web.models.User;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class UserServiceTest {

    final private static long ID = 1234L;
    final private static String EMAIL = "tata.toto@titi.com";
    final private static String PASSWORD = "mypassword";
    private static UserService _userService;
    private static IUserDao _userDao;
    private static UserEntity _userEntity;

    @BeforeClass
    public static void setupClass() {
        // Création du mock de DAO.
        _userDao = mock(IUserDao.class);

        _userEntity = new UserEntity();
        _userEntity.setId(ID);
        _userEntity.setFirstName("Tata");
        _userEntity.setName("Toto");
        _userEntity.setEmail(EMAIL);
        _userEntity.setPassword(PASSWORD);
    }

    @Before
    public void setup() {
        // Réinitialise le comportement du Mock.
        reset(_userDao);
        when(_userDao.login(anyString(), anyString())).thenReturn(null);
        when(_userDao.get(anyLong())).thenReturn(null);

        _userService = new UserService();
        _userService.setUserDao(_userDao);
    }

    @Test
    public void testSuccesLogin() {
        // Initialisation du comportement du Mock pour le succes.
        when(_userDao.login(eq(EMAIL), eq(PASSWORD))).thenReturn(_userEntity);

        // Exécution du test.
        User resultat = _userService.login(EMAIL, PASSWORD);

        // Vérification du résultat du test.
        assertNotNull(resultat);
        assertEquals(resultat.getId(), _userEntity.getId().longValue());
        assertEquals(resultat.getName(), _userEntity.getName());
        assertEquals(resultat.getFirstName(), _userEntity.getFirstName());
        assertEquals(resultat.getEmail(), _userEntity.getEmail());
        // Vérification des appels sur le DAO.
        // - La méthode login n'a été appelée qu'une seule fois, quels que soient les paramètres.
        verify(_userDao, times(1)).login(anyString(), anyString());
        // - La méthode login a bien été appelée avec les paramètres attendus.
        verify(_userDao, times(1)).login(eq(EMAIL), eq(PASSWORD));
        // Les autres méthodes n'ont pas été appelées.
    }

    @Test
    public void testErreurLogin() {
        // Exécution du test.
        User resultat = _userService.login(EMAIL, PASSWORD);

        // Vérification du résultat du test.
        assertNull(resultat);
        // Vérification des appels sur le DAO.
        // - La méthode login n'a été appelée qu'une seule fois, quels que soient les paramètres.
        verify(_userDao, times(1)).login(anyString(), anyString());
        // - La méthode login a bien été appelée avec les paramètres attendus.
        verify(_userDao, times(1)).login(eq(EMAIL), eq(PASSWORD));
        // Les autres méthodes n'ont pas été appelées.
    }

    @Test
    public void testSuccesGet() {
        // Initialisation du comportement du Mock pour le succes.
        when(_userDao.get(eq(ID))).thenReturn(_userEntity);

        // Exécution du test.
        User resultat = _userService.get(ID);

        // Vérification du résultat du test.
        assertNotNull(resultat);
        assertEquals(resultat.getId(), _userEntity.getId().longValue());
        assertEquals(resultat.getName(), _userEntity.getName());
        assertEquals(resultat.getFirstName(), _userEntity.getFirstName());
        assertEquals(resultat.getEmail(), _userEntity.getEmail());
        // Vérification des appels sur le DAO.
        // - La méthode get n'a été appelée qu'une seule fois, quels que soient les paramètres.
        verify(_userDao, times(1)).get(anyLong());
        // - La méthode get a bien été appelée avec les paramètres attendus.
        verify(_userDao, times(1)).get(eq(ID));
        // Les autres méthodes n'ont pas été appelées.
    }

    //@Test(expected = ServiceException.class)
    public void testExceptionGet() throws ServiceException{
        // Initialisation du comportement du Mock pour le succes.
        when(_userDao.get(anyLong())).thenThrow(new NullPointerException());

        // Exécution du test.
        User resultat = _userService.get(ID);
    }
    
    

    //@Test(expected = ServiceException.class)
    public void testCreateUserSansNom() throws ServiceException{
        // TODO
        verifyZeroInteractions(_userDao);
    }
}
