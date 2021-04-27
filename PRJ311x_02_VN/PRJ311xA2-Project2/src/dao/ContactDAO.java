package dao;

import entity.Contact;

import java.io.*;
import java.util.*;


public class ContactDAO {

    //load all Contacts from the file Contact in to a list
    public List<Contact> loadContact(String fname) throws Exception {
        List<Contact> contacts = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        String line = "";
        while ((line = reader.readLine()) != null){
            line = line.trim();
            if(!line.isEmpty()){
                String[] st = line.split(":");
                contacts.add(new Contact(st[0].trim(), st[1].trim(), st[2].trim(), st[3].trim(), st[4].trim(), st[5].trim()));
            }
        }

        reader.close();
        return contacts;
    }

    //save all Contacts from a given list to a text file
    public  void saveToFile(List<Contact> g, String fname) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
        Iterator v = g.iterator();

        while (v.hasNext()){
            Contact contact = (Contact) v.next();
            writer.write(contact.toString());
         }

        writer.close();
    }

    //return the first position of a given contact g in the list
    //otherwise return -1
    public int indexOf(List<Contact> list, Contact g) {
        for (int i = 0; i < list.size(); i++) {
            Contact x = (Contact) list.get(i);
            if(x.getFirstName().equalsIgnoreCase(g.getFirstName()) && x.getLastName().equalsIgnoreCase(g.getLastName())){
                return i;
            }
        }
        return -1;
    }
    //save a Contact to a current list
    public  void saveToList(List<Contact> list, Contact g) {
        list.add(g);
    }
    //update information of a contact c at position i in the list
    public  void updateContact(List<Contact> list, Contact c, int i) {
        Contact x = list.get(i);
        x.setDob(c.getDob());
        x.setEmail(c.getEmail());
        x.setFirstName(c.getFirstName());
        x.setLastName(c.getLastName());
        x.setGroup(c.getGroup());
        x.setPhone(c.getPhone());
    }
    //return a list of Contact who information matched given search word
    public  List<Contact> search(List<Contact> c, String group, String search) {
        if(group.equals("All")){
            group = "";
        }
        List<Contact> contacts = new ArrayList<>();
        Iterator<Contact> contactIterator = c.iterator();
        while (contactIterator.hasNext()){
            Contact x = contactIterator.next();
            String s = x.toString().toLowerCase();
            if(s.contains(search.toLowerCase()) && x.getGroup().contains(group)){
                contacts.add(x);
            }
        }

        return contacts;
    }
    //return a list of Contact who is in a given group
    public  List<Contact> contactByGroup(List<Contact> c, String group) {
        if (group.equals("All")) {
            return c;
        } else {
            List<Contact> ct = new Vector();
            Iterator var4 = c.iterator();

            while(var4.hasNext()) {
                Contact x = (Contact)var4.next();
                String s = x.getGroup().toLowerCase();
                if (s.contains(group.toLowerCase())) {
                    ct.add(x);
                }
            }

            return ct;
        }
    }
}
