package Example.Tutorial.Entity;

import Example.Tutorial.Entity.Student;

import java.util.Comparator;

public class GehaltSorter implements Comparator<Student>
{
    @Override
    public int compare(Student o1, Student o2) {
        return o2.compareTo(o1);
    }
}