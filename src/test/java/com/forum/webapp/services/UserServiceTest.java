package com.forum.webapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.forum.webapp.dao.IUserDao;
import com.forum.webapp.entities.UserEntity;
import com.forum.webapp.services.impl.UserService;
import com.forum.webapp.web.models.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    final private static long ID = 1234L;
    final private static String EMAIL = "tata.toto@titi.com";
    final private static String PASSWORD = "mypassword";

    private static UserService userService;

    private static IUserDao userDao;

    private static UserEntity userEntity;

    @BeforeClass
    public static void setupClass() {
        // Création du mock de DAO.
        userDao = mock(IUserDao.class);

        userEntity = new UserEntity();
        userEntity.setId(ID);
        userEntity.setFirstName("Tata");
        userEntity.setName("Toto");
        userEntity.setEmail(EMAIL);
        userEntity.setPassword(PASSWORD);
    }

    @Before
    public void setup() {
        // Réinitialise le comportement du Mock.
        reset(userDao);
        when(userDao.login(anyString(), anyString())).thenReturn(null);
        when(userDao.get(anyLong())).thenReturn(null);

        userService = new UserService();
        userService.setUserDao(userDao);
    }

    @Test
    public void testSuccesLogin() {
        // Initialisation du comportement du Mock pour le succes.
        when(userDao.login(eq(EMAIL), eq(PASSWORD))).thenReturn(userEntity);

        // Exécution du test.
        User resultat = userService.login(EMAIL, PASSWORD);

        // Vérification du résultat du test.
        assertNotNull(resultat);
        assertEquals(resultat.getId(), userEntity.getId().longValue());
        assertEquals(resultat.getName(), userEntity.getName());
        assertEquals(resultat.getFirstName(), userEntity.getFirstName());
        assertEquals(resultat.getEmail(), userEntity.getEmail());
        // Vérification des appels sur le DAO.
        // - La méthode login n'a été appelée qu'une seule fois, quels que
        // soient les paramètres.
        verify(userDao, times(1)).login(anyString(), anyString());
        // - La méthode login a bien été appelée avec les paramètres attendus.
        verify(userDao, times(1)).login(eq(EMAIL), eq(PASSWORD));
        // Les autres méthodes n'ont pas été appelées.
    }

    @Test
    public void testErreurLogin() {
        // Exécution du test.
        User resultat = userService.login(EMAIL, PASSWORD);

        // Vérification du résultat du test.
        assertNull(resultat);
        // Vérification des appels sur le DAO.
        // - La méthode login n'a été appelée qu'une seule fois, quels que
        // soient les paramètres.
        verify(userDao, times(1)).login(anyString(), anyString());
        // - La méthode login a bien été appelée avec les paramètres attendus.
        verify(userDao, times(1)).login(eq(EMAIL), eq(PASSWORD));
        // Les autres méthodes n'ont pas été appelées.
    }

    @Test
    public void testSuccesGet() {
        // Initialisation du comportement du Mock pour le succes.
        when(userDao.get(eq(ID))).thenReturn(userEntity);

        // Exécution du test.
        User resultat = userService.get(ID);

        // Vérification du résultat du test.
        assertNotNull(resultat);
        assertEquals(resultat.getId(), userEntity.getId().longValue());
        assertEquals(resultat.getName(), userEntity.getName());
        assertEquals(resultat.getFirstName(), userEntity.getFirstName());
        assertEquals(resultat.getEmail(), userEntity.getEmail());
        // Vérification des appels sur le DAO.
        // - La méthode get n'a été appelée qu'une seule fois, quels que soient
        // les paramètres.
        verify(userDao, times(1)).get(anyLong());
        // - La méthode get a bien été appelée avec les paramètres attendus.
        verify(userDao, times(1)).get(eq(ID));
        // Les autres méthodes n'ont pas été appelées.
    }

    // @Test(expected = ServiceException.class)
    public void testExceptionGet() throws ServiceException {
        // Initialisation du comportement du Mock pour le succes.
        when(userDao.get(anyLong())).thenThrow(new NullPointerException());

        // Exécution du test.
        User resultat = userService.get(ID);

        assertNotNull(resultat);
    }

    // @Test(expected = ServiceException.class)
    public void testCreateUserSansNom() throws ServiceException {
        // TODO
        verifyZeroInteractions(userDao);
    }
}
