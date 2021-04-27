package dao;


import entity.Group;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class GroupDAO {

    //load all groups from the file group in to a list
    public List<Group> loadGroup(String fname) throws Exception {
        List<Group> g = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        String line = "";
        while ((line = reader.readLine()) != null){
            line = line.trim();
            if(!line.isEmpty()){
                g.add(new Group(line));
            }
        }
        reader.close();
        return g;
    }

    //save all groups from a given list to a text file
    public  void saveGroupToFile(List<Group> g, String fname) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
        Iterator<Group> groupIterator = g.iterator();
        while (groupIterator.hasNext()){
            Group x = groupIterator.next();
            writer.write(x.toString());
        }
        writer.close();
    }

    //return the first position of a given contact group in the list
    //otherwise return -1
    public int indexOf(List<Group> list, Group g) {
        for (int i = 0; i < list.size(); i++) {
            Group x = list.get(i);
            if(x.getName().equalsIgnoreCase(g.getName())){
                return i;
            }
        }
        return -1;
    }
    //save a group to a current list
    public  void saveGroupToList(List<Group> list, Group g) {
        list.add(g);
    }

    //return a list of Contact who information matched given search word
    public  List<Group> search(List<Group> c, String search) {
        List<Group> groups = new Vector();
        Iterator<Group> groupIterator = c.iterator();

        while(groupIterator.hasNext()) {
            Group x = groupIterator.next();
            String s = x.toString().toLowerCase();
            if (s.contains(search.toLowerCase())) {
                groups.add(x);
            }
        }

        return groups;
    }

    //update a group in groups by a newGroup
    public  boolean updateGroup(List<Group> groups, int i, String newGroup) {

        int exist = indexOf(groups, new Group(newGroup));

        if(exist != -1){
            return false;
        }
        groups.get(i).setName(newGroup);
        return true;
    }
}
