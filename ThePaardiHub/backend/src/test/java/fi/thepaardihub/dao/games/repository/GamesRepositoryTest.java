/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.thepaardihub.dao.games.repository;

import fi.thepaardihub.dao.games.tables.Games;
import fi.thepaardihub.dao.users.repository.UserRolesRepository;
import fi.thepaardihub.dao.users.tables.UserRoles;
import fi.thepaardihub.rest.jsonobject.GameSet;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author maaritemilia
 */
    @RunWith(SpringRunner.class)
    @SpringBootTest

public class GamesRepositoryTest {
         @Autowired
    private GamesRepository gamesrepo;
    
    public GamesRepositoryTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        Games game1 = new Games("Masa", "Village idiot", true , "3");
        Games game2 = new Games("Mikko", "Peli", false , "1");

        gamesrepo.save(game1);
        gamesrepo.save(game2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findByAuthor method, of class GamesRepository.
     */
    @Test
    public void testFindByAuthor() {
        
        System.out.println("findByAuthor");
        String email = "";
        GamesRepository instance = new GamesRepositoryImpl();
        ArrayList<GameSet> expResult = null;
        ArrayList<GameSet> result = instance.findByAuthor(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
         Games test1 = gamesrepo.findOne(1);
        assertNotNull(test1);
        assertEquals("Village idiot", test1.getAuthor());

        fail("The test case is a prototype.");
    }

    /**
     * Test of findByID method, of class GamesRepository.
     */
    @Test
    public void testFindByID() {
        System.out.println("findByID");
        String id = "";
        GamesRepository instance = new GamesRepositoryImpl();
        GameSet expResult = null;
        GameSet result = instance.findByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    public class GamesRepositoryImpl implements GamesRepository {

        public ArrayList<GameSet> findByAuthor(String email) {
            return null;
        }

        public GameSet findByID(String id) {
            return null;
        }

        @Override
        public <S extends Games> S save(S s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Games> Iterable<S> save(Iterable<S> itrbl) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Games findOne(Integer id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean exists(Integer id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Iterable<Games> findAll() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Iterable<Games> findAll(Iterable<Integer> itrbl) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public long count() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void delete(Integer id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void delete(Games t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void delete(Iterable<? extends Games> itrbl) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteAll() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
