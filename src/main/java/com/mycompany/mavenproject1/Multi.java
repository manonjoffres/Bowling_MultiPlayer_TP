/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.ArrayList;
import java.util.HashMap;
import sun.security.util.Length;

/**
 *
 * @author MANON J
 */
public class Multi implements MultiPlayerGame{
    
    HashMap<String,SinglePlayerGame> Table = new HashMap<String,SinglePlayerGame>();
    ArrayList<String> NameList= new ArrayList<String>();
    int cptPlayer = -1;     

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if (playerName.length==0){
            throw new UnsupportedOperationException ("Il n'y a pas de joueurs");
        }
        
        for(int i=0;i<playerName.length;i++){
            Table.put(playerName[i], new SinglePlayerGame());
            NameList.add(playerName[i]);
        }
        cptPlayer=0;
        return "Prochain tir : joueur "+ NameList.get(0)+", tour n° 1, boule n° 1";
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            SinglePlayerGame Player = this.Table.get(this.NameList.get(this.cptPlayer));
            
            if (this.cptPlayer == -1) {
                throw new UnsupportedOperationException("La partie  n'a pas démarré");
            }
            
            if (this.Table.get(this.NameList.get(this.NameList.size()-1)).getCurrentFrame()==null){
                    throw new UnsupportedOperationException("La partie est finie");
                }    
           
            if(Player.getCurrentFrame().isFinished()){
                    this.cptPlayer= (this.cptPlayer+1)%this.NameList.size();
                }
            
            if (Player.getCurrentFrame()!=null){
                Player.lancer(nombreDeQuillesAbattues);
                
            }
            
            
            return "Prochain tir : joueur "+ this.NameList.get(this.cptPlayer)+", tour n° "+ Player.getCurrentFrame().getFrameNumber() +", boule n° "+ Player.getCurrentFrame().getBallsThrown();
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        SinglePlayerGame Player = Table.get(NameList.get(cptPlayer));
        if (Table.containsKey(playerName)==true){
            return Player.score();
        }
        else {
            throw new UnsupportedOperationException("Le joueur ne joue pas");
        }
        
    }
    
}
