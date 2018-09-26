package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    HashMap players = new HashMap();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        String key = firstName + " ## " + lastName;
        SoccerPlayer player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);

        if (getPlayer(firstName, lastName) == null){
            players.put(key, player);
            return true;
        }
        else {
            return false;
        }
	}


    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if (players.remove(firstName + " ## " + lastName) != null) return true;
        else return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        return (SoccerPlayer) players.get(firstName + " ## " + lastName);
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        SoccerPlayer player = (SoccerPlayer) players.get(firstName + " ## " + lastName);
        if (player != null){
            player.bumpGoals();
            return true;
        }
        else return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        SoccerPlayer player = (SoccerPlayer) players.get(firstName + " ## " + lastName);
        if (player != null){
            player.bumpAssists();
            return true;
        }
        else return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        SoccerPlayer player = (SoccerPlayer) players.get(firstName + " ## " + lastName);
        if (player != null){
            player.bumpShots();
            return true;
        }
        else return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        SoccerPlayer player = (SoccerPlayer) players.get(firstName + " ## " + lastName);
        if (player != null){
            player.bumpSaves();
            return true;
        }
        else return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        SoccerPlayer player = (SoccerPlayer) players.get(firstName + " ## " + lastName);
        if (player != null){
            player.bumpFouls();
            return true;
        }
        else return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        SoccerPlayer player = (SoccerPlayer) players.get(firstName + " ## " + lastName);
        if (player != null){
            player.bumpYellowCards();
            return true;
        }
        else return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        SoccerPlayer player = (SoccerPlayer) players.get(firstName + " ## " + lastName);
        if (player != null){
            player.bumpRedCards();
            return true;
        }
        else return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        if (teamName == null) return players.size();
        else{
            int count = 0; //current number of players counted on team

            for (Object s : players.keySet()){
                String key = (String) s;
                SoccerPlayer player = (SoccerPlayer) players.get(key);
                if(player.getTeamName() == teamName) count++;
            }

            return count;
        }
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int count = -1; //player index; 0 is the first player...
        if (teamName == null) {

            for (Object s : players.keySet()) {
                count++;

                if (count == idx){
                    String key = (String) s;
                    SoccerPlayer player = (SoccerPlayer) players.get(key);
                    return player;
                }
            }

        } else {

            for (Object s : players.keySet()) {
                String key = (String) s;
                SoccerPlayer player = (SoccerPlayer) players.get(key);

                if (player.getTeamName() == teamName) count++;
                if (count == idx) return player;
            }

        }

        return null; //should never happen bc of error checking by SoccerActivity
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        try {
            if (!file.exists()) return false;
            else {
                Scanner scan = new Scanner(file);

                while(scan.hasNextLine()) {
                    String firstName = logString(scan.nextLine());
                    String lastName = logString(scan.nextLine());
                    String team = logString(scan.nextLine());
                    int uniform = Integer.valueOf(logString(scan.nextLine()));
                    int goals = Integer.valueOf(logString(scan.nextLine()));
                    int assists = Integer.valueOf(logString(scan.nextLine()));
                    int shots = Integer.valueOf(logString(scan.nextLine()));
                    int saves = Integer.valueOf(logString(scan.nextLine()));
                    int fouls = Integer.valueOf(logString(scan.nextLine()));
                    int yellowCards = Integer.valueOf(logString(scan.nextLine()));
                    int redCards = Integer.valueOf(logString(scan.nextLine()));

                    if(getPlayer(firstName, lastName) == null) {
                        addPlayer(firstName, lastName, uniform, team);
                        SoccerPlayer player = getPlayer(firstName, lastName);
                        for (int i = 0; i < goals; i++) {
                            player.bumpGoals();
                        }
                        for (int i = 0; i < assists; i++) {
                            player.bumpAssists();
                        }
                        for (int i = 0; i < shots; i++) {
                            player.bumpShots();
                        }
                        for (int i = 0; i < saves; i++) {
                            player.bumpSaves();
                        }
                        for (int i = 0; i < fouls; i++) {
                            player.bumpFouls();
                        }
                        for (int i = 0; i < yellowCards; i++) {
                            player.bumpYellowCards();
                        }
                        for (int i = 0; i < redCards; i++) {
                            player.bumpRedCards();
                        }
                        logString("temp");
                    }
                } return true;
            }
        }
        catch(FileNotFoundException ex){
            return false;
        }
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (Object s : players.keySet()) {
                SoccerPlayer player = (SoccerPlayer) players.get(s);
                
                logString("testing...");
                pw.println(logString(player.getFirstName()));
                pw.println(logString(player.getLastName()));
                pw.println(logString(player.getTeamName()));
                pw.println(logString(Integer.toString(player.getUniform())));
                pw.println(logString(Integer.toString(player.getGoals())));
                pw.println(logString(Integer.toString(player.getAssists())));
                pw.println(logString(Integer.toString(player.getShots())));
                pw.println(logString(Integer.toString(player.getSaves())));
                pw.println(logString(Integer.toString(player.getFouls())));
                pw.println(logString(Integer.toString(player.getYellowCards())));
                pw.println(logString(Integer.toString(player.getRedCards())));
            }

            pw.close();
            return true;
        }catch(FileNotFoundException ex){
            return false;
        }
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        HashSet<String> teams = new HashSet<String>();

        for(Object s : players.keySet()){
            SoccerPlayer player = (SoccerPlayer) players.get(s);

            if (!teams.contains(player.getTeamName())){
                teams.add(player.getTeamName());
            }
        }

        return teams;
	}

}
