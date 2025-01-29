package se.lexicon.g52todoapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    // Todo: Start Here - Implement the accurate relationship annotation. The foreign key column in Person table shall be "email".
    @NonNull
    private User user;

    // Todo: Start Here - Implement the accurate relationship annotation.
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task... tasks) { //task1, task2, task3
        if (Objects.requireNonNull(tasks).length == 0)
            throw new IllegalArgumentException("Tasks cannot be empty");
        for (Task task : tasks) {
            this.tasks.add(task);
            if (task.getPerson() != null) {
                task.setPerson(this);
            }
        }
    }

    public void removeTask(Task... tasks) {
        if (Objects.requireNonNull(tasks).length == 0)
            throw new IllegalArgumentException("Tasks cannot be empty");
        for (Task task : tasks) {
            if (this.tasks.remove(task) && task.getPerson() == this) {
                task.setPerson(null);
            }
        }
    }


}
