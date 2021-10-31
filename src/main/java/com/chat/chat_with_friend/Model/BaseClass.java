package com.chat.chat_with_friend.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "time_create")
    private Date timeCreate;

    @UpdateTimestamp
    @Column(name = "time_update")
    private Date timeUpdate;
}
