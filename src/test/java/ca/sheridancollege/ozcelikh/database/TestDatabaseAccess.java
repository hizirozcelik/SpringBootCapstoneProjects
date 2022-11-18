package ca.sheridancollege.ozcelikh.database;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import ca.sheridancollege.ozcelikh.beans.Project;

@SpringBootTest
@AutoConfigureTestDatabase
public class TestDatabaseAccess {
	
	@Autowired
	private DatabaseAccess da;
	
	@Test
	public void testSave_getProjectListIncreasedByOne(){
		int size = da.findAll().size();
		Project project = new Project();
		project.setTitle("Test");
		project.setTeamName("Test");
		project.setLink("http://youtube.com");
		

		da.save(project);

		assertEquals(da.findAll().size(), ++size);

	}


}
