import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class  Sem6App {
    private static List<Student> students =new ArrayList<>();
    private static List<Tema> teme=new ArrayList<>();
    private static List<Nota> note=new ArrayList<>();

    public static void main(String[] args) {

        students.add(new Student(1, "Larisa", 224));
        students.add(new Student(2, "Razvan", 223));
        students.add(new Student(3, "Madalina", 224));
        students.add(new Student(4, "Ale", 224));
        students.add(new Student(5, "Alexandra", 227));

        teme.add(new Tema("Tema1", "introducere"));
        teme.add(new Tema("Tema2", "aplicatia de baza"));
        teme.add(new Tema("Tema3", "baze de date"));
        teme.add(new Tema("Tema4", "proiect final"));

        note.add(new Nota(students.get(0), teme.get(0), 9.5, "Istvan"));
        note.add(new Nota(students.get(0), teme.get(1), 3, "Istvan"));
        note.add(new Nota(students.get(0), teme.get(2), 8, "Istvan"));
        note.add(new Nota(students.get(0), teme.get(3), 10, "Istvan"));

        note.add(new Nota(students.get(1), teme.get(0), 10, "Istvan"));
        note.add(new Nota(students.get(1), teme.get(1), 7, "Istvan"));
        note.add(new Nota(students.get(1), teme.get(2), 8, "Istvan"));
        note.add(new Nota(students.get(1), teme.get(3), 10, "Istvan"));

        note.add(new Nota(students.get(2), teme.get(0), 9, "Istvan"));
        note.add(new Nota(students.get(2), teme.get(1), 7, "Istvan"));
        note.add(new Nota(students.get(2), teme.get(2), 8, "Istvan"));
        note.add(new Nota(students.get(2), teme.get(3), 9, "Istvan"));

        note.add(new Nota(students.get(3), teme.get(0), 10, "Istvan"));
        note.add(new Nota(students.get(3), teme.get(1), 10, "Istvan"));
        note.add(new Nota(students.get(3), teme.get(2), 8, "Istvan"));
        note.add(new Nota(students.get(3), teme.get(3), 9, "Istvan"));

        raport1("a");
        raport2("s");
        raport3(224);
        raport4("2");
    }

    private static double averageList(List<Nota> list){
        double Suma=list.stream().map(Nota::getValue).reduce(0.0, Double::sum);
        return Suma/list.size();
    }

    //lista studentilor care contin un string in nume, ordonati descrescator dupa media lor
    static void raport1(String param){
        Map<Student, List<Nota>> studentGrades = note.stream()
                        .filter(t-> t.getStudent().getName().contains(param))
                        .collect(Collectors.groupingBy(Nota::getStudent));
        Map<Student, Double> averageGrades =studentGrades.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.averagingDouble(e-> averageList(e.getValue()))));

        averageGrades.entrySet().stream()
                .sorted(Map.Entry.<Student, Double>comparingByValue().reversed())
                .forEach(x->System.out.println(x.getKey().getName()+" "+x.getValue()));

//        Map<Student, Double> sortedAverageGrades = averageGrades.entrySet().stream()
//                .sorted(Map.Entry.<Student, Double>comparingByValue().reversed())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, _)->value1, LinkedHashMap::new));
//
//        sortedAverageGrades.forEach((x,y)->System.out.println(x.getName()+" "+y));
    }

    //lista profesorilor care contin un string in nume, sortati descrescator dupa media notelor date
    static void raport2(String args){
        Map<String, Double> teacherAverage=note.stream()
                .filter(nota-> nota.getProfesor().contains(args))
                .collect(Collectors.groupingBy(Nota::getProfesor, Collectors.averagingDouble(Nota::getValue)))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, _)->value1, LinkedHashMap::new));
        teacherAverage.forEach((x,y)-> System.out.println(x + " " + y));
    }

    //temele de la o anumita grupa sortate descrescator in functie de numarul studentilor care au primit nota la acea tema
    static void raport3(Integer args){
        note.stream()
                .filter(nota->nota.getStudent().getGroup()==args)
                .collect(Collectors.groupingBy(Nota::getTema, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Tema,Long>comparingByValue().reversed())
                .forEach(x-> System.out.println(x.getKey().getId()+ " " + x.getValue()));
    }

    //grupele care incep cu un anumit numar, sortate descrescator in functie de media notelor primite de studentii acestora
    static void raport4(String args){
        note.stream()
                .filter(nota->String.valueOf(nota.getStudent().getGroup()).startsWith(args))
                .collect(Collectors.groupingBy(x->x.getStudent().getGroup(), Collectors.collectingAndThen(Collectors.toList(),groupGrades->new GroupDTO(averageList(groupGrades), (long)groupGrades.size()))))
                .entrySet().stream()
                .sorted((e1,e2)->Double.compare(e2.getValue().getAverage(), e1.getValue().getAverage()))
                .forEach(x->System.out.println(x.getKey() + " " + x.getValue().getAverage()+ " " + x.getValue().getCount()));
    }

}

