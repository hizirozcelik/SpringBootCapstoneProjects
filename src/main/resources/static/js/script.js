function getProject(id) {

	if (document.getElementById("project" + id).innerHTML == "") {

		fetch('http://localhost:8080/getProject/' + id) // use HomeController to fetch from our service
			.then(project => project.json()) // JSONify the data returned
			.then(function(project) { // with the JSON data
				// modify textToDisplay below here!

				var textToDisplay = ""; // create and append to a blank var 
				
					textToDisplay += "Title: " + project.title + "<br>";
					textToDisplay += "Team Name: " + project.teamName + "<br>";
					textToDisplay += "<a></a>" + project.link + "<br>";
							
			
				document.getElementById("project" + id).innerHTML = textToDisplay;

			});

	} else {
		document.getElementById("project" + id).innerHTML = "";
	}
};

function postVote(id) {
	push('http://localhost:8080/getProject/' + id);
	
	
}
