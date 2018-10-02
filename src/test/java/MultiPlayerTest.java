/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.mavenproject1.Multi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MANON J
 */
public class MultiPlayerTest {
    
    private Multi m;
    private String[] playerName= {"Matthias","Manon","Léo","Pierre"};
            
    public MultiPlayerTest() {
    }

    
    @Before
    public void setUp() {
        m = new Multi();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void BonneInitialisation() throws Exception{
        assertEquals(m.startNewGame(playerName),"Prochain tir : joueur Matthias, tour n° 1, boule n° 1");
    }
    @Test (expected= UnsupportedOperationException.class)
    public void MauvaiseInitialisation() throws Exception { 
        String[] test = {};
        assertEquals(m.startNewGame(test),"Il n'y a pas de joueurs");
    }
    
    @Test
    public void UnLancer() throws Exception {
        m.startNewGame(playerName);
        m.lancer(2);
        assertEquals("Prochain tir : joueur Matthias, tour n° 1, boule n° 2",m.lancer(2));
    }
    
    @Test
    public void DeuxLancer() throws Exception {
        m.startNewGame(playerName);
        rollMany(2,2);
        assertEquals(m.lancer(2),"Prochain tir : joueur Manon, tour n° 1, boule n° 1");
    }
    
    @Test
    public void ScoreJoueur1() throws Exception {
        m.startNewGame(playerName);
        m.lancer(4);
        m.lancer(3);
        assertEquals(m.scoreFor("Matthias"), 7);
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void PasLeBonJoueur() throws Exception {
        m.startNewGame(playerName);
        assertEquals(m.scoreFor("Patrick"),"Le joueur ne joue pas");
    }
    
    // Quelques methodes utilitaires pour faciliter l'écriture des tests
	private String rollMany(int n, int pins) throws Exception {
		for (int i = 0; i < n-1; i++) {
			m.lancer(pins);
		}
        return m.lancer(pins);
	}
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
