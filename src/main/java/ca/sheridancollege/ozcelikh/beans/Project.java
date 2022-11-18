package ca.sheridancollege.ozcelikh.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Project {
	
	private Long id;
	private String title;
	private String teamName;
	private String link;
	private Integer vote;

}
