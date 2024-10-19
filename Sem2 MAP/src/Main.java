import java.util.*;

public class Main {
    public static void main(String[] args) {
        Student s1=new Student("Dan", 4.5f);
        Student s2=new Student("Ana", 8.5f);
        Student s3=new Student("Dan", 4.5f);

//        Set<Student> students=new HashSet<>();
//        students.add(s1);
//        students.add(s2);
//        students.add(s3);
//        for( Student s:students){
//            System.out.println(s);
//        }
//
//        Set<Student> treeSet=new TreeSet<>();
//        treeSet.add(s1);
//        treeSet.add(s2);
//        treeSet.add(s3);
//        for( Student s:treeSet){
//            System.out.println(s);
//        }
//
//        Set<Student> treeSet1=new TreeSet<>(
//                new Comparator<Student>() {
//                    public int compare(Student o1, Student o2) {
//                        return o2.compareTo(o1);
//                    }
//                }
//        );
//        treeSet1.add(s1);
//        treeSet1.add(s2);
//        treeSet1.add(s3);
//        for( Student s:treeSet1){
//            System.out.println(s);
//        }

//        Map<String, Student> map1=new TreeMap<>();
//        map1.put(s1.getNume(), s1);
//        map1.put(s2.getNume(), s2);
//        map1.put(s3.getNume(), s3);
//        for( Map.Entry<String, Student>s:map1.entrySet()){
//            System.out.println(s);
//        }

//        Map<String, Student> map2=new HashMap<>();
//        map2.put(s1.getNume(), s1);
//        map2.put(s2.getNume(), s2);
//        map2.put(s3.getNume(), s3);
//        for( Map.Entry<String, Student>s:map2.entrySet()){
//            System.out.println(s);
//        }

//        MyMap map=new MyMap();
//        List<Student> list= getList();
//        for(Student s:list){
//            map.addStudent(s);
//        }
//        for(Map.Entry<Integer,List<Student>> e:map.getEntries()){
//            System.out.println(e.getKey());
//            List<Student> sorted=e.getValue();
//            Collections.sort(sorted);
//            for(Student s:sorted){
//                System.out.println(s);
//            }
//        }

//        MyMathComposition map=new MyMathComposition();
//        List<Student> list= getList();
//        for(Student s:list){
//            map.addStudent(s);
//        }
//        for(Map.Entry<Integer,List<Student>> e:map.getEntries()){
//            System.out.println(e.getKey());
//            List<Student> sorted=e.getValue();
//            Collections.sort(sorted);
//            for(Student s:sorted){
//                System.out.println(s);
//            }
//        }

        Repository<String, Student> repo=new InMemoryRepository<>();
        repo.save(s1);
        repo.save(s2);
        repo.save(s3);

        for(Student s:repo.findAll()){
            System.out.println(s);
        }

    }

    public static List<Student> getList(){
        List<Student> l=new ArrayList<>();
        l.add(new Student("1",9.7f));
        l.add(new Student("2",7.3f));
        l.add(new Student("3",6f));
        l.add(new Student("4",6.9f));
        l.add(new Student("5",9.5f));
        l.add(new Student("6",9.9f));
        return l;
    }

}


