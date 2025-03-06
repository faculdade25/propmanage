package app.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "log")
@Getter
@Setter
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String action;

	@Column(nullable = false)
	private String tabela;

	@Column(nullable = false, name = "entity_id")
	private Long entityId;

	private String nome;

	@Column(nullable = false)
	private LocalDateTime timestamp;
}