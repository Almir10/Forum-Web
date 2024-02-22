package WebInfo_248.ForumWeb.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String content;
    private LocalDateTime postedDate;

    @ManyToOne
    @JoinColumn(name = "user_id") // Dodato kako bi se definisalo ime foreign keya u bazi za Comment tabelu
    private User postedBy;


    @ManyToOne
    @JoinColumn(name="discussion_id") //Ime foreign keya u bazi podataka za tabelu Comment
    private Discussion discussion;
}