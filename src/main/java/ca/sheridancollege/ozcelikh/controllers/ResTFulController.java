package ca.sheridancollege.ozcelikh.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.ozcelikh.beans.Project;
import ca.sheridancollege.ozcelikh.database.DatabaseAccess;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/projects")
public class ResTFulController {

	private DatabaseAccess da;

	@GetMapping(value = "/{id}")
	public Project getIndividual(@PathVariable Long id) {
		return da.findById(id);
	}
	
	@GetMapping
	public List<Project> getCollection() {
		return da.findAll();

	}

	@PostMapping(consumes = "application/json")
	public String postIndividual(@RequestBody Project p) {
		return "http://localhost:8080/projects/" + da.save(p);

	}

	@PatchMapping(value = "/{id}", consumes = "application/json")
	public String postVote(@PathVariable Long id, @RequestBody Project p) {
		System.out.println("debeee");
		Project c = da.findById(id);
		int vote = p.getVote() + 1;
		c.setVote(vote);
		da.updateProject(c);
		return "Record updated";

	}

}