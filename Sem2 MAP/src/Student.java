import java.util.Objects;

public class Student extends Entity<String> implements Comparable<Student> {
    private String nume;
    private float media;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
        this.id=nume;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public Student(String name, float media) {
        this.nume = name;
        this.media = media;
        this.id=nume;
    }

    @Override
    public void setId(String id) {
        this.id=id;
        this.nume=id;
    }

    @Override
    public String toString() {
        return "Student [name=" + nume + ", media=" + media + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, media);
    }

    @Override
    public int compareTo(Student o) {
        return this.nume.compareTo(o.nume);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Float.compare(media, student.media) == 0 && Objects.equals(nume, student.nume);
    }
}
