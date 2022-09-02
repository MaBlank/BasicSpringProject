package Example.Tutorial.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {
	@Id
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age", nullable = false, updatable = true)
	private long age;

	@Column(name = "Gehalt")
	private long gehalt;

	@Transient
	private long rente;


	// public Student() {}

	public Student(long id, String name, long age, long gehalt) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gehalt = gehalt;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", gehalt=" + gehalt +
				", rente=" + rente +
				'}';
	}

	public long getRente() {
		return getGehalt() * 2;
	}

	public int compareTo(Student other){
		if(getGehalt() > other.getGehalt()) return 1;
		else if(getGehalt() < other.getGehalt()) return -1;
		else return 0;
	}

}
