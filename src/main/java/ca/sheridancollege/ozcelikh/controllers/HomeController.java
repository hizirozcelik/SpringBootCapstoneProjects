package ca.sheridancollege.ozcelikh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.ozcelikh.beans.Project;




@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index(Model model, RestTemplate restTemplate) {

		ResponseEntity<Project[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/projects",
				Project[].class);

		model.addAttribute("project", new Project());
		model.addAttribute("projectList", responseEntity.getBody());
		return "index";
	}

	@GetMapping(value = "/getProject/{id}", produces = "application/json")
	@ResponseBody
	public Project getProject(@PathVariable Long id, RestTemplate restTemplate) {
		ResponseEntity<Project> responseEntity = restTemplate.getForEntity("http://localhost:8080/projects/{id}",
				Project.class, id);
		return responseEntity.getBody();
	}
	
	
//	@RequestMapping(value = "/getVote/{id}", method = RequestMethod.POST)
//	public ResponseEntity<String> getVote(@RequestBody Project p, RestTemplate restTemplate) {
//
//	    int vote = p.getVote() +1;
//	    String response = restTemplate.postForEntity("http://localhost:8080/getVote/{id}", p, String.class, id);
//	    
//	    return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
	@GetMapping("/updateVote/{id}")
	public String postVote(Model model, @PathVariable Long id, RestTemplate restTemplate) {

		

		Project[] projectList = restTemplate.getForObject("http://localhost:8080/projects", Project[].class);
		model.addAttribute("projectList", projectList);
		model.addAttribute("project", new Project());

		return "index";

	}
	
	@PostMapping("/addProject")
	public String postProject(Model model, @ModelAttribute Project p, RestTemplate restTemplate) {

		String url = restTemplate.postForObject("http://localhost:8080/projects", p, String.class);
		model.addAttribute("url", url);

		Project[] projectList = restTemplate.getForObject("http://localhost:8080/projects", Project[].class);
		model.addAttribute("projectList", projectList);
		model.addAttribute("project", new Project());

		return "index";

	}
	
	
	@GetMapping("/updateProject/{id}")
	public String editItemById(Model model, @PathVariable Long id, RestTemplate restTemplate) {


		ResponseEntity<Project> responseEntity = restTemplate.getForEntity("http://localhost:8080/projects/{id}", Project.class, id);
		model.addAttribute("project", responseEntity.getBody());
		
		Project[] projectList = restTemplate.getForObject("http://localhost:8080/projects", Project[].class);
		model.addAttribute("projectList", projectList);
		model.addAttribute("project", new Project());

		return "index";
	}
	

}
