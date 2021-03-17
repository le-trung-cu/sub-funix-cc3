public class App {
    MyList<Person> persons;

    App(){
        persons = new MyList<>();
    }

    public static void main(String[] args) throws Exception {
        String[] a = { "HOA", "HA", "LAN", "NOI", "MUA", "NAY" };
        int[] b = { 25, 17, 17, 17, 23, 21 };
        App app = new App();
        app.intialData(a, b);

        System.out.println("Traverse:");
        app.persons.traverse();

        System.out.println("Sort by name:");

        app.persons.sort(new MyList.Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        app.persons.traverse();


        System.out.println("Sort by age:");

        app.persons.sort(new MyList.Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });

        app.persons.traverse();

        System.out.println();
    }

    public void intialData(String[] names, int[] ages) {
        for (int i = 0; i < ages.length; i++) {
            Person person = new Person(names[i], ages[i]);
            persons.add(person);
        }
    }
}
