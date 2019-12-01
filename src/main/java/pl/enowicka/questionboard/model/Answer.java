package pl.enowicka.questionboard.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    private User user;

    @NotEmpty
    @NotNull
    @Size(min = 20, max = 5000)
    private String content;

    @NotNull
    @ManyToOne
    private Question question;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @OrderBy("createdOn")
    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setAnswer(this);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        question.getAnswers().add(this);
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                Objects.equals(user, answer.user) &&
                Objects.equals(content, answer.content) &&
                Objects.equals(question, answer.question) &&
                Objects.equals(createdOn, answer.createdOn) /*&&
                Objects.equals(comments, answer.comments)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, content, question, createdOn/*, comments*/);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", question=" + question +
                ", createdOn=" + createdOn +
//                ", comments=" + comments +
                '}';
    }
}
